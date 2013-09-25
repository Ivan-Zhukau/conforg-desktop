package net.ostis.confman.model.registrationform;

public class AuthorInformation {

    private String               id_Author;

    private PersonalInformation  personalInformation;

    private ContactInformation   contactInformation;

    private WorkPlaceInformation workPlaceInformation;

    public AuthorInformation() {

        super();
        this.personalInformation = new PersonalInformation();
        this.contactInformation = new ContactInformation();
        this.workPlaceInformation = new WorkPlaceInformation();
    }

    public String getId_Author() {

        return this.id_Author;
    }

    public void setId_Author(final String id_Author) {

        this.id_Author = id_Author;
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
