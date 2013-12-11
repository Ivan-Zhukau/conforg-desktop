package net.ostis.confman.model.registrationform.wordparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.wordparser.format.DocParser;
import net.ostis.confman.model.registrationform.wordparser.format.DocxParser;

public class RegistrationFormParser {

    public RegistrationFormParser() {

    }

    public RegistrationForm parse(final String path) {

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(path));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        if (".doc".equals(getFormat(path))) {
            return new InfoParser().parse(new DocParser().parse(inputStream));
        }
        if (".docx".equals(getFormat(path))) {
            return new InfoParser().parse(new DocxParser().parse(inputStream));
        }
        return new RegistrationForm();
    }

    private String getFormat(final String path) {

        return path.substring(path.lastIndexOf("."));
    }
}
