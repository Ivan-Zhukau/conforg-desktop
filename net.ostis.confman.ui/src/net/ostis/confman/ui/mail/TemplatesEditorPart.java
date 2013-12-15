package net.ostis.confman.ui.mail;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.model.mail.entity.Template;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class TemplatesEditorPart {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum Captions implements Localizable {
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

    @Inject
    private ESelectionService selectionService;

    private Text              textArea;

    public TemplatesEditorPart() {

        super();
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
                final Template template = (Template) selection;
                onNewSelection(template);
            }
        });

        buildLayout(parent);
    }

    private void onNewSelection(final Template template) {

        this.textArea.setText(template.getName());
    }

    private void buildLayout(final Composite parent) {

        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));
        this.textArea = new Text(parent, SWT.MULTI | SWT.LEAD
        | SWT.BORDER | SWT.WRAP | SWT.FILL);
        this.textArea.setSize(200, 200);
        createNextStepButton(parent);
    }

    private void createNextStepButton(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        final Button nextButton = new Button(parent, SWT.NONE);
        nextButton.setText(util.translate(Captions.NEXT_STEP));
        final GridData gridData = new GridData(SWT.RIGHT, SWT.CENTER,
                Boolean.FALSE, Boolean.FALSE);
        nextButton.setLayoutData(gridData);
    }

}
