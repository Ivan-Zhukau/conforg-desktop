package net.ostis.confman.ui.conference.parts;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.DateChooserField;
import net.ostis.confman.ui.common.component.DateDataConverter;
import net.ostis.confman.ui.common.component.EditableComponent;
import net.ostis.confman.ui.common.component.StringDataConverter;
import net.ostis.confman.ui.common.component.TextField;
import net.ostis.confman.ui.common.component.ValueBinder;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;
import net.ostis.confman.ui.reports.SelectReportDialog;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class SectionEditorPart {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum SectionFields implements Localizable {
        TITLE("sectionTitle");

        private String rk;

        private SectionFields(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum DateChooserFields implements Localizable {
        DATE("sectionDate");

        private String rk;

        private DateChooserFields(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum Buttons implements Localizable {
        SAVE("save"),
        SHOW_REPORT_DIALOG("showReportDialog"),
        SELECT_DATE("selectDate");

        private String rk;

        private Buttons(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }

    }

    private final Map<SectionFields, EditableComponent<TextField>>            editFields;

    private final Map<DateChooserFields, EditableComponent<DateChooserField>> dateChooserFields;

    @Inject
    private ESelectionService                                                 selectionService;

    @Inject
    private IEventBroker                                                      eventBroker;

    private ConferenceService                                                 conferenceService;

    private Section                                                           section;

    public SectionEditorPart() {

        super();
        this.editFields = new EnumMap<>(SectionFields.class);
        this.dateChooserFields = new EnumMap<>(DateChooserFields.class);
        this.conferenceService = (ConferenceService) ServiceLocator
                .getInstance().getService(ConferenceService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (selection instanceof Section) {
                    final Section section = (Section) selection;
                    SectionEditorPart.this.section = section;
                    onSectionEvent(section);
                }
            }
        });
        buildLayout(parent);
    }

    protected void onSectionEvent(final Section section) {

        applyValueBindings(section);
        for (final SectionFields field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }

        for (final DateChooserFields field : this.dateChooserFields.keySet()) {
            this.dateChooserFields.get(field).activate();
        }
    }

    private void applyValueBindings(final Section section) {

        this.editFields.get(SectionFields.TITLE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        section.setTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return section.getTitle();
                    }
                });
        this.dateChooserFields.get(DateChooserFields.DATE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        section.setDate((Date) value);
                    }

                    @Override
                    public Object getValue() {

                        return section.getDate();
                    }
                });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));
        this.editFields.put(SectionFields.TITLE,
                new TextField(parent, util.translate(SectionFields.TITLE))
                        .setDataConverter(new StringDataConverter()));
        final DateDataConverter dateConverter = new DateDataConverter();
        this.dateChooserFields.put(
                DateChooserFields.DATE,
                new DateChooserField(parent, util
                        .translate(DateChooserFields.DATE))
                        .setDataConverter(dateConverter));
        addReportButton(parent, util);
        addApplyButton(parent, util);
    }

    private void addApplyButton(final Composite parent,
            final LocalizationUtil util) {

        final Button button = new Button(parent, SWT.PUSH);
        button.setText(util.translate(Buttons.SAVE));
        button.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                onUpdate();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                onUpdate();
            }
        });
    }

    private void addReportButton(final Composite parent,
            final LocalizationUtil util) {

        final Button showReportDialogButton = new Button(parent, SWT.PUSH);
        showReportDialogButton.setText(util
                .translate(Buttons.SHOW_REPORT_DIALOG));
        showReportDialogButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                showReportDialog(parent);
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                showReportDialog(parent);
            }
        });
    }

    private void onUpdate() {

        for (final SectionFields field : this.editFields.keySet()) {
            this.editFields.get(field).apply();

        }
        this.eventBroker.post(ConferenceTopics.CONF_UPDATE, null);
    }

    private void showReportDialog(final Composite parent) {

        final SelectReportDialog dialog = new SelectReportDialog(
                parent.getShell());
        dialog.create();
        if (dialog.open() == Window.OK) {
            final Report selectedReport = dialog.getSelectedReport();
            if (selectedReport != null) {
                this.conferenceService.addReport(this.section, selectedReport);
            }
        }
    }
}
