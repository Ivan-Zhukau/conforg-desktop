package net.ostis.confman.test.converter;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.convert.DataConverter;
import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.ContactInformation;
import net.ostis.confman.model.registrationform.PersonalInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.WorkPlaceInformation;
import net.ostis.confman.services.common.model.ConferenceInformation;
import net.ostis.confman.services.common.model.RegistrationInformation;

import org.junit.Test;

public class TableEditorPartTest {

    @Test
    public void testExcelGeneration() {

        final RegistrationForm registrationForm = new RegistrationForm();
        final List<AuthorInformation> authorInfo = new ArrayList<AuthorInformation>();
        final AuthorInformation author = new AuthorInformation();
        final ContactInformation contactInfo = new ContactInformation();
        final PersonalInformation personalInfo = new PersonalInformation();
        final WorkPlaceInformation workInfo = new WorkPlaceInformation();

        contactInfo.setCity("London");
        contactInfo.setCountry("England");
        contactInfo.seteMail("123@rambler.ru");
        contactInfo.setContactPhoneNumber("123456");

        personalInfo.setFirstName("Vasia");
        personalInfo.setSecondName("Petrov");
        personalInfo.setThirdName("Vasiliavich");
        personalInfo.setAcademicDegree("Doctor of sience");
        personalInfo.setAcademicTitle("Docent");

        workInfo.setAffliation("DHL");
        workInfo.setPosition("Refueler");

        author.setContactInformation(contactInfo);
        author.setPersonalInformation(personalInfo);
        author.setWorkPlaceInformation(workInfo);
        author.setIdAuthor("2");

        authorInfo.add(author);

        registrationForm.setAuthorsInformation(authorInfo);
        
        final RegistrationForm registrationForm1 = new RegistrationForm();
        final List<AuthorInformation> authorInfo1 = new ArrayList<AuthorInformation>();
        final AuthorInformation author1 = new AuthorInformation();
        final ContactInformation contactInfo1 = new ContactInformation();
        final PersonalInformation personalInfo1 = new PersonalInformation();
        final WorkPlaceInformation workInfo1 = new WorkPlaceInformation();

        contactInfo1.setCity("London1");
        contactInfo1.setCountry("England1");
        contactInfo1.seteMail("1234@rambler.ru");
        contactInfo1.setContactPhoneNumber("1234567");

        personalInfo1.setFirstName("Vasia1");
        personalInfo1.setSecondName("Petrov1");
        personalInfo1.setThirdName("Vasiliavich1");
        personalInfo1.setAcademicDegree("Doctor of sience1");
        personalInfo1.setAcademicTitle("Docent1");

        workInfo1.setAffliation("DHL1");
        workInfo1.setPosition("Refueler1");

        author1.setContactInformation(contactInfo);
        author1.setPersonalInformation(personalInfo);
        author1.setWorkPlaceInformation(workInfo);
        author1.setIdAuthor("1");

        authorInfo1.add(author1);

        registrationForm1.setAuthorsInformation(authorInfo1);
        
        List<RegistrationForm> rInfo = new ArrayList<RegistrationForm>();
        rInfo.add(registrationForm);
        rInfo.add(registrationForm1);        

        ConferenceInformation aList = DataConverter.getInstance().convertAuthors(rInfo);
        
    }
}
