package net.ostis.confman.ui.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.inject.Named;

import net.ostis.confman.services.ScheduleServiceImpl;
import net.ostis.confman.services.SectionServiceImpl;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SaveSectionReportsHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        FileDialog dialog = new FileDialog(shell, SWT.SAVE);
        dialog
            .setFilterNames(new String[] { "xlsx", "xls" });
        dialog.setFilterExtensions(new String[] { "*.xlsx", "*.xls" }); 
        dialog.setFileName("Section_schedule.xlsx");
        final String filePath = dialog.open();
        if(filePath != null){
                try {
                    new ScheduleServiceImpl().save(
                            new FileOutputStream(new File(filePath))
                            );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }        
        }
    }
}
