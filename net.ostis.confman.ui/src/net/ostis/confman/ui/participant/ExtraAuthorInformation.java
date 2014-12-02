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
import org.eclipse.swt.widgets.Group;

public class ExtraAuthorInformation {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum GroupButtons implements Localizable {
        PARTICIPATION_IN_CONFERENCE("participationInConference"),
        ARRIVAL("arrival");

        private String rk;

        private GroupButtons(final String rk) {

            this.rk = rk;
        }

        public String getValue() {

            return this.rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum ParticipationFormFields implements Localizable {
        DEFAULT("defaultComboItem"),
        REPORT("reportParticipationForm"),
        PUBLICATION("publicationParticipationForm");

        private String rk;

        private ParticipationFormFields(final String rk) {

            this.rk = rk;
        }

        public String getValue() {

            return this.rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }
    
    private enum ParticipationInConferenceCompetitionsFields implements Localizable {
        DEFAULT("defaultComboItem"),
        COMPETITION_RESEARCH_OF_YOUNG_SCIENTISTS("competitionResearchOfYoungScientists"),
        cOMPETITION_SOFTWARE_YOUNG_SCIENTISTS("competitionSoftwareYoungScientists");

        private String rk;

        private ParticipationInConferenceCompetitionsFields(final String rk) {

            this.rk = rk;
        }

        public String getValue() {

            return this.rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum TextFields implements Localizable {
        PATICIPATION_FORM("participationForm"),
        PATICIPATION_CATEGORY("participationCategory"),
        PATICIPATION_IN_CONFERENCE_COMPETITIONS("participationInConferenceCompetitions"),
        COUNTRY("country"),
        CITY("city"),
        STREET("street"),
        HOUSE_NUMBER("houseNumber"),
        EXHIBITION_PRESENTATION_OF_REPORTS("exhibitionStand"),
        TOUR_OF_THE_CITY_OF_MINSK("tourOfTheCityOfMinsk"),
        CULTURAL_PROGRAM("culturalProgram"),
        EVENING_MEETING_PC("eveningMeetingPC"),
        HOSTEL_RESERVATION("hotelReservation");

        private String rk;

        private TextFields(final String rk) {

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

    private final Map<TextFields, EditableComponent<?>> editFields;

    @Inject
    private ESelectionService                           selectionService;

    @Inject
    private IEventBroker                                eventBroker;

    public ExtraAuthorInformation() {

        super();
        this.editFields = new EnumMap<>(TextFields.class);
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
        for (final TextFields field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }
    }

    private void applyValueBindings(final Participant participant) {

        final ParticipantArrival participantArrival = participant.getArrival();
        final Address address = participantArrival.getResidencePlace();
        applyCheckBoxes(participant);
        applyParticipationFormBinder(participant);
        applyParticipationCategoryBinder(participant);
        //applyParticipationInConferenceCompetitionsBinder(participant);
        applyCityBinder(address);
        applyCountryBinder(address);
        applyHouseBinder(address);
        applyStreetBinder(address);
    }

    private void applyParticipationCategoryBinder(final Participant participant) {

        this.editFields.get(TextFields.PATICIPATION_CATEGORY)
                .setValueComboBinder(new ValueComboBinder() {

                    final LocalizationUtil util = LocalizationUtil
                                                        .getInstance();

                    @Override
                    public void setValues(final Object value) {

                    }

                    @Override
                    public void setCurrentValue(final Object value) {

                        if (!this.util.translate(ParticipationCategory.DEFAULT)
                                .equals(value)) {
                            participant.getRole().setParticipationCategory(
                                    (String) value);
                        } else {
                            participant.getRole()
                                    .setParticipationCategory(null);
                        }
                    }

                    @Override
                    public Object getValues() {

                        final List<String> list = new ArrayList<String>();
                        for (final ParticipationCategory item : ParticipationCategory
                                .values()) {
                            list.add(this.util.translate(item));
                        }
                        return list;
                    }

                    @Override
                    public Object getCurrentValue() {

                        return participant.getRole().getParticipationCategory();
                    }
                });
    }

    private void applyCheckBoxes(final Participant participant) {

        this.editFields.get(TextFields.EXHIBITION_PRESENTATION_OF_REPORTS)
                .setValueBinder(new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getParticipationInConference()
                                .setExhibitionPresentationOfeports(
                                        (Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getParticipationInConference()
                                .getExhibitionPresentationOfeports();
                    }
                });

        this.editFields.get(TextFields.TOUR_OF_THE_CITY_OF_MINSK)
                .setValueBinder(new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getParticipationInConference()
                                .setTourOfTheCityOfMinsk((Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getParticipationInConference()
                                .getTourOfTheCityOfMinsk();
                    }
                });

        this.editFields.get(TextFields.CULTURAL_PROGRAM).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getParticipationInConference()
                                .setCulturalProgram((Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getParticipationInConference()
                                .getCulturalProgram();
                    }
                });

        this.editFields.get(TextFields.EVENING_MEETING_PC).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getParticipationInConference()
                                .setEveningMeetingPC((Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getParticipationInConference()
                                .getEveningMeetingPC();
                    }
                });

        this.editFields.get(TextFields.HOSTEL_RESERVATION).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participant.getArrival().setHostelReservation(
                                (Boolean) value);
                    }

                    @Override
                    public Object getValue() {

                        return participant.getArrival().getHostelReservation();
                    }
                });

    }

    private void applyCityBinder(final Address address) {

        this.editFields.get(TextFields.CITY).setValueBinder(new ValueBinder() {

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

        this.editFields.get(TextFields.COUNTRY).setValueBinder(
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

        this.editFields.get(TextFields.STREET).setValueBinder(
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

        this.editFields.get(TextFields.HOUSE_NUMBER).setValueBinder(
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

    private void applyParticipationFormBinder(final Participant participant) {

        this.editFields.get(TextFields.PATICIPATION_FORM).setValueComboBinder(
                new ValueComboBinder() {

                    final LocalizationUtil util = LocalizationUtil
                                                        .getInstance();

                    @Override
                    public void setValues(final Object value) {

                    }

                    @Override
                    public void setCurrentValue(final Object value) {

                        if (!this.util.translate(
                                ParticipationFormFields.DEFAULT).equals(value)) {
                            participant.getRole().setParticipationForm(
                                    (String) value);
                        } else {
                            participant.getRole().setParticipationForm(null);
                        }
                    }

                    @Override
                    public Object getValues() {

                        final List<String> list = new ArrayList<String>();
                        for (final ParticipationFormFields item : ParticipationFormFields
                                .values()) {
                            list.add(this.util.translate(item));
                        }
                        return list;
                    }

                    @Override
                    public Object getCurrentValue() {

                        return participant.getRole().getParticipationForm();
                    }
                });
    }
    
    private void applyParticipationInConferenceCompetitionsBinder(final Participant participant) {

        this.editFields.get(TextFields.PATICIPATION_IN_CONFERENCE_COMPETITIONS).setValueComboBinder(
                new ValueComboBinder() {

                    final LocalizationUtil util = LocalizationUtil
                                                        .getInstance();

                    @Override
                    public void setValues(final Object value) {

                    }

                    @Override
                    public void setCurrentValue(final Object value) {

                        if (!this.util.translate(
                                ParticipationInConferenceCompetitionsFields.DEFAULT).equals(value)) {
                            participant.getRole().setParticipationForm(
                                    (String) value);
                        } else {
                            participant.getRole().setParticipationForm(null);
                        }
                    }

                    @Override
                    public Object getValues() {

                        final List<String> list = new ArrayList<String>();
                        for (final ParticipationInConferenceCompetitionsFields item : ParticipationInConferenceCompetitionsFields
                                .values()) {
                            list.add(this.util.translate(item));
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

        this.editFields.put(TextFields.PATICIPATION_FORM, new ComboBoxField(
                parent, util.translate(TextFields.PATICIPATION_FORM),
                new String[0], null).setDataConverter(new StringDataConverter()));
        this.editFields.put(
                TextFields.PATICIPATION_CATEGORY,
                new ComboBoxField(parent, util
                        .translate(TextFields.PATICIPATION_CATEGORY),
                        new String[0], null)
                        .setDataConverter(new StringDataConverter()));
        /**this.editFields.put(
                TextFields.PATICIPATION_IN_CONFERENCE_COMPETITIONS,
                new ComboBoxField(parent, util
                        .translate(TextFields.PATICIPATION_IN_CONFERENCE_COMPETITIONS),
                        new String[0], null)
                        .setDataConverter(new StringDataConverter()));*/
        this.editFields.put(TextFields.CITY,
                new TextField(parent, util.translate(TextFields.CITY))
                        .setDataConverter(stringConverter));
        this.editFields.put(TextFields.COUNTRY,
                new TextField(parent, util.translate(TextFields.COUNTRY))
                        .setDataConverter(stringConverter));
        this.editFields.put(TextFields.HOUSE_NUMBER,
                new TextField(parent, util.translate(TextFields.HOUSE_NUMBER))
                        .setDataConverter(stringConverter));
        this.editFields.put(TextFields.STREET,
                new TextField(parent, util.translate(TextFields.STREET))
                        .setDataConverter(stringConverter));

        final Group group1 = new Group(parent, SWT.READ_ONLY);
        group1.setText(util.translate(GroupButtons.PARTICIPATION_IN_CONFERENCE));

        final CheckBoxField boxField1 = new CheckBoxField(group1,
                util.translate(TextFields.EXHIBITION_PRESENTATION_OF_REPORTS));
        boxField1.setLocation(5, 20);
        boxField1.pack();

        final CheckBoxField boxField2 = new CheckBoxField(group1,
                util.translate(TextFields.TOUR_OF_THE_CITY_OF_MINSK));
        boxField2.setLocation(5, 40);
        boxField2.pack();

        final CheckBoxField boxField3 = new CheckBoxField(group1,
                util.translate(TextFields.CULTURAL_PROGRAM));
        boxField3.setLocation(5, 60);
        boxField3.pack();

        final CheckBoxField boxField4 = new CheckBoxField(group1,
                util.translate(TextFields.EVENING_MEETING_PC));
        boxField4.setLocation(5, 80);
        boxField4.pack();

        this.editFields.put(TextFields.EXHIBITION_PRESENTATION_OF_REPORTS,
                boxField1);
        this.editFields.put(TextFields.TOUR_OF_THE_CITY_OF_MINSK, boxField2);
        this.editFields.put(TextFields.CULTURAL_PROGRAM, boxField3);
        this.editFields.put(TextFields.EVENING_MEETING_PC, boxField4);

        group1.pack();

        final Group group2 = new Group(parent, SWT.READ_ONLY);
        group2.setText(util.translate(GroupButtons.ARRIVAL));

        final CheckBoxField boxField5 = new CheckBoxField(group2,
                util.translate(TextFields.HOSTEL_RESERVATION));
        boxField5.setLocation(5, 20);
        boxField5.pack();

        this.editFields.put(TextFields.HOSTEL_RESERVATION, boxField5);

        group2.pack();

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

        for (final TextFields field : this.editFields.keySet()) {
            this.editFields.get(field).apply();
        }
        this.eventBroker.post(ConferenceTopics.TABLE_UPDATE, null);
        final ParticipantService participantService = (ParticipantService) ServiceLocator
                .getInstance().getService(ParticipantService.class);
        participantService.fireData();
    }

}
