package net.ostis.confman.services;

import net.ostis.confman.model.registrationform.RegistrationForm;

public interface RegistrationService {

    RegistrationForm parseForm(String path);
}
