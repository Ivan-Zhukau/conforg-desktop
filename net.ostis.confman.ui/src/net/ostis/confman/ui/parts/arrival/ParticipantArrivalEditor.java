package net.ostis.confman.ui.parts.arrival;

import java.util.EnumMap;
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
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ParticipantArrivalEditor {
    
    private static final int LAYOUT_COL_COUNT = 1;

    private enum Captions implements Localizable {
        SAVE("save"),
        COUNTRY("country"),
        CITY("city"),
        STREET("street"),
        HOUSE("houseNumber"),
        ;

        private String rk;

        private Captions(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    @Inject
    private ESelectionService selectionService;

    @Inject
    private EPartService      partService;

    @Inject
    private IEventBroker      eventBroker;

    private final Map<Captions, EditableComponent<?>> editFields;
    
    public ParticipantArrivalEditor() {

        super();
        this.editFields = new EnumMap<>(Captions.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (selection instanceof Participant) {
                    final Participant participant = (Participant) selection;
                    onParticipantEvent(participant, parent);
                }
            }
        });
        buildLayout(parent);
    }

    private void onParticipantEvent(Participant participant,
            Composite parent) {

        applyValueBindings(participant);
        for (final Captions field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }
    }

    private void applyValueBindings(final Participant participant) {

        this.editFields.get(Captions.COUNTRY).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getArrival().getResidencePlace()
                                .setCountry((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getArrival().getResidencePlace()
                                .getCountry();
                    }
                });
        this.editFields.get(Captions.CITY).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getArrival().getResidencePlace()
                                .setCity((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getArrival().getResidencePlace()
                                .getCity();
                    }
                });
        this.editFields.get(Captions.STREET).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getArrival().getResidencePlace()
                                .setStreet((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getArrival().getResidencePlace()
                                .getStreet();
                    }
                });
        this.editFields.get(Captions.HOUSE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getArrival().getResidencePlace()
                                .setHouseNumber((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getArrival().getResidencePlace()
                                .getHouseNumber();
                    }
                });
        /*this.editFields.get(Captions.PLENARY_REPORT).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        report.setPlenaryReport((Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return report.isPlenaryReport();
                    }
                });*/
    }

    private void buildLayout(Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));
        this.editFields.put(Captions.COUNTRY, new TextField(parent,
                util.translate(Captions.COUNTRY))
                .setDataConverter(new StringDataConverter()));
        this.editFields.put(Captions.CITY,
                new TextField(parent, util.translate(Captions.CITY))
                        .setDataConverter(new StringDataConverter()));
        this.editFields.put(Captions.STREET,
                new TextField(parent, util.translate(Captions.STREET))
                        .setDataConverter(new StringDataConverter()));
        this.editFields.put(Captions.HOUSE,
                new TextField(parent, util.translate(Captions.HOUSE))
                        .setDataConverter(new StringDataConverter()));
        /*this.editFields.put(
                Captions.PARTICIPATION_IN_CONTEST,
                new CheckBoxField(parent, util
                        .translate(Captions.PARTICIPATION_IN_CONTEST)));*/
        final Button button = new Button(parent, SWT.PUSH);
        button.setText(util.translate(Captions.SAVE));
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

        for (final Captions field : this.editFields.keySet()) {
            this.editFields.get(field).apply();
        }
        this.eventBroker.post(ConferenceTopics.PARTICIPANT_UPDATED, null);
    }

}
