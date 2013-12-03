package net.ostis.confman.test.xml;

import java.util.Arrays;

import net.ostis.confman.model.datastore.local.PersonReader;
import net.ostis.confman.model.datastore.local.PersonWriter;
import net.ostis.confman.model.entity.AcademicInformation;
import net.ostis.confman.model.entity.Address;
import net.ostis.confman.model.entity.ContactInformation;
import net.ostis.confman.model.entity.Person;
import net.ostis.confman.model.entity.Persons;
import net.ostis.confman.model.entity.WorkplaceInformation;

import org.junit.Test;

public class PersonStorageTest {

    @Test
    public void testPersonSave() {

        final Person person = new Person();
        final WorkplaceInformation wpi = new WorkplaceInformation();
        wpi.setPosition("Scholar");
        wpi.setWorkplace("BSUIR");
        final Address address = new Address();
        address.setCity("MINSK");
        address.setCountry("BOOLBLAND");
        final ContactInformation ci = new ContactInformation();
        ci.setEmail("v.m@yyahho.com");
        ci.setPhone("78965412");
        final AcademicInformation ai = new AcademicInformation();
        ai.setTitle("scholar");
        ai.setDegree("scholar");
        person.setId(1L);
        person.setFirstName("Vadim");
        person.setPatronymic("Viktorovich");
        person.setSurname("Mihalovski");
        person.setWorkplace(wpi);
        person.setResidence(address);
        person.setContacts(ci);
        person.setDegree(ai);

        final Persons persons = new Persons();
        persons.setPersons(Arrays.asList(person));
        final Runnable command = new PersonWriter(persons);
        command.run();
    }

    @Test
    public void testPersonLoad() throws Exception {

        final PersonReader reader = new PersonReader();
        reader.call();
    }
}
