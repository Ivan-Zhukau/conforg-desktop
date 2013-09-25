package net.ostis.confman.model.registrationform;

public class AuthorInformation {
	private String id_Author;
	private PersonalInformation personalInformation;
	private ContactInformation contactInformation;
	private WorkPlaceInformation workPlaceInformation;
	
	public AuthorInformation() {
		super();
		personalInformation = new PersonalInformation();
		contactInformation = new ContactInformation();
		workPlaceInformation = new WorkPlaceInformation();
	}
	
	public String getId_Author() {
		return id_Author;
	}
	public void setId_Author(String id_Author) {
		this.id_Author = id_Author;
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
