package net.ostis.confman.ui.participant;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.common.model.AcademicInformation;
import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.ContactInformation;
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
        INFORMATION_OF_AUTHOR_OF_ARTICLE("Информация о всех Авторах статьи:"),
        SURNAME("Фамилия:"),
        NAME("Имя:"),
        PATRONYMIC("Отчество:"),
        ACADEMIC_DEGREE("Учёная степень:"),
        ACADEMIC_TITLE("Учёное звание:"),
        COUNTRY("Страна:"),
        SITY("Город:"),
        E_MAIL("E-mail для связи:"),
        CONTACT_PHONE_NUMBER("Контактный телефон:"),
        PLACE_OF_WORK("Место работы:"),
        AFFLICATION("Организация:"),
        POSITION("Должность:");

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
        SAVE("Save the changes");

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

        Person person = participant.getPerson(); 
        Address address = person.getResidence();
        WorkplaceInformation workplaceInformation = person.getWorkplace();
        ContactInformation contactInfo = person.getContacts();
        AcademicInformation academicInfo = person.getDegree();
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

        this.editFields.get(TableFields.SITY).setValueBinder(new ValueBinder() {

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
        
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));
       
        final StringDataConverter stringConverter = new StringDataConverter();
        this.editFields.put(TableFields.SURNAME, new TextField(parent,
                LocalizationUtil.translate(TableFields.SURNAME))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.NAME, new TextField(parent,
                LocalizationUtil.translate(TableFields.NAME))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.PATRONYMIC, new TextField(parent,
                LocalizationUtil.translate(TableFields.PATRONYMIC))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.ACADEMIC_DEGREE, new TextField(parent,
                LocalizationUtil.translate(TableFields.ACADEMIC_DEGREE))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.ACADEMIC_TITLE, new TextField(parent,
                LocalizationUtil.translate(TableFields.ACADEMIC_TITLE))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.COUNTRY, new TextField(parent,
                LocalizationUtil.translate(TableFields.COUNTRY))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.SITY, new TextField(parent,
                LocalizationUtil.translate(TableFields.SITY))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.E_MAIL, new TextField(parent,
                LocalizationUtil.translate(TableFields.E_MAIL))
                .setDataConverter(stringConverter));
        this.editFields.put(
                TableFields.CONTACT_PHONE_NUMBER,
                new TextField(parent, LocalizationUtil
                        .translate(TableFields.CONTACT_PHONE_NUMBER))
                        .setDataConverter(stringConverter));
        this.editFields.put(TableFields.AFFLICATION, new TextField(parent,
                LocalizationUtil.translate(TableFields.AFFLICATION))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.POSITION, new TextField(parent,
                LocalizationUtil.translate(TableFields.POSITION))
                .setDataConverter(stringConverter));
        final Button button = new Button(parent, SWT.PUSH);
        button.setText(LocalizationUtil.translate(Buttons.SAVE));
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
        this.eventBroker.post(ConferenceTopics.CONF_SAVE, null); // TODO
    }
}
