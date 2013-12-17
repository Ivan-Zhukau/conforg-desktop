package net.ostis.confman.ui.mail;

import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class InformationDialog extends TitleAreaDialog {

    private enum LocaleStrings implements Localizable {
        DIALOG_TITLE("warningDialogTitle"),
        DIALOG_MESSAGE("warningDialogMessage"),
        OK_BUTTON_TEXT("warningDialogOKText");

        private String rk;

        private LocaleStrings(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    public InformationDialog(final Shell parentShell) {

        super(parentShell);
    }

    @Override
    public void create() {

        super.create();
        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        setTitle(localizationUtil.translate(LocaleStrings.DIALOG_TITLE));
        setMessage(localizationUtil.translate(LocaleStrings.DIALOG_MESSAGE),
                IMessageProvider.INFORMATION);
    }

    @Override
    protected void createButtonsForButtonBar(final Composite parent) {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        createOkButton(parent, localizationUtil);
    }

    private void createOkButton(final Composite parent,
            final LocalizationUtil localizationUtil) {

        final Button ok = createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, false);
        ok.setText(localizationUtil.translate(LocaleStrings.OK_BUTTON_TEXT));
        setButtonLayoutData(ok);
    }

    @Override
    protected boolean isResizable() {

        return true;
    }

}
