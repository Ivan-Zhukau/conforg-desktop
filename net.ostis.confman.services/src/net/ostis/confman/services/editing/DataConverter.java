package net.ostis.confman.services.editing;

import java.util.List;

import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;

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

    public AuthorInformation convertAuthor(final RegistrationForm form) {

        final AuthorsList authorsList = new AuthorsList();
        final List<AuthorInformation> authorInfo = form.getAuthorsInformation();
        for (final AuthorInformation authorInformation : authorInfo) {

        }
        return null;
    }
}
