package net.ostis.confman.services.common.model;

public class Person {

    private String               surname;

    private String               patronymic;

    private String               firstName;

    private Address              arddess;

    private WorkplaceInformation workplace;

    private ContactInformation   contacts;

    private AcademicInformation  degree;

    public Person() {

        super();
        this.arddess = new Address();
        this.workplace = new WorkplaceInformation();
        this.contacts = new ContactInformation();
        this.degree = new AcademicInformation();
    }

    public String getSurname() {

        return this.surname;
    }

    public void setSurname(final String surname) {

        this.surname = surname;
    }

    public String getPatronymic() {

        return this.patronymic;
    }

    public void setPatronymic(final String patronymic) {

        this.patronymic = patronymic;
    }

    public String getFirstName() {

        return this.firstName;
    }

    public void setFirstName(final String firstName) {

        this.firstName = firstName;
    }

    public Address getAddress() {

        return this.arddess;
    }

    public void setAddress(final Address address) {

        this.arddess = address;
    }

    public WorkplaceInformation getWorkplace() {

        return this.workplace;
    }

    public void setWorkplace(final WorkplaceInformation workplace) {

        this.workplace = workplace;
    }

    public ContactInformation getContacts() {

        return this.contacts;
    }

    public void setContacts(final ContactInformation contacts) {

        this.contacts = contacts;
    }

    public AcademicInformation getDegree() {

        return this.degree;
    }

    public void setDegree(final AcademicInformation degree) {

        this.degree = degree;
    }

    public String getFullName() {

        final StringBuilder fullName = new StringBuilder();
        if(this.surname != null) {
            fullName.append(this.surname).append(" ");
        }
        if(this.firstName != null) {
            fullName.append(this.firstName).append(" ");
        }        
        fullName.append(this.patronymic == null ? "" : " " + this.patronymic);        
        return fullName.toString();
    }
    
}
