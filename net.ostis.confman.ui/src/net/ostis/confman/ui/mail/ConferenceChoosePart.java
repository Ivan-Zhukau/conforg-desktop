package net.ostis.confman.ui.mail;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.SafeConversionService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.EmailedParticipants;
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

public class ConferenceChoosePart {

    private enum Captions implements Localizable {
        NAME("conferenceTitle"),
        START_DATE("conferenceStartDate"),
        END_DATE("conferenceEndDate"),
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

    protected static final String PARTICIPANTS_PART_ID = "net.ostis.confman.ui.part.email.participantsPart";

    protected static final String TEMPLATES_VIEW_PART_ID   = "net.ostis.confman.ui.part.emailTemplatesPart";

    @Inject
    private ESelectionService     selectionService;

    @Inject
    private EPartService          partService;

    private ConferenceService    conferenceService;

    private DynamicalTable        table;

    private Template              template;

    public ConferenceChoosePart() {

        super();
        this.conferenceService = (ConferenceService) ServiceLocator
                .getInstance().getService(ConferenceService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (!(selection instanceof Template)) {
                    return;
                }
                ConferenceChoosePart.this.template = (Template) selection;
            }
        });
        final MPart part = this.partService.findPart(PARTICIPANTS_PART_ID);
        this.partService.showPart(part, PartState.ACTIVATE);
        parent.setLayout(new GridLayout(1, true));
        this.table = new DynamicalTable(parent, Boolean.FALSE, SWT.SINGLE);
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

                final IStructuredSelection selection = (IStructuredSelection) ConferenceChoosePart.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Conference) {
                    final Conference conference = (Conference)selectedElement;
                    EmailedParticipants emailedParticipants = new EmailedParticipants();
                    emailedParticipants.setConference(conference);
                    emailedParticipants.setTemplate(template);
                    ConferenceChoosePart.this.selectionService
                            .setSelection(emailedParticipants);
                    final MPart part = ConferenceChoosePart.this.partService
                            .findPart(PARTICIPANTS_PART_ID);
                    ConferenceChoosePart.this.partService.activate(part);

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

                final MPart part = ConferenceChoosePart.this.partService
                        .findPart(TEMPLATES_VIEW_PART_ID);
                ConferenceChoosePart.this.partService.showPart(part,
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

                        final Conference conference = (Conference) element;
                        return conversionService.safeConverter(conference.getTitle());
                    }
                });
        this.table.createColumn(localizationUtil.translate(Captions.START_DATE),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Conference conference = (Conference) element;
                        return conversionService.safeConverter(conference.getStartDate());
                    }
                });
        this.table.createColumn(localizationUtil.translate(Captions.END_DATE),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Conference conference = (Conference) element;
                        return conversionService.safeConverter(conference.getEndDate());
                    }
                });
    }

    private void addTableEventSupport() {

        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(this.conferenceService.getConferences());
    }

    @Inject
    @Optional
    private void onConfDataUpdate(
            @UIEventTopic(ConferenceTopics.TEMPLATES_TABLE_UPDATE) final String s) {

        this.table.getViewer().refresh();
    }

}
