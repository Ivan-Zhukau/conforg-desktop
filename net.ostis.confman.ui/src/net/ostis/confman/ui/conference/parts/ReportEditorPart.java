package net.ostis.confman.ui.conference.parts;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.ComboBoxField;
import net.ostis.confman.ui.common.component.EditableComponent;
import net.ostis.confman.ui.common.component.StringDataConverter;
import net.ostis.confman.ui.common.component.TextField;
import net.ostis.confman.ui.common.component.ToStringArrayConverter;
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

    private enum ReportFields implements Localizable {
        TITLE("reportTitle");

        private String rk;

        private ReportFields(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum ReportCombos implements Localizable {
        MAIN_AUTHOR("mainAuthor");

        private String rk;

        private ReportCombos(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum Buttons implements Localizable {
        SAVE("save");

        private String rk;

        private Buttons(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }

    }

    private final Map<ReportFields, EditableComponent<?>> editFields;

    private final Map<ReportCombos, EditableComponent<?>> combos;

    private String[]                                      authorsArray;

    @Inject
    private ESelectionService                             selectionService;

    @Inject
    private IEventBroker                                  eventBroker;

    public ReportEditorPart() {

        super();
        this.editFields = new EnumMap<>(ReportFields.class);
        this.combos = new EnumMap<>(ReportCombos.class);
        this.authorsArray = new String[0];
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

        this.authorsArray = new ToStringArrayConverter().convert(report
                .getAllAuthors());

        applyValueBindings(report);
        for (final ReportFields field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }

        for (final ReportCombos field : this.combos.keySet()) {
            this.combos.get(field).activate();
        }
    }

    private void applyValueBindings(final Report report) {

        this.editFields.get(ReportFields.TITLE).setValueBinder(
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

        this.combos.get(ReportCombos.MAIN_AUTHOR).setValueComboBinder(
                new ValueComboBinder() {
                    
                    @Override
                    public void setValues(Object value) {
                    
                        report.setAllAuthors((List<Participant>) value);
                        
                    }
                    
                    @Override
                    public void setCurrentValue(Object value) {
                    
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
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));

        this.editFields.put(ReportFields.TITLE,
                new TextField(parent, util.translate(ReportFields.TITLE))
                        .setDataConverter(new StringDataConverter()));

        this.combos.put(ReportCombos.MAIN_AUTHOR, new ComboBoxField(parent,
                util.translate(ReportCombos.MAIN_AUTHOR), new String[0])
                .setDataConverter(new StringDataConverter()));

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

    private void onUpdate() {

        for (final ReportFields field : this.editFields.keySet()) {
            this.editFields.get(field).apply();

        }
        for (final ReportCombos field : this.combos.keySet()) {
            this.combos.get(field).apply();

        }
        // TODO: add getter (?) for ValueBinder in TextField and create\
        // Conference obj with updated fiels.
        this.eventBroker.post(ConferenceTopics.CONF_UPDATE, null);
    }
}
