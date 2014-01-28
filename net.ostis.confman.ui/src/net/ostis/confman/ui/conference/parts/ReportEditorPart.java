package net.ostis.confman.ui.conference.parts;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.CheckBoxField;
import net.ostis.confman.ui.common.component.ComboBoxField;
import net.ostis.confman.ui.common.component.EditableComponent;
import net.ostis.confman.ui.common.component.StringDataConverter;
import net.ostis.confman.ui.common.component.TextField;
import net.ostis.confman.ui.common.component.ValueBinder;
import net.ostis.confman.ui.common.component.ValueComboBinder;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ReportEditorPart {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum ReportCaptions implements Localizable {
        TITLE("reportTitle"),
        MAIN_AUTHOR("mainAuthor"),
        YOUNG_REPORT("youngScientistReport"),
        ACCEPT_REPORT("acceptReport"),
        CANCEL_REPORT("cancelReport"),
        NUMBER_OF_PAGES("numberOfPages"),
        SAVE("save");

        private String rk;

        private ReportCaptions(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private final Map<ReportCaptions, EditableComponent<?>> editFields;

    @Inject
    private ESelectionService                               selectionService;

    @Inject
    private IEventBroker                                    eventBroker;

    public ReportEditorPart() {

        super();
        this.editFields = new EnumMap<>(ReportCaptions.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (selection instanceof Report) {
                    final Report report = (Report) selection;
                    onReportEvent(report, parent);
                }
            }
        });
        buildLayout(parent);
    }

    protected void onReportEvent(final Report report, final Composite parent) {

        applyValueBindings(report);
        for (final ReportCaptions field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }
    }

    private void applyValueBindings(final Report report) {

        this.editFields.get(ReportCaptions.TITLE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        report.setTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return report.getTitle();
                    }
                });

        this.editFields.get(ReportCaptions.NUMBER_OF_PAGES).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        report.setNumberOfPages((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return report.getNumberOfPages();
                    }
                });

        this.editFields.get(ReportCaptions.MAIN_AUTHOR).setValueComboBinder(
                new ValueComboBinder() {

                    @Override
                    public void setValues(final Object value) {

                        report.setAllAuthors((List<Participant>) value);
                    }

                    @Override
                    public void setCurrentValue(final Object value) {

                        report.setMainAuthor((Participant) value);
                    }

                    @Override
                    public Object getValues() {

                        return report.getAllAuthors();
                    }

                    @Override
                    public Object getCurrentValue() {

                        return report.getMainAuthor();
                    }
                });
        this.editFields.get(ReportCaptions.YOUNG_REPORT).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        report.setYoungScientistReport((Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return report.isYoungScientistReport();
                    }
                });
        this.editFields.get(ReportCaptions.ACCEPT_REPORT).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        report.setReportAccepted((Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return report.isReportAccepted();
                    }
                });
        this.editFields.get(ReportCaptions.CANCEL_REPORT).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        report.setReportCanceled((Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return report.isReportCanceled();
                    }
                });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));
        this.editFields.put(ReportCaptions.TITLE,
                new TextField(parent, util.translate(ReportCaptions.TITLE))
                        .setDataConverter(new StringDataConverter()));
        this.editFields.put(ReportCaptions.NUMBER_OF_PAGES, new TextField(
                parent, util.translate(ReportCaptions.NUMBER_OF_PAGES))
                .setDataConverter(new StringDataConverter()));
        this.editFields.put(ReportCaptions.MAIN_AUTHOR, new ComboBoxField(
                parent, util.translate(ReportCaptions.MAIN_AUTHOR),
                new String[0]).setDataConverter(new StringDataConverter()));
        this.editFields.put(ReportCaptions.YOUNG_REPORT, new CheckBoxField(
                parent, util.translate(ReportCaptions.YOUNG_REPORT)));
        this.editFields.put(ReportCaptions.ACCEPT_REPORT, new CheckBoxField(
                parent, util.translate(ReportCaptions.ACCEPT_REPORT)));
        this.editFields.put(ReportCaptions.CANCEL_REPORT, new CheckBoxField(
                parent, util.translate(ReportCaptions.CANCEL_REPORT)));
        final Button button = new Button(parent, SWT.PUSH);
        button.setText(util.translate(ReportCaptions.SAVE));
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

    private void onUpdate() {

        for (final ReportCaptions field : this.editFields.keySet()) {
            this.editFields.get(field).apply();
        }
        this.eventBroker.post(ConferenceTopics.CONF_TREE_UPDATE, null);
        this.eventBroker.post(ConferenceTopics.CONF_UPDATE, null);
        this.eventBroker.post(ConferenceTopics.TABLE_UPDATE, null);
    }
}
