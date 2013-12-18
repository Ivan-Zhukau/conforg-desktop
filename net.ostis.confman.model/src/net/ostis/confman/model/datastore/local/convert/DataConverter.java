package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.datastore.StorageProvider;
import net.ostis.confman.model.registrationform.ArticleInformation;
import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.ContactInformation;
import net.ostis.confman.model.registrationform.PersonalInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.WorkPlaceInformation;
import net.ostis.confman.services.common.model.AcademicInformation;
import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Report;
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

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        final FullModel model = converter.convertData();
        final List<Participant> participants = new ArrayList<Participant>();
        for (final RegistrationForm currentRegForm : rForm) {
            final List<AuthorInformation> authorsInformation = currentRegForm
                    .getAuthorsInformation();
            final ArticleInformation articleInformation = currentRegForm
                    .getArticleInformation();
            final List<Participant> participantsList = new ArrayList<>();
            final Report report = setReport(articleInformation);
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
                model.getPersons().add(person);
                participant.getReports().add(report);
                participantsList.add(participant);
            }
            report.setAllAuthors(participantsList);
            model.getReports().add(report);
        }
        model.getParticipants().addAll(participants);
        saveModel(model);
    }

    private void saveModel(final FullModel model) {

        final StorageProvider storageProvider = StorageProvider.getInstance();
        storageProvider.persist(model);
    }

    private Report setReport(final ArticleInformation articleInformation) {

        final Report report = new Report();
        report.setTitle(articleInformation.getTitleEntry());
        return report;
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
