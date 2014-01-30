package net.ostis.confman.services;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.wordparser.RegistrationFormParser;

class RegistrationServiceImpl implements RegistrationService {

    @Override
    public List<RegistrationForm> parseForm(final List<String> paths) {

        final ArrayList<RegistrationForm> registrationForms = new ArrayList<RegistrationForm>();

        for (final String path : paths) {
            registrationForms.add(new RegistrationFormParser().parse(path));
        }
        return registrationForms;
    }
}
