package net.ostis.confman.model.registrationform.ui.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ExitListener implements SelectionListener {
	private Shell shell;
	
	public ExitListener(Shell shell) {
		this.shell = shell;
	}
	
	public void widgetSelected(SelectionEvent event) {
        MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messageBox.setMessage("Do you really want to exit?");
        messageBox.setText("Exiting Application");
        int response = messageBox.open();
        if (response == SWT.YES){
            System.exit(0);            	
        }   
    }

    public void widgetDefaultSelected(SelectionEvent event) {
    }
}
