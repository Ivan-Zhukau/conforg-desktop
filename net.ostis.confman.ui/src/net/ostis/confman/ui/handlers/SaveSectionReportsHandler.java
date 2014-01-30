package net.ostis.confman.ui.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.inject.Named;

import net.ostis.confman.services.SectionService;
import net.ostis.confman.services.ServiceLocator;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SaveSectionReportsHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        final FileDialog dialog = new FileDialog(shell, SWT.SAVE);
        dialog.setFilterNames(new String[] { "xlsx", "xls" });
        dialog.setFilterExtensions(new String[] { "*.xlsx", "*.xls" });
        dialog.setFileName("Section_reports.xlsx");
        final String filePath = dialog.open();
        if (filePath != null) {
            try {
                ((SectionService) ServiceLocator.getInstance().getService(SectionService.class))
                        .generateSectionReporList(new FileOutputStream(
                                new File(filePath)));
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
