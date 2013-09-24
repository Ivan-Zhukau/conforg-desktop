package net.ostis.confman.model.registrationform.ui.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SaveListener implements SelectionListener {
	private Shell shell;
	
	public SaveListener(Shell shell) {
		this.shell = shell;
	}
	
	public void widgetSelected(SelectionEvent event) {
        FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        fileDialog.setText("Save");
        String[] filterExtension = { "*.docx", "*.doc", "*.txt", "*.*" };
        fileDialog.setFilterExtensions(filterExtension);
        fileDialog.open();
    }

    public void widgetDefaultSelected(SelectionEvent event) {
    }
}
