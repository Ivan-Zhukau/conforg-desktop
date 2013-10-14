package net.ostis.confman.model.convert;

import java.util.List;

import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.WorkPlaceInformation;
import net.ostis.confman.services.common.model.AuthorsList;
import net.ostis.confman.services.common.model.ContactInformation;
import net.ostis.confman.services.common.model.PersonalInformation;
import net.ostis.confman.services.common.model.WorkplaceInformation;

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

    public AuthorsList convertAuthor(final RegistrationForm form) {

        final AuthorsList authorsList = new AuthorsList();
        final List<AuthorInformation> authors = form.getAuthorsInformation();
        for (final AuthorInformation authorInformation : authors) {
            net.ostis.confman.services.common.model.AuthorInformation authorInfo = new net.ostis.confman.services.common.model.AuthorInformation();
            ContactInformation contactInfo = authorInfo.getContactInformation();
            PersonalInformation personalInfo = authorInfo
                    .getPersonalInformation();
            WorkplaceInformation workpaceInfo = authorInfo
                    .getWorkPlaceInformation();
            contactInfo = convertContactInformation(authorInformation,
                    contactInfo);
            personalInfo = convertPersonalInformation(authorInformation,
                    personalInfo);
            workpaceInfo = convertWorcplaceInformation(authorInformation,
                    workpaceInfo);
            authorInfo = convertAuthor(contactInfo, personalInfo, workpaceInfo,
                    authorInfo);
            authorsList.getAuthorInfo().add(authorInfo);
        }
        return authorsList;
    }

    private ContactInformation convertContactInformation(
            final AuthorInformation author, final ContactInformation contactInfo) {

        final net.ostis.confman.model.registrationform.ContactInformation cInfo = author
                .getContactInformation();
        contactInfo.setCity(cInfo.getCity());
        contactInfo.setContactPhoneNumber(cInfo.getContactPhoneNumber());
        contactInfo.setCountry(cInfo.getCountry());
        contactInfo.seteMail(cInfo.geteMail());

        return contactInfo;
    }

    private PersonalInformation convertPersonalInformation(
            final AuthorInformation author,
            final PersonalInformation personalInfo) {

        final net.ostis.confman.model.registrationform.PersonalInformation pInfo = author
                .getPersonalInformation();
        personalInfo.setAcademicDegree(pInfo.getAcademicDegree());
        personalInfo.setAcademicTitle(pInfo.getAcademicTitle());
        personalInfo.setName(pInfo.getFirstName());
        personalInfo.setPatronymic(pInfo.getThirdName());
        personalInfo.setSurname(pInfo.getSecondName());

        return personalInfo;
    }

    private WorkplaceInformation convertWorcplaceInformation(
            final AuthorInformation author,
            final WorkplaceInformation workpaceInfo) {

        final WorkPlaceInformation wInfo = author.getWorkPlaceInformation();
        workpaceInfo.setAffliation(wInfo.getAffliation());
        workpaceInfo.setPosition(wInfo.getPosition());

        return workpaceInfo;
    }

    private net.ostis.confman.services.common.model.AuthorInformation convertAuthor(
            final ContactInformation cInfo, final PersonalInformation pInfo,
            final WorkplaceInformation wInfo,
            final net.ostis.confman.services.common.model.AuthorInformation author) {

        author.setContactInformation(cInfo);
        author.setPersonalInformation(pInfo);
        author.setWorkPlaceInformation(wInfo);

        return author;
    }
}
