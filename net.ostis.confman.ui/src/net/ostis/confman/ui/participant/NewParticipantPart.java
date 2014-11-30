package net.ostis.confman.ui.participant;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ParticipantService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.ComboBoxField;
import net.ostis.confman.ui.common.component.EditableComponent;
import net.ostis.confman.ui.common.component.StringDataConverter;
import net.ostis.confman.ui.common.component.TextField;
import net.ostis.confman.ui.common.component.ValueBinder;
import net.ostis.confman.ui.common.component.ValueComboBinder;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class NewParticipantPart {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum TextFields implements Localizable {
        PATICIPATION_CATEGORY("participationCategory"),
        SURNAME("surname"),
        NAME("name"),
        PATRONYMIC("patronymic");

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
    private IEventBroker                                eventBroker;

    private Participant                                 newParticipant;

    public NewParticipantPart() {

        super();
        this.editFields = new EnumMap<>(TextFields.class);
        this.newParticipant = new Participant();
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        buildLayout(parent);
    }

    private void bind(final Participant participant) {

        applyValueBindings(participant);
        for (final TextFields field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }
    }

    private void applyValueBindings(final Participant participant) {

        final Person person = participant.getPerson();
        applySurnameBinder(person);
        applyNameBinder(person);
        applyPatronymicBinder(person);
        applyParticipationCategoryBinder(participant);
    }

    private void applySurnameBinder(final Person personInfo) {

        this.editFields.get(TextFields.SURNAME).setValueBinder(
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

        this.editFields.get(TextFields.NAME).setValueBinder(new ValueBinder() {

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

        this.editFields.get(TextFields.PATRONYMIC).setValueBinder(
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

                        if (!util.translate(ParticipationCategory.DEFAULT)
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

                        List<String> list = new ArrayList<String>();
                        for (ParticipationCategory item : ParticipationCategory
                                .values()) {
                            list.add(util.translate(item));
                        }
                        return list;
                    }

                    @Override
                    public Object getCurrentValue() {

                        return participant.getRole().getParticipationCategory();
                    }
                });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));

        final StringDataConverter stringConverter = new StringDataConverter();
        this.editFields.put(TextFields.SURNAME,
                new TextField(parent, util.translate(TextFields.SURNAME))
                        .setDataConverter(stringConverter));
        this.editFields.put(TextFields.NAME,
                new TextField(parent, util.translate(TextFields.NAME))
                        .setDataConverter(stringConverter));
        this.editFields.put(TextFields.PATRONYMIC,
                new TextField(parent, util.translate(TextFields.PATRONYMIC))
                        .setDataConverter(stringConverter));
        this.editFields.put(
                TextFields.PATICIPATION_CATEGORY,
                new ComboBoxField(parent, util
                        .translate(TextFields.PATICIPATION_CATEGORY),
                        new String[0], null)
                        .setDataConverter(new StringDataConverter()));

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

        bind(newParticipant);
    }

    private void onSave() {

        for (final TextFields field : this.editFields.keySet()) {
            this.editFields.get(field).apply();
        }
        this.eventBroker.post(ConferenceTopics.TABLE_UPDATE, null);
        final ParticipantService participantService = (ParticipantService) ServiceLocator
                .getInstance().getService(ParticipantService.class);
        if (!"".equals(newParticipant.getPerson().getFirstName())
                || !"".equals(newParticipant.getPerson().getPatronymic())
                || !"".equals(newParticipant.getPerson().getSurname())) {
            participantService.addParticipant(newParticipant);
            participantService.addPerson(newParticipant.getPerson());
            participantService.fireData();
            newParticipant = new Participant();
            bind(newParticipant);
        }
    }
}
