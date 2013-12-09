package net.ostis.confman.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.wordparser.DocxRegistrationFormParser;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public RegistrationForm parseForm(final String path) {

        final FileInputStream inputStream = createInputStream(path);
        final RegistrationForm registrationForm = new DocxRegistrationFormParser()
                .parse(inputStream);
        return registrationForm;
    }

    private FileInputStream createInputStream(final String path) {

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(path));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

}
