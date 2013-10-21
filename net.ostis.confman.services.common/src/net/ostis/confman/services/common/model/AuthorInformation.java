package net.ostis.confman.services.common.model;

public class AuthorInformation {

    private String               idAuthor;

    private PersonalInformation  personalInformation;

    private ContactInformation   contactInformation;

    private WorkplaceInformation workplaceInformation;

    public AuthorInformation() {

        super();
        this.personalInformation = new PersonalInformation();
        this.contactInformation = new ContactInformation();
        this.workplaceInformation = new WorkplaceInformation();
    }

    public String getIdAuthor() {

        return this.idAuthor;
    }

    public void setIdAuthor(final String idAuthor) {

        this.idAuthor = idAuthor;
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
