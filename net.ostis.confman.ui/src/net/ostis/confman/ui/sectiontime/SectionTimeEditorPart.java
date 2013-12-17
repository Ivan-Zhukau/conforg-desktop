package net.ostis.confman.ui.sectiontime;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.AddressDataConverter;
import net.ostis.confman.ui.common.component.DateChooserField;
import net.ostis.confman.ui.common.component.DateDataConverter;
import net.ostis.confman.ui.common.component.EditableComponent;
import net.ostis.confman.ui.common.component.StringDataConverter;
import net.ostis.confman.ui.common.component.TextField;
import net.ostis.confman.ui.common.component.ValueBinder;
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

public class SectionTimeEditorPart {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum TextFields implements Localizable {
        TIME_CHAIRMAN("sectionTimeChairman"),
        TIME_REPORT("sectionTimeReport"),
        TIME_PLENARY_REPORT("sectionTimePlenaryReport"),
        TIME_SMALL_BREAK("sectionTimeSmallBreak");

        private String rk;

        private TextFields(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum DateChooserFields implements Localizable {
        START("sectionTimeStart"),;

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

    private final Map<TextFields, EditableComponent<TextField>>         editFields;

    private final Map<DateChooserFields, EditableComponent<DateChooserField>> dateChooserFields;

    @Inject
    private ESelectionService                                                 selectionService;

    @Inject
    private IEventBroker                                                      eventBroker;

    public SectionTimeEditorPart() {

        super();
        this.editFields = new EnumMap<>(TextFields.class);
        this.dateChooserFields = new EnumMap<>(DateChooserFields.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (selection instanceof Conference) {
                    final Conference conf = (Conference) selection;
                    onConferenceEvent(conf);
                }
            }
        });
        buildLayout(parent);
    }

    protected void onConferenceEvent(final Conference conf) {

        applyValueBindings(conf);
        for (final TextFields field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }

        for (final DateChooserFields field : this.dateChooserFields.keySet()) {
            this.dateChooserFields.get(field).activate();
        }
    }

    private void applyValueBindings(final Conference conf) {
        
        this.dateChooserFields.get(DateChooserFields.START)
        .setValueBinder(new ValueBinder() {

            @Override
            public void setValue(final Object value) {

                conf.setStartDate((Date) value);
            }

            @Override
            public Object getValue() {

                return conf.getStartDate();
            }
        });

        this.editFields.get(TextFields.TIME_CHAIRMAN).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        conf.setTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return conf.getTitle();
                    }
                });
        this.editFields.get(TextFields.TIME_REPORT).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        conf.setConferenceVenue((Address) value);
                    }

                    @Override
                    public Object getValue() {

                        return conf.getConferenceVenue();
                    }
                });
        this.editFields.get(TextFields.TIME_PLENARY_REPORT).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        conf.setConferenceVenue((Address) value);
                    }

                    @Override
                    public Object getValue() {

                        return conf.getConferenceVenue();
                    }
                });
        this.editFields.get(TextFields.TIME_SMALL_BREAK).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        conf.setConferenceVenue((Address) value);
                    }

                    @Override
                    public Object getValue() {

                        return conf.getConferenceVenue();
                    }
                });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));
        final DateDataConverter dateConverter = new DateDataConverter();
        
        this.dateChooserFields.put(
                DateChooserFields.START,
                new DateChooserField(parent, util
                        .translate(DateChooserFields.START))
                        .setDataConverter(dateConverter));
        
        this.editFields.put(TextFields.TIME_CHAIRMAN,
                new TextField(parent, util.translate(TextFields.TIME_CHAIRMAN))
                        .setDataConverter(new StringDataConverter()));
        this.editFields.put(TextFields.TIME_REPORT, new TextField(parent,
                util.translate(TextFields.TIME_REPORT))
                .setDataConverter(new AddressDataConverter()));
        this.editFields.put(TextFields.TIME_PLENARY_REPORT, new TextField(parent,
                util.translate(TextFields.TIME_PLENARY_REPORT))
                .setDataConverter(new AddressDataConverter()));
        this.editFields.put(TextFields.TIME_SMALL_BREAK, new TextField(parent,
                util.translate(TextFields.TIME_SMALL_BREAK))
                .setDataConverter(new AddressDataConverter()));
        
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

        for (final TextFields field : this.editFields.keySet()) {
            this.editFields.get(field).apply();
        }
        this.eventBroker.post(ConferenceTopics.CONF_UPDATE, null);
    }
}
