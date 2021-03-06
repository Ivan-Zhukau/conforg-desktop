package net.ostis.confman.ui.mail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ParticipantService;
import net.ostis.confman.services.SafeConversionService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.EmailedParticipant;
import net.ostis.confman.services.common.model.EmailedParticipants;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Template;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

public class ParticipantsChoosePart {

    private enum Captions implements Localizable {
        NAME("participantTableAuthorName"),
        CATEGORY("participantTableCategory"),
        SECTION("participantTableSection"),
        PREVIOUS_STEP("previousStep"),
        NEXT_STEP("nextStep");

        private String rk;

        private Captions(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    protected static final String TEMPLATES_EDITOR_PART_ID = "net.ostis.confman.ui.part.email.editor";

    protected static final String CONFERENCE_PART_ID   = "net.ostis.confman.ui.part.conferencePart";

    @Inject
    private ESelectionService     selectionService;

    @Inject
    private EPartService          partService;

    private ParticipantService    participantService;

    private DynamicalTable        table;

    private EmailedParticipants   emailedParticipants;
    
    private Template template;

    public ParticipantsChoosePart() {

        super();
        this.participantService = (ParticipantService) ServiceLocator
                .getInstance().getService(ParticipantService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (!(selection instanceof EmailedParticipants)) {
                    return;
                }
                emailedParticipants = (EmailedParticipants) selection;
                template = emailedParticipants.getTemplate(); 
            }
        });
        final MPart part = this.partService.findPart(TEMPLATES_EDITOR_PART_ID);
        this.partService.showPart(part, PartState.ACTIVATE);
        parent.setLayout(new GridLayout(1, true));
        this.table = new DynamicalTable(parent, Boolean.FALSE, SWT.MULTI);
        createNextStepButton(parent);
        createPreviousStepButton(parent);
        createColumns();
        addTableEventSupport();
    }

    private void createNextStepButton(final Composite parent) {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final Button nextButton = new Button(parent, SWT.NONE);
        nextButton.setText(localizationUtil.translate(Captions.NEXT_STEP));
        final GridData gridData = new GridData(SWT.RIGHT, SWT.CENTER,
                Boolean.FALSE, Boolean.FALSE);
        nextButton.setLayoutData(gridData);
        nextButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                onSelected();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                onSelected();
            }

            private void onSelected() {

                final IStructuredSelection selection = (IStructuredSelection) ParticipantsChoosePart.this.table
                        .getViewer().getSelection();
                final Object[] selectedElements = selection.toArray();
                if (selection.getFirstElement() instanceof Participant) {
                    final EmailedParticipants ep = prepareEmailedParticipantsInformation(selectedElements);
                    ParticipantsChoosePart.this.selectionService
                            .setSelection(ep);
                    final MPart part = ParticipantsChoosePart.this.partService
                            .findPart(TEMPLATES_EDITOR_PART_ID);
                    ParticipantsChoosePart.this.partService.activate(part);

                } else {
                    final MessageBox dialog = new MessageBox(parent.getShell(),
                            SWT.ICON_QUESTION | SWT.OK);
                    dialog.setText(localizationUtil
                            .translate("warningDialogTitle"));
                    dialog.setMessage(localizationUtil
                            .translate("warningDialogMessage"));
                    dialog.open();
                }
            }

            private EmailedParticipants prepareEmailedParticipantsInformation(
                    final Object[] selectedElements) {

                final List<Participant> participants = new ArrayList<>();
                for (final Object object : selectedElements) {
                    final Participant participant = (Participant) object;
                    participants.add(participant);
                }
                final List<EmailedParticipant> emailedParticipants = new ArrayList<>();
                for (final Participant participant : participants) {
                    final EmailedParticipant emailedParticipant = new EmailedParticipant();
                    emailedParticipant.setParticipant(participant);
                    emailedParticipant.setTemplate(new Template(
                            ParticipantsChoosePart.this.template));
                    emailedParticipants.add(emailedParticipant);
                }
                ParticipantsChoosePart.this.emailedParticipants.setEmailedParticipants(emailedParticipants);
                return ParticipantsChoosePart.this.emailedParticipants;
            }
        });
    }

    private void createPreviousStepButton(final Composite composite) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        final Button previousButton = new Button(composite, SWT.NONE);
        previousButton.setText(util.translate(Captions.PREVIOUS_STEP));
        final GridData gridData = new GridData(SWT.RIGHT, SWT.BOTTOM,
                Boolean.FALSE, Boolean.FALSE);
        previousButton.setLayoutData(gridData);
        previousButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                previousStep();

            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                previousStep();

            }

            private void previousStep() {

                final MPart part = ParticipantsChoosePart.this.partService
                        .findPart(CONFERENCE_PART_ID);
                ParticipantsChoosePart.this.partService.showPart(part,
                        PartState.VISIBLE);
            }
        });
    }

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final ServiceLocator serviceLocator = ServiceLocator.getInstance();
        final SafeConversionService conversionService = (SafeConversionService) serviceLocator
                .getService(SafeConversionService.class);
        final int COLUMN_WIDTH = 200;
        this.table.createColumn(localizationUtil.translate(Captions.NAME),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Participant participant = (Participant) element;
                        final Person person = participant.getPerson();
                        return person.getFullName();
                    }
                });
        this.table.createColumn(localizationUtil.translate(Captions.CATEGORY),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Participant participant = (Participant) element;
                        final String category = conversionService
                                .safeConverter(participant.getRole()
                                        .getParticipationCategory());
                        return category;
                    }
                });
    }

    private void addTableEventSupport() {

        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(this.participantService.getParticipants());
    }

    @Inject
    @Optional
    private void onConfDataUpdate(
            @UIEventTopic(ConferenceTopics.TEMPLATES_TABLE_UPDATE) final String s) {

        this.table.getViewer().refresh();
    }

}
