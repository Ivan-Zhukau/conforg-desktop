package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.model.registrationform.RegistrationForm;

public interface RegistrationService {

    List<RegistrationForm> parseForm(List<String> paths);
}
