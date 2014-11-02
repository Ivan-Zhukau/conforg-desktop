package net.ostis.confman.ui.mail;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.services.EmailService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Template;
import net.ostis.confman.services.common.model.Templates;
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

public class TemplatesViewPart {

    private enum Captions implements Localizable {
        NAME("templateName"),
        LANGUAGE("templateLanguage"),
        NEXT("nextStep");

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

    @Inject
    private ESelectionService     selectionService;

    @Inject
    private EPartService          partService;

    private EmailService          emailService;

    private DynamicalTable        table;

    public TemplatesViewPart() {

        super();
        this.emailService = (EmailService) ServiceLocator.getInstance()
                .getService(EmailService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        final MPart part = this.partService.findPart(PARTICIPANTS_PART_ID);
        this.partService.showPart(part, PartState.ACTIVATE);
        parent.setLayout(new GridLayout(1, true));
        this.table = new DynamicalTable(parent, Boolean.FALSE, SWT.SINGLE);
        createNextStepButton(parent);
        createColumns();
        addTableEventSupport();
    }

    private void createNextStepButton(final Composite parent) {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final Button nextButton = new Button(parent, SWT.NONE);
        nextButton.setText(localizationUtil.translate(Captions.NEXT));
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

                final IStructuredSelection selection = (IStructuredSelection) TemplatesViewPart.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Template) {
                    Template template = (Template) selectedElement;
                    template.setBody(emailService.getTemplateBody(template.getPath()));
                    TemplatesViewPart.this.selectionService
                            .setSelection(template);
                    final MPart part = TemplatesViewPart.this.partService
                            .findPart(PARTICIPANTS_PART_ID);
                    TemplatesViewPart.this.partService.activate(part);

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

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final int COLUMN_WIDTH = 200;
        this.table.createColumn(localizationUtil.translate(Captions.NAME),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Template template = (Template) element;
                        return template.getName();
                    }
                });
        this.table.createColumn(localizationUtil.translate(Captions.LANGUAGE),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Template template = (Template) element;
                        return localizationUtil.translate(template
                                .getLanguage());
                    }
                });
    }

    private void addTableEventSupport() {

        this.table.getViewer().setContentProvider(
                ArrayContentProvider.getInstance());
        ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        Templates tempaltes = converter.convertTemplates(this.emailService.getTemplates());
        this.table.getViewer().setInput(tempaltes.getTemplates());
    }

    @Inject
    @Optional
    private void onConfDataUpdate(
            @UIEventTopic(ConferenceTopics.TEMPLATES_TABLE_UPDATE) final String s) {

        this.table.getViewer().refresh();
    }

}
