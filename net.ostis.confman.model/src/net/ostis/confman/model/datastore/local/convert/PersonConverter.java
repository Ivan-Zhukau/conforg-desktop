package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.AcademicInformation;
import net.ostis.confman.model.entity.Address;
import net.ostis.confman.model.entity.ContactInformation;
import net.ostis.confman.model.entity.Person;
import net.ostis.confman.model.entity.Persons;
import net.ostis.confman.model.entity.WorkplaceInformation;
import net.ostis.confman.services.common.model.FullModel;

public class PersonConverter {

    public static Persons convert(final FullModel model,
            final IDProvider idProvider) {

        final List<Person> personsToStore = convertPersons(model.getPersons(),
                idProvider);
        final Persons personStorage = new Persons();
        personStorage.setPersons(personsToStore);
        return personStorage;
    }

    private static List<Person> convertPersons(
            final List<net.ostis.confman.services.common.model.Person> persons,
            final IDProvider idProvider) {

        final List<Person> personsToStore = new ArrayList<Person>();
        Person personToStore;
        for (final net.ostis.confman.services.common.model.Person person : persons) {
            personToStore = convertFields(person, idProvider.getId(person));
            personsToStore.add(personToStore);
        }
        return personsToStore;
    }

    private static Person convertFields(
            final net.ostis.confman.services.common.model.Person anotherPerson,
            final Long id) {

        final Person person = new Person();
        person.setId(id);
        person.setFirstName(anotherPerson.getFirstName());
        person.setPatronymic(anotherPerson.getPatronymic());
        person.setSurname(anotherPerson.getSurname());
        person.setContacts(convertContacts(anotherPerson.getContacts()));
        person.setDegree(convertDegree(anotherPerson.getDegree()));
        person.setResidence(convertResidence(anotherPerson.getResidence()));
        person.setWorkplace(convertWorkplace(anotherPerson.getWorkplace()));
        return person;
    }

    private static ContactInformation convertContacts(
            final net.ostis.confman.services.common.model.ContactInformation contacts) {

        final ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail(contacts.geteMail());
        contactInformation.setPhone(contacts.getContactPhoneNumber());
        return contactInformation;
    }

    private static AcademicInformation convertDegree(
            final net.ostis.confman.services.common.model.AcademicInformation degree) {

        final AcademicInformation academicInformation = new AcademicInformation();
        academicInformation.setDegree(degree.getDegree());
        academicInformation.setTitle(degree.getTitle());
        return academicInformation;
    }

    private static Address convertResidence(
            final net.ostis.confman.services.common.model.Address residence) {

        final Address address = new Address();
        address.setCity(residence.getCity());
        address.setCountry(residence.getCountry());
        address.setHouseNumber(residence.getHouseNumber());
        address.setStreet(residence.getStreet());
        return address;
    }

    private static WorkplaceInformation convertWorkplace(
            final net.ostis.confman.services.common.model.WorkplaceInformation workplace) {

        final WorkplaceInformation workplaceInformation = new WorkplaceInformation();
        workplaceInformation.setPosition(workplace.getPosition());
        workplaceInformation.setWorkplace(workplace.getAffliation());
        return workplaceInformation;
    }
}
