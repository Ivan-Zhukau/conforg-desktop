package net.ostis.confman.ui.participant;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.common.model.AcademicInformation;
import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.ContactInformation;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.WorkplaceInformation;
import net.ostis.confman.ui.common.Localizable;
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

public class TableEditorPart {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum TableFields implements Localizable {
        INFORMATION_OF_AUTHOR_OF_ARTICLE("informationOfAuthorArticle"),
        SURNAME("surname"),
        NAME("name"),
        PATRONYMIC("patronymic"),
        ACADEMIC_DEGREE("academicDegree"),
        ACADEMIC_TITLE("academicTitle"),
        COUNTRY("country"),
        CITY("city"),
        E_MAIL("eMail"),
        CONTACT_PHONE_NUMBER("contactPhoneNumber"),
        PLACE_OF_WORK("placeOfWork"),
        AFFLICATION("afflication"),
        POSITION("position");

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

    private final Map<TableFields, EditableComponent<TextField>> editFields;

    @Inject
    private ESelectionService                                    selectionService;

    @Inject
    private IEventBroker                                         eventBroker;

    public TableEditorPart() {

        super();
        this.editFields = new EnumMap<>(TableFields.class);
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
    }

    private void applyValueBindings(final Participant participant) {

        final Person person;
        if(participant.getPerson() == null){
            person = new Person();
        }
        else{
            person = participant.getPerson();
        }
        final Address address;
        if(person.getResidence() == null){
            address = new Address();
        }
        else{
            address = person.getResidence();
        }
        final WorkplaceInformation workplaceInformation;
        if(person.getWorkplace() == null){
            workplaceInformation = new WorkplaceInformation();
        }
        else{
            workplaceInformation = person.getWorkplace();
        }
        final ContactInformation contactInfo;
        if(person.getContacts() == null){
            contactInfo = new ContactInformation();
        }
        else{
            contactInfo = person.getContacts();
        }
        final AcademicInformation academicInfo;
        if(person.getDegree() == null){
            academicInfo = new AcademicInformation();
        }
        else{
            academicInfo = person.getDegree();
        }
        applySurnameBinder(person);
        applyNameBinder(person);
        applyPatronymicBinder(person);
        applyAcademicDegreeBinder(academicInfo);
        applyAcademicTitleBinder(academicInfo);
        applyCountryBinder(address);
        applySityBinder(address);
        applyEMAILBinder(contactInfo);
        applyNumberBinder(contactInfo);
        applyAfflicationBinder(workplaceInformation);
        applyPositionBinder(workplaceInformation);
    }

    private void applySurnameBinder(final Person personInfo) {

        this.editFields.get(TableFields.SURNAME).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        personInfo.setSurname((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return personInfo.getSurname();
                    }
                });
    }

    private void applyNameBinder(final Person personInfo) {

        this.editFields.get(TableFields.NAME).setValueBinder(new ValueBinder() {

            @Override
            public void setValue(final Object value) {

                personInfo.setFirstName((String) value);
            }

            @Override
            public Object getValue() {

                return personInfo.getFirstName();
            }
        });
    }

    private void applyPatronymicBinder(final Person personInfo) {

        this.editFields.get(TableFields.PATRONYMIC).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        personInfo.setPatronymic((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return personInfo.getPatronymic();
                    }
                });
    }

    private void applyAcademicDegreeBinder(final AcademicInformation personInfo) {

        this.editFields.get(TableFields.ACADEMIC_DEGREE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        personInfo.setDegree((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return personInfo.getDegree();
                    }
                });
    }

    private void applyAcademicTitleBinder(final AcademicInformation personInfo) {

        this.editFields.get(TableFields.ACADEMIC_TITLE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        personInfo.setTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return personInfo.getTitle();
                    }
                });
    }

    private void applyAfflicationBinder(final WorkplaceInformation workInfo) {

        this.editFields.get(TableFields.AFFLICATION).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        workInfo.setAffliation((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return workInfo.getAffliation();
                    }
                });
    }

    private void applyPositionBinder(final WorkplaceInformation workInfo) {

        this.editFields.get(TableFields.POSITION).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        workInfo.setPosition((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return workInfo.getPosition();
                    }
                });
    }

    private void applyEMAILBinder(final ContactInformation contactInfo) {

        this.editFields.get(TableFields.E_MAIL).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        contactInfo.seteMail((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return contactInfo.geteMail();
                    }
                });
    }

    private void applyNumberBinder(final ContactInformation contactInfo) {

        this.editFields.get(TableFields.CONTACT_PHONE_NUMBER).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        contactInfo.setContactPhoneNumber((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return contactInfo.getContactPhoneNumber();
                    }
                });
    }

    private void applyCountryBinder(final Address contactInfo) {

        this.editFields.get(TableFields.COUNTRY).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        contactInfo.setCountry((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return contactInfo.getCountry();
                    }
                });
    }

    private void applySityBinder(final Address contactInfo) {

        this.editFields.get(TableFields.CITY).setValueBinder(new ValueBinder() {

            @Override
            public void setValue(final Object value) {

                contactInfo.setCity((String) value);
            }

            @Override
            public Object getValue() {

                return contactInfo.getCity();
            }
        });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));

        final StringDataConverter stringConverter = new StringDataConverter();
        this.editFields.put(TableFields.SURNAME,
                new TextField(parent, util.translate(TableFields.SURNAME))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.NAME,
                new TextField(parent, util.translate(TableFields.NAME))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.PATRONYMIC,
                new TextField(parent, util.translate(TableFields.PATRONYMIC))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.ACADEMIC_DEGREE, new TextField(parent,
                util.translate(TableFields.ACADEMIC_DEGREE))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.ACADEMIC_TITLE, new TextField(parent,
                util.translate(TableFields.ACADEMIC_TITLE))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.COUNTRY,
                new TextField(parent, util.translate(TableFields.COUNTRY))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.CITY,
                new TextField(parent, util.translate(TableFields.CITY))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.E_MAIL,
                new TextField(parent, util.translate(TableFields.E_MAIL))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.CONTACT_PHONE_NUMBER, new TextField(
                parent, util.translate(TableFields.CONTACT_PHONE_NUMBER))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.AFFLICATION,
                new TextField(parent, util.translate(TableFields.AFFLICATION))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.POSITION,
                new TextField(parent, util.translate(TableFields.POSITION))
                        .setDataConverter(stringConverter));
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
        this.eventBroker.post(ConferenceTopics.CONF_UPDATE, null); // TODO
    }
}
