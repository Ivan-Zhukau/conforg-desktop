package net.ostis.confman.ui.mail;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.model.mail.entity.Template;
import net.ostis.confman.services.EmailService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

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

    @Inject
    private ESelectionService selectionService;

    private EmailService      emailService;

    private DynamicalTable    table;

    public TemplatesViewPart() {

        super();
        this.emailService = (EmailService) ServiceLocator.getInstance()
                .getService(EmailService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        parent.setLayout(new GridLayout(1, true));
        this.table = new DynamicalTable(parent, Boolean.FALSE);
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
        this.table.getViewer().setInput(this.emailService.getTemplates());
        this.table.getViewer().addSelectionChangedListener(
                new ISelectionChangedListener() {

                    @Override
                    public void selectionChanged(
                            final SelectionChangedEvent event) {

                        final IStructuredSelection selection = (IStructuredSelection) TemplatesViewPart.this.table
                                .getViewer().getSelection();
                        final Object selectedElement = selection
                                .getFirstElement();
                        if (selectedElement instanceof Template) {
                            TemplatesViewPart.this.selectionService
                                    .setSelection(selectedElement);
                        }
                    }
                });
    }

    @Inject
    @Optional
    private void onConfDataUpdate(
            @UIEventTopic(ConferenceTopics.TEMPLATES_TABLE_UPDATE) final String s) {

        this.table.getViewer().refresh();
    }

}
