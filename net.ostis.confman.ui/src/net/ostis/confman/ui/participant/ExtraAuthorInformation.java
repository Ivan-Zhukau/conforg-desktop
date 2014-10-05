package net.ostis.confman.ui.participant;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ParticipantService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.ParticipantArrival;
import net.ostis.confman.services.common.model.ParticipantRole;
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
import org.eclipse.e4.ui.workbench.UIEvents.Item;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ExtraAuthorInformation {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum ComboFields implements Localizable {
        DEFAULT("defaultComboItem"),
        REPORT("reportParticipationForm"),
        PUBLICATION("publicationParticipationForm");

        private String rk;

        private ComboFields(final String rk) {

            this.rk = rk;
        }

        public String getValue(){
            return this.rk;
        }
        
        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum TableFields implements Localizable {
        PATICIPATION_FORM("participationForm"),
        COUNTRY("country"),
        CITY("city"),
        STREET("street"),
        HOUSE_NUMBER("houseNumber");

        private String rk;

        private TableFields(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum Buttons implements Localizable {
        SAVE("save"),
        PROGRAMM_COMMITTEE_MEMBER("programCommitteeMember"),
        MEETING("meeting"),
        HOUSING("housing"),
        EXIBITION_STAND("exhibitionStand");

        private String rk;

        private Buttons(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }

    }

    private final Map<TableFields, EditableComponent<?>> editFields;

    private final Map<Buttons, EditableComponent<CheckBoxField>> checkFields;

    @Inject
    private ESelectionService                                    selectionService;

    @Inject
    private IEventBroker                                         eventBroker;

    public ExtraAuthorInformation() {

        super();
        this.editFields = new EnumMap<>(TableFields.class);
        this.checkFields = new EnumMap<>(Buttons.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (!(selection instanceof Participant)) {
                    return;
                }
                final Participant participant = (Participant) selection;
                onNewSelection(participant);
            }
        });
        buildLayout(parent);
    }

    private void onNewSelection(final Participant participant) {

        applyValueBindings(participant);
        for (final TableFields field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }
        for (final Buttons button : this.checkFields.keySet()) {
            this.checkFields.get(button).activate();
        }
    }

    private void applyValueBindings(final Participant participant) {

        final ParticipantArrival participantArrival = participant.getArrival();
        final ParticipantRole participantRole = participant.getRole();
        final Address address = participantArrival.getResidencePlace();
        applyParticipationFormBinder(participant);
        applyCityBinder(address);
        applyCountryBinder(address);
        applyHouseBinder(address);
        applyStreetBinder(address);
        applyHousingBinder(participantArrival);
        applyMeetingBinder(participantArrival);
        applyCommitteeBinder(participantRole);
    }

    private void applyCommitteeBinder(final ParticipantRole role) {

        this.checkFields.get(Buttons.PROGRAMM_COMMITTEE_MEMBER).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        role.setProgramCommitteeMember((Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return role.getProgramCommitteeMember();
                    }
                });
    }

    private void applyMeetingBinder(final ParticipantArrival arrival) {

        this.checkFields.get(Buttons.MEETING).setValueBinder(new ValueBinder() {

            @Override
            public void setValue(final Object value) {

                arrival.setMeeting((Boolean) value);
            }

            @Override
            public Object getValue() {

                return arrival.getMeeting();
            }
        });
    }

    private void applyHousingBinder(final ParticipantArrival arrival) {

        this.checkFields.get(Buttons.HOUSING).setValueBinder(new ValueBinder() {

            @Override
            public void setValue(final Object value) {

                arrival.setHousing((Boolean) value);
            }

            @Override
            public Object getValue() {

                return arrival.getHousing();
            }
        });
    }

    private void applyCityBinder(final Address address) {

        this.editFields.get(TableFields.CITY).setValueBinder(new ValueBinder() {

            @Override
            public void setValue(final Object value) {

                address.setCity((String) value);
            }

            @Override
            public Object getValue() {

                return address.getCity();
            }
        });
    }

    private void applyCountryBinder(final Address address) {

        this.editFields.get(TableFields.COUNTRY).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        address.setCountry((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return address.getCountry();
                    }
                });
    }

    private void applyStreetBinder(final Address address) {

        this.editFields.get(TableFields.STREET).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        address.setStreet((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return address.getStreet();
                    }
                });
    }

    private void applyHouseBinder(final Address address) {

        this.editFields.get(TableFields.HOUSE_NUMBER).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        address.setHouseNumber((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return address.getHouseNumber();
                    }
                });
    }

    private void applyParticipationFormBinder(
            final Participant participant) {

        this.editFields.get(TableFields.PATICIPATION_FORM).setValueComboBinder(
                new ValueComboBinder() {
                    final LocalizationUtil util = LocalizationUtil.getInstance();

                    @Override
                    public void setValues(final Object value) {
                    }

                    @Override
                    public void setCurrentValue(final Object value) {
                        if(!util.translate(ComboFields.DEFAULT).equals((String) value)) {
                            participant.getRole().setParticipationForm((String) value);
                        } else {
                            participant.getRole().setParticipationForm(null);
                        }
                    }

                    @Override
                    public Object getValues() {
                        
                        List<String> list = new ArrayList<String>();
                        for(ComboFields item: ComboFields.values()) {
                            list.add(util.translate(item));
                        }
                        return list;
                    }

                    @Override
                    public Object getCurrentValue() {
                        return participant.getRole().getParticipationForm(); 
                    }
                });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));

        final StringDataConverter stringConverter = new StringDataConverter();
        
        this.editFields.put(TableFields.PATICIPATION_FORM, new ComboBoxField(
                parent, util.translate(TableFields.PATICIPATION_FORM),
                new String[0]).setDataConverter(new StringDataConverter()));        
        this.editFields.put(TableFields.CITY,
                new TextField(parent, util.translate(TableFields.CITY))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.COUNTRY,
                new TextField(parent, util.translate(TableFields.COUNTRY))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.HOUSE_NUMBER, new TextField(parent,
                util.translate(TableFields.HOUSE_NUMBER))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.STREET,
                new TextField(parent, util.translate(TableFields.STREET))
                        .setDataConverter(stringConverter));
        this.checkFields.put(Buttons.HOUSING,
                new CheckBoxField(parent, util.translate(Buttons.HOUSING)));
        this.checkFields.put(Buttons.MEETING,
                new CheckBoxField(parent, util.translate(Buttons.MEETING)));
        this.checkFields.put(
                Buttons.PROGRAMM_COMMITTEE_MEMBER,
                new CheckBoxField(parent, util
                        .translate(Buttons.PROGRAMM_COMMITTEE_MEMBER)));
        final Button button = new Button(parent, SWT.PUSH);
        button.setText(util.translate(Buttons.SAVE));
        button.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                onSave();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                onSave();
            }
        });
    }

    private void onSave() {

        for (final TableFields field : this.editFields.keySet()) {
            this.editFields.get(field).apply();
        }
        for (final Buttons button : this.checkFields.keySet()) {
            this.checkFields.get(button).apply();
        }
        this.eventBroker.post(ConferenceTopics.TABLE_UPDATE, null);
        final ParticipantService participantService = (ParticipantService) ServiceLocator
                .getInstance().getService(ParticipantService.class);
        participantService.fireData();
    }

}
