package net.ostis.confman.model.registrationform.ui;

import javax.swing.JTextArea;

import net.ostis.confman.model.registrationform.RegistrationForm;

public class FormRenderer {
	public FormRenderer() {
		super();
	}

	public void render(RegistrationForm form, JTextArea area) {
		area.append(form.getArticleInformation().getTitleEntry()+"\n");
		area.append(form.getArticleInformation().getSpeaker());
		// и так далее
	}

}
