package net.ostis.confman.test.tableEditorPart;



import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.AuthorInformation;
import net.ostis.confman.model.registrationform.ContactInformation;
import net.ostis.confman.model.registrationform.PersonalInformation;
import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.WorkPlaceInformation;

import org.junit.Test;

public class TableEditorPartTest {
	
	@Test
    public void testExcelGeneration() {
		
		RegistrationForm registrationForm = new RegistrationForm();
		List<AuthorInformation> authorInfo = new ArrayList<AuthorInformation>();
		AuthorInformation author = new AuthorInformation();
		ContactInformation contactInfo = new ContactInformation();
		PersonalInformation personalInfo = new PersonalInformation();
		WorkPlaceInformation workInfo = new WorkPlaceInformation();
		
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
		
		authorInfo.add(author);
		
		TableEditorPartTest tablePart = new TableEditorPartTest();
		
		
		
	}
}
