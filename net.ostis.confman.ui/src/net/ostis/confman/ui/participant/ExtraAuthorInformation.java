package net.ostis.confman.ui.participant;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.ParticipantArrival;
import net.ostis.confman.services.common.model.ParticipantRole;
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

public class ExtraAuthorInformation {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum TableFields implements Localizable {
        PATICIPATION_FORM("participationForm"),
        REZIDENCE_PLACE("residencePlace");

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
        EXIBITION_STAND("exibitionStand");

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

    public ExtraAuthorInformation() {

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

        final ParticipantArrival participantArrival = participant.getArrival();
        final ParticipantRole participantRole = participant.getRole();
        applyParticipationFormBinder(participantRole);
        applyResidencePlaceBinder(participantArrival);
    }

    private void applyResidencePlaceBinder(
            final ParticipantArrival participantArrival) {

        this.editFields.get(TableFields.REZIDENCE_PLACE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participantArrival.setResidencePlace((Address) value);
                    }

                    @Override
                    public Object getValue() {

                        return participantArrival.getResidencePlace();
                    }
                });
    }

    private void applyParticipationFormBinder(
            final ParticipantRole participantRole) {

        this.editFields.get(TableFields.PATICIPATION_FORM).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        participantRole.setParticipationForm((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return participantRole.getParticipationForm();
                    }
                });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));

        final StringDataConverter stringConverter = new StringDataConverter();
        this.editFields.put(TableFields.PATICIPATION_FORM, new TextField(
                parent, util.translate(TableFields.PATICIPATION_FORM))
                .setDataConverter(stringConverter));
        this.editFields.put(TableFields.REZIDENCE_PLACE, new TextField(parent,
                util.translate(TableFields.REZIDENCE_PLACE))
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
        this.eventBroker.post(ConferenceTopics.CONF_SAVE, null); // TODO
    }

}
