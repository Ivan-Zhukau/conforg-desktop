package net.ostis.confman.ui.participant;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.editing.AuthorInformation;
import net.ostis.confman.services.editing.AuthorsList;
import net.ostis.confman.services.editing.ContactInformation;
import net.ostis.confman.services.editing.PersonalInformation;
import net.ostis.confman.services.editing.WorkplaceInformation;
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
        INFORMATION_OF_AUTHOR_OF_ARTICLE(
                "Информация о всех Авторах статьи/Information of Author of article:"),
        SURNAME("Фамилия/Surname:"),
        NAME("Имя/First Name:"),
        PATRONYMIC("Отчество/Patronymic:"),
        ACADEMIC_DEGREE("Учёная степень/Academic degree:"),
        ACADEMIC_TITLE("Учёное звание/Academic title:"),
        COUNTRY("Страна/Country:"),
        SITY("Город/City:"),
        E_MAIL("E-mail для связи:"),
        CONTACT_PHONE_NUMBER("Контактный телефон/Contact phone number:"),
        PLACE_OF_WORK("Место работы/Place of work:"),
        AFFLICATION("Организация/Affliation:"),
        POSITION("Должность/Position:");

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

                if (!(selection instanceof AuthorsList)) {
                    return;
                }
                final AuthorsList tableInfo = (AuthorsList) selection;
                onNewSelection(tableInfo, 1); // TODO
            }
        });
        buildLayout(parent);
    }

    private void onNewSelection(final AuthorsList tableInfo,
            final int numberOfAuthor) {

        applyValueBindings(tableInfo, numberOfAuthor);
        for (final TableFields field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }
    }

    private void applyValueBindings(final AuthorsList tableInfo,
            final int numberOfAuthor) {

        final List<AuthorInformation> authorInfo = tableInfo.getAuthorInfo();
        final PersonalInformation personInfo = authorInfo.get(numberOfAuthor)
                .getPersonalInformation();
        final WorkplaceInformation workInfo = authorInfo.get(numberOfAuthor)
                .getWorkPlaceInformation();
        final ContactInformation contactInfo = authorInfo.get(numberOfAuthor)
                .getContactInformation();
        applySurnameBinder(personInfo);
        applyNameBinder(personInfo);
        applyPatronymicBinder(personInfo);
        applyAcademicDegreeBinder(personInfo);
        applyAcademicTitleBinder(personInfo);
        applyCountryBinder(contactInfo);
        applySityBinder(contactInfo);
        applyEMAILBinder(contactInfo);
        applyNumberBinder(contactInfo);
        applyAfflicationBinder(workInfo);
        applyPositionBinder(workInfo);
    }

    private void applySurnameBinder(final PersonalInformation personInfo) {

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

    private void applyNameBinder(final PersonalInformation personInfo) {

        this.editFields.get(TableFields.NAME).setValueBinder(new ValueBinder() {

            @Override
            public void setValue(final Object value) {

                personInfo.setName((String) value);
            }

            @Override
            public Object getValue() {

                return personInfo.getName();
            }
        });
    }

    private void applyPatronymicBinder(final PersonalInformation personInfo) {

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

    private void applyAcademicDegreeBinder(final PersonalInformation personInfo) {

        this.editFields.get(TableFields.ACADEMIC_DEGREE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        personInfo.setAcademicDegree((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return personInfo.getAcademicDegree();
                    }
                });
    }

    private void applyAcademicTitleBinder(final PersonalInformation personInfo) {

        this.editFields.get(TableFields.ACADEMIC_TITLE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        personInfo.setAcademicTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return personInfo.getAcademicTitle();
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

    private void applyCountryBinder(final ContactInformation contactInfo) {

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

    private void applySityBinder(final ContactInformation contactInfo) {

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
