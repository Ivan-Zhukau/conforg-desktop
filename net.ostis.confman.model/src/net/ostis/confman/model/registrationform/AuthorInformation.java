package net.ostis.confman.model.registrationform;

public class AuthorInformation {

	private String shortName;

	private PersonalInformation personalInformation;

	private ContactInformation contactInformation;

	private WorkPlaceInformation workPlaceInformation;

	public AuthorInformation() {

		super();
		this.personalInformation = new PersonalInformation();
		this.contactInformation = new ContactInformation();
		this.workPlaceInformation = new WorkPlaceInformation();
	}

	public String getShortName() {

		return this.shortName;
	}

	public void setIdAuthor(final String shortName) {

		this.shortName = shortName;
	}

	public ContactInformation getContactInformation() {

		return this.contactInformation;
	}

	public PersonalInformation getPersonalInformation() {

		return this.personalInformation;
	}

	public WorkPlaceInformation getWorkPlaceInformation() {

		return this.workPlaceInformation;
	}

	public void setContactInformation(
			final ContactInformation contactInformation) {

		this.contactInformation = contactInformation;
	}

	public void setPersonalInformation(
			final PersonalInformation personalInformation) {

		this.personalInformation = personalInformation;
	}

	public void setWorkPlaceInformation(
			final WorkPlaceInformation workPlaceInformation) {

		this.workPlaceInformation = workPlaceInformation;
	}
}
