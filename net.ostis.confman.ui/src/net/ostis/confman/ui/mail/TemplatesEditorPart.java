package net.ostis.confman.ui.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import net.ostis.confman.services.BuildTemplateService;
import net.ostis.confman.services.EmailService;
import net.ostis.confman.services.SafeConversionService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.TemplateContextService;
import net.ostis.confman.services.TemplateContextServiceLocator;
import net.ostis.confman.services.common.model.EmailedParticipant;
import net.ostis.confman.services.common.model.EmailedParticipants;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TemplatesEditorPart {

    private static final int    LAYOUT_COL_COUNT  = 1;

    private static final String DEFAULT_FILE_NAME = "Emailed_participants_list.xlsx";

    private enum Captions implements Localizable {
        NAME("participantTableAuthorName"),
        EMAIL("participantTableEmail"),
        CATEGORY("participantTableCategory"),
        SECTION("participantTableSection"),
        PREVIOUS_STEP("previousStep"),
        NEXT_STEP("nextStep"),
        SAVE("save"),
        CURRENT_TEMPLATE_NAME("currentTemplateName"),
        NON_TEMPLATE_MESSAGE("nonTemplateMessage");

        private String rk;

        private Captions(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    protected final static String PARTICIPANTS_CHOOSE_PART_ID = "net.ostis.confman.ui.part.email.participantsPart";

    @Inject
    private EPartService          partService;

    @Inject
    private ESelectionService     selectionService;

    private Text                  textArea;

    private DynamicalTable        table;

    private EmailedParticipants   participants;

    private EmailedParticipant    selectedParticipant;

    private Label                 currentTemplateLabel;

    public TemplatesEditorPart() {

        super();
        this.participants = new EmailedParticipants();
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
                TemplatesEditorPart.this.participants = (EmailedParticipants) selection;
                addTableEventSupport();
                processMailBody(TemplatesEditorPart.this.participants);
                updateCurrentTemplateValue();
            }
        });
        buildLayout(parent);
    }

    private void onNewSelection(final EmailedParticipant participant) {

        this.textArea.setText(participant.getTemplate().getBody());
    }

    private void processMailBody(final EmailedParticipants participants) {

        for (final EmailedParticipant participant : participants
                .getEmailedParticipants()) {
            final ServiceLocator locator = ServiceLocator.getInstance();
            final BuildTemplateService templateService = (BuildTemplateService) locator
                    .getService(BuildTemplateService.class);
            final TemplateContextServiceLocator contextServiceLocator = TemplateContextServiceLocator
                    .getInstance();
            final TemplateContextService contextService = (TemplateContextService) contextServiceLocator
                    .getService(participants.getTemplateName());
            contextService.initTemplateContext(participant.getParticipant());
            final String mailBody = templateService.processTemplate(participant
                    .getTemplate().getBody(), contextService);
            participant.getTemplate().setBody(mailBody);
        }
    }

    private void buildLayout(final Composite parent) {

        parent.setLayout(new FillLayout());
        this.table = new DynamicalTable(parent, Boolean.FALSE, SWT.SINGLE);
        createColumns();
        final Composite composite = createTextWrapper(parent);
        createTextArea(composite);
        createSaveButton(composite);
        createNextStepButton(composite);
        createPreviousStepButton(composite);
        createLabelForCurrentTemplate(composite);
    }

    private Composite createTextWrapper(final Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(LAYOUT_COL_COUNT, Boolean.FALSE));
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL,
                Boolean.TRUE, Boolean.TRUE);
        composite.setLayoutData(gridData);
        return composite;
    }

    private void createTextArea(final Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new FillLayout());
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL,
                Boolean.TRUE, Boolean.TRUE);
        this.textArea = new Text(composite, SWT.MULTI | SWT.BORDER
                | SWT.V_SCROLL | SWT.H_SCROLL);
        composite.setLayoutData(gridData);
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

                        final EmailedParticipant emailedParticipant = (EmailedParticipant) element;
                        final Participant participant = emailedParticipant
                                .getParticipant();
                        final Person person = participant.getPerson();
                        return person.getFullName();
                    }
                });
        this.table.createColumn(localizationUtil.translate(Captions.EMAIL),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final EmailedParticipant emailedParticipant = (EmailedParticipant) element;
                        final Participant participant = emailedParticipant
                                .getParticipant();
                        final String email = conversionService
                                .safeConverter(participant.getPerson()
                                        .getContacts().geteMail());
                        return email;
                    }
                });
        this.table.createColumn(localizationUtil.translate(Captions.CATEGORY),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final EmailedParticipant emailedParticipant = (EmailedParticipant) element;
                        final Participant participant = emailedParticipant
                                .getParticipant();
                        final String category = conversionService
                                .safeConverter(participant.getRole()
                                        .getParticipationCategory());
                        return category;
                    }
                });
    }

    private void addTableEventSupport() {

        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(this.participants.getEmailedParticipants());
        this.table.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                final IStructuredSelection selection = (IStructuredSelection) TemplatesEditorPart.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof EmailedParticipant) {
                    TemplatesEditorPart.this.selectedParticipant = (EmailedParticipant) selectedElement;
                    onNewSelection(TemplatesEditorPart.this.selectedParticipant);
                }
            }
        });
    }

    private void createNextStepButton(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        final Button nextButton = new Button(parent, SWT.NONE);
        nextButton.setText(util.translate(Captions.NEXT_STEP));
        final GridData gridData = new GridData(SWT.RIGHT, SWT.BOTTOM,
                Boolean.FALSE, Boolean.FALSE);
        nextButton.setLayoutData(gridData);
        nextButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                onSelection();

            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                onSelection();

            }

            private void onSelection() {

                final IStructuredSelection selection = (IStructuredSelection) TemplatesEditorPart.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof EmailedParticipant) {
                    onNewSelection((EmailedParticipant) selectedElement);

                } else {

                    // TODO vadim-mihalovski: add warning dialog: empty
                    // selection
                }
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

                final MPart part = TemplatesEditorPart.this.partService
                        .findPart(PARTICIPANTS_CHOOSE_PART_ID);
                TemplatesEditorPart.this.partService.showPart(part,
                        PartState.VISIBLE);
            }
        });
    }

    private void createSaveButton(final Composite parent) {

        this.textArea.setText("");
        final LocalizationUtil util = LocalizationUtil.getInstance();
        final Button nextButton = new Button(parent, SWT.NONE);
        nextButton.setText(util.translate(Captions.SAVE));
        final GridData gridData = new GridData(SWT.LEFT, SWT.BOTTOM,
                Boolean.FALSE, Boolean.FALSE);
        nextButton.setLayoutData(gridData);
        nextButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                onSelection();

            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                onSelection();

            }

            private void onSelection() {

                if (TemplatesEditorPart.this.selectedParticipant != null) {
                    TemplatesEditorPart.this.selectedParticipant
                            .getTemplate()
                            .setBody(
                                    TemplatesEditorPart.this.textArea.getText());
                }
            }
        });
    }

    private void createLabelForCurrentTemplate(final Composite composite) {

        this.currentTemplateLabel = new Label(composite, SWT.LEFT);
    }

    private void updateCurrentTemplateValue() {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        String currentTemplateName;
        if (this.participants.getEmailedParticipants() != null
                && this.participants.getTemplateName() != null) {
            final String template = this.participants.getTemplateName();
            currentTemplateName = template;
        } else {
            currentTemplateName = util.translate(Captions.NON_TEMPLATE_MESSAGE);
        }
        this.currentTemplateLabel.setText(util
                .translate(Captions.CURRENT_TEMPLATE_NAME)
                + currentTemplateName);
    }

    @Inject
    @Optional
    private void saveEmailedParticipantsToExcel(
            @UIEventTopic(ConferenceTopics.SAVE_EMAILED_PARTICIPANTS_TO_EXCEL) final String s,
            @Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        final FileDialog dialog = new FileDialog(shell, SWT.SAVE);
        dialog.setFilterNames(new String[] { "xlsx", "xls" });
        dialog.setFilterExtensions(new String[] { "*.xlsx", "*.xls" });
        dialog.setFileName(DEFAULT_FILE_NAME);
        final String filePath = dialog.open();
        if (filePath != null) {
            try {
                ((EmailService) ServiceLocator.getInstance().getService(
                        EmailService.class))
                        .generateExcelListOfEmailedParticipants(
                                new FileOutputStream(new File(filePath)),
                                this.participants.getEmailedParticipants());
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
