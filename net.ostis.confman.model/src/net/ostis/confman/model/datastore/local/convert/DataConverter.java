package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.ContactInformation;
import net.ostis.confman.model.registrationform.PersonalInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.WorkPlaceInformation;
import net.ostis.confman.services.common.model.AcademicInformation;
import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
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

    public void convertAuthors(final List<RegistrationForm> rForm) {

        final List<Participant> participants = new ArrayList<Participant>();
        for (final RegistrationForm currentRegForm : rForm) {
            final List<AuthorInformation> authorsInformation = currentRegForm
                    .getAuthorsInformation();
            for (final AuthorInformation currentAuthor : authorsInformation) {
                final Participant participant = new Participant();
                final Person person = new Person();
                final PersonalInformation personalInformation = currentAuthor
                        .getPersonalInformation();
                final ContactInformation contactInfo = currentAuthor
                        .getContactInformation();
                final WorkPlaceInformation workPlaceInformation = currentAuthor
                        .getWorkPlaceInformation();
                setPersonInfo(person, personalInformation, contactInfo,
                        workPlaceInformation);
                participant.setPerson(person);
                participants.add(participant);
            }

        }
    }

    private void setPersonInfo(final Person person,
            final PersonalInformation personalInformation,
            final ContactInformation contactInfo,
            final WorkPlaceInformation workPlaceInformation) {

        setPersonalInfo(person, personalInformation);
        person.setResidence(getAddress(contactInfo));
        person.setWorkplace(getWorkplaceInfo(workPlaceInformation));
        person.setContacts(getContactInfo(contactInfo));
        person.setDegree(getAcademicInfo(personalInformation));
    }

    private Person setPersonalInfo(final Person person,
            final PersonalInformation personalInformation) {

        person.setFirstName(personalInformation.getFirstName());
        person.setSurname(personalInformation.getSecondName());
        person.setPatronymic(personalInformation.getThirdName());
        return person;
    }

    private Address getAddress(final ContactInformation contactInformation) {

        final Address address = new Address();
        address.setCity(contactInformation.getCity());
        address.setCountry(contactInformation.getCountry());
        return address;
    }

    private WorkplaceInformation getWorkplaceInfo(
            final WorkPlaceInformation workPlaceInformation) {

        final WorkplaceInformation information = new WorkplaceInformation();
        information.setAffliation(workPlaceInformation.getAffliation());
        information.setPosition(workPlaceInformation.getPosition());
        return information;
    }

    private net.ostis.confman.services.common.model.ContactInformation getContactInfo(
            final ContactInformation contactInfo) {

        final net.ostis.confman.services.common.model.ContactInformation contacts = new net.ostis.confman.services.common.model.ContactInformation();
        contacts.setContactPhoneNumber(contactInfo.getContactPhoneNumber());
        contacts.seteMail(contactInfo.geteMail());
        return contacts;
    }

    private AcademicInformation getAcademicInfo(
            final PersonalInformation information) {

        final AcademicInformation academicInformation = new AcademicInformation();
        academicInformation.setTitle(information.getAcademicTitle());
        academicInformation.setDegree(information.getAcademicDegree());
        return academicInformation;
    }
}
