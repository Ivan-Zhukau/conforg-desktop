package net.ostis.confman.ui.handlers;

import javax.inject.Named;

import net.ostis.confman.services.SectionServiceImpl;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class SaveSectionReportsHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        final SectionServiceImpl generator = new SectionServiceImpl();
        generator.generateSectionReporList();
        MessageDialog.openInformation(shell, "Info", "SectionReports.xlsx "
                + util.translate("fileWasCreated"));
    }
}
