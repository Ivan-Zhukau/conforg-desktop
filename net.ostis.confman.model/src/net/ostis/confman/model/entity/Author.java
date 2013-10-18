package net.ostis.confman.model.entity;

public class Author {
    
    private long id;

    private AuthorPersonalInfo  personalInformation;

    private AuthorContacts   contactInformation;

    private AuthorWorkplace workplaceInformation;

    public Author() {

        super();
        this.personalInformation = new AuthorPersonalInfo();
        this.contactInformation = new AuthorContacts();
        this.workplaceInformation = new AuthorWorkplace();
    }

    public AuthorContacts getContactInformation() {

        return this.contactInformation;
    }

    public AuthorPersonalInfo getPersonalInformation() {

        return this.personalInformation;
    }

    public AuthorWorkplace getWorkPlaceInformation() {

        return this.workplaceInformation;
    }

    public void setContactInformation(
            final AuthorContacts contactInformation) {

        this.contactInformation = contactInformation;
    }

    public void setPersonalInformation(
            final AuthorPersonalInfo personalInformation) {

        this.personalInformation = personalInformation;
    }

    public void setWorkPlaceInformation(
            final AuthorWorkplace workPlaceInformation) {

        this.workplaceInformation = workPlaceInformation;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }
}
