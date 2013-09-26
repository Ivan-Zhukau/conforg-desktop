package net.ostis.confman.model.registrationform.ui.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SaveListener implements SelectionListener {

    private Shell shell;

    public SaveListener(final Shell shell) {

        this.shell = shell;
    }

    @Override
    public void widgetSelected(final SelectionEvent event) {

        final FileDialog fileDialog = new FileDialog(this.shell, SWT.SAVE);
        fileDialog.setText("Save");
        final String[] filterExtension = { "*.docx", "*.doc", "*.txt", "*.*" };
        fileDialog.setFilterExtensions(filterExtension);
        fileDialog.open();
    }

    @Override
    public void widgetDefaultSelected(final SelectionEvent event) {

    }
}
