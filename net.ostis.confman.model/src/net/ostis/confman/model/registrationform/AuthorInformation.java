package net.ostis.confman.model.registrationform;

public class AuthorInformation {
	private PersonalInformation personalInformation;
	private ContactInformation contactInformation;
	private WorkPlaceInformation workPlaceInformation;
	
	public AuthorInformation() {
		super();
		personalInformation = new PersonalInformation();
		contactInformation = new ContactInformation();
		workPlaceInformation = new WorkPlaceInformation();
	}
	
	public ContactInformation getContactInformation() {
		return contactInformation;
	}
	public PersonalInformation getPersonalInformation() {
		return personalInformation;
	}
	public WorkPlaceInformation getWorkPlaceInformation() {
		return workPlaceInformation;
	}
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
	public void setPersonalInformation(PersonalInformation personalInformation) {
		this.personalInformation = personalInformation;
	}
	public void setWorkPlaceInformation(
			WorkPlaceInformation workPlaceInformation) {
		this.workPlaceInformation = workPlaceInformation;
	}
}
