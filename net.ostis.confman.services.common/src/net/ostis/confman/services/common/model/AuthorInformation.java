package net.ostis.confman.services.common.model;

public class AuthorInformation {

    private String               id_Author;

    private PersonalInformation  personalInformation;

    private ContactInformation   contactInformation;

    private WorkplaceInformation workplaceInformation;

    public AuthorInformation() {

        super();
        this.personalInformation = new PersonalInformation();
        this.contactInformation = new ContactInformation();
        this.workplaceInformation = new WorkplaceInformation();
    }

    public String getId_Author() {

        return this.id_Author;
    }

    public void setIdAuthor(final String id_Author) {

        this.id_Author = id_Author;
    }

    public ContactInformation getContactInformation() {

        return this.contactInformation;
    }

    public PersonalInformation getPersonalInformation() {

        return this.personalInformation;
    }

    public WorkplaceInformation getWorkPlaceInformation() {

        return this.workplaceInformation;
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
            final WorkplaceInformation workPlaceInformation) {

        this.workplaceInformation = workPlaceInformation;
    }
}
