package net.ostis.confman.model.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.entity.Author;
import net.ostis.confman.model.entity.AuthorContacts;
import net.ostis.confman.model.entity.AuthorPersonalInfo;
import net.ostis.confman.model.entity.AuthorWorkplace;
import net.ostis.confman.model.entity.Authors;
import net.ostis.confman.services.common.model.AuthorInformation;
import net.ostis.confman.services.common.model.ContactInformation;
import net.ostis.confman.services.common.model.AcademiclInformation;
import net.ostis.confman.services.common.model.RegistrationInformation;
import net.ostis.confman.services.common.model.WorkplaceInformation;

public class EntityConverter {

    private static EntityConverter instance;

    private EntityConverter() {

        super();
    }

    public static EntityConverter getInstance() {

        if (instance == null) {
            instance = new EntityConverter();
        }
        return instance;
    }

    public Authors authorsToEntity(final RegistrationInformation info) {

        final List<AuthorInformation> list = info.getAuthorInfo();
        final Authors authors = new Authors();
        authors.setAuthorInfo(convertAuthorInformation(list));
        return null;
    }

    private List<Author> convertAuthorInformation(
            final List<AuthorInformation> authorInfo) {

        final List<Author> authors = new ArrayList<Author>();
        for (final AuthorInformation authorInformation : authorInfo) {
            authors.add(convertAuthor(authorInformation));
        }
        return authors;
    }

    private Author convertAuthor(final AuthorInformation authorInformation) {

        final AuthorPersonalInfo info = convertPersonalInfo(authorInformation
                .getPersonalInformation());
        final AuthorContacts contacts = convertContacts(authorInformation
                .getContactInformation());
        final AuthorWorkplace workplace = convertWorkplace(authorInformation
                .getWorkPlaceInformation());
        final Author author = new Author();
        author.setPersonalInformation(info);
        author.setContactInformation(contacts);
        author.setWorkPlaceInformation(workplace);
        return author;
    }

    private AuthorPersonalInfo convertPersonalInfo(
            final AcademiclInformation personalInformation) {

        final AuthorPersonalInfo info = new AuthorPersonalInfo();
        info.setName(personalInformation.getName());
        info.setSurname(personalInformation.getSurname());
        info.setPatronymic(personalInformation.getPatronymic());
        info.setAcademicDegree(personalInformation.getAcademicDegree());
        info.setAcademicTitle(personalInformation.getAcademicTitle());
        return info;
    }

    private AuthorContacts convertContacts(
            final ContactInformation contactInformation) {

        final AuthorContacts contacts = new AuthorContacts();
        contacts.setCountry(contactInformation.getCountry());
        contacts.setCity(contactInformation.getCity());
        contacts.setContactPhoneNumber(contactInformation
                .getContactPhoneNumber());
        contacts.seteMail(contactInformation.geteMail());
        return contacts;
    }

    private AuthorWorkplace convertWorkplace(
            final WorkplaceInformation workPlaceInformation) {

        final AuthorWorkplace workplace = new AuthorWorkplace();
        workplace.setAffliation(workPlaceInformation.getAffliation());
        workplace.setPosition(workPlaceInformation.getPosition());
        return workplace;
    }

    public List<AuthorInformation> entityToAuthors(final Authors authors) {

        return null;
    }
}
