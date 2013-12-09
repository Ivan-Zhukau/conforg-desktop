package net.ostis.confman.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.wordparser.DocxRegistrationFormParser;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public List<RegistrationForm> parseForm(final List<String> paths) {

        final ArrayList<RegistrationForm> registrationForms = new ArrayList<RegistrationForm>();

        final List<FileInputStream> inputStreams = createInputStream(paths);
        for (final FileInputStream inputStream : inputStreams) {
            registrationForms.add(new DocxRegistrationFormParser()
                    .parse(inputStream));
        }
        return registrationForms;
    }

    private List<FileInputStream> createInputStream(final List<String> paths) {

        final List<FileInputStream> inputStreams = new ArrayList<FileInputStream>();
        try {
            for (final String path : paths) {
                inputStreams.add(new FileInputStream(new File(path)));
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStreams;
    }

}
