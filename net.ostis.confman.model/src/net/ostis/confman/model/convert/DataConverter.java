package net.ostis.confman.model.convert;

import java.util.List;

import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.services.common.model.ConferenceModel;

public class DataConverter {

	private static DataConverter instance;

	private DataConverter() {

		super();
	}

	public static DataConverter getInstance() {

		if (instance == null) {
			instance = new DataConverter();
		}
		return instance;
	}

	public void convertAuthors(
			final List<RegistrationForm> rForm, ConferenceModel conferenceModel) {
	    
	}
}
