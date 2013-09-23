package net.ostis.confman.model.registrationform.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class OpenListener extends MouseAdapter{
	UIRegistrationForm uiRegistrationForm;
	
	public OpenListener(UIRegistrationForm uiRegistrationForm) {
		this.uiRegistrationForm = uiRegistrationForm;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		File[] files= null;
		files =  uiRegistrationForm.openFiles();
		uiRegistrationForm.parseAllFiles(files);
	}
}
