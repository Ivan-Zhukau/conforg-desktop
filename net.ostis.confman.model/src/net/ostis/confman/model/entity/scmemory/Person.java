package net.ostis.confman.model.entity.scmemory;

public class Person extends BaseEntity {

    private String               surname;

    private String               patronymic;

    private String               firstName;

    private Address              residence;

    private WorkplaceInformation workplace;

    private ContactInformation   contacts;

    private AcademicInformation  degree;

    public Person() {

        super();
    }

    public Person(String surname, String patronymic, String firstName,
            Address residence, WorkplaceInformation workplace,
            ContactInformation contacts, AcademicInformation degree) {

        super();
        this.surname = surname;
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.residence = residence;
        this.workplace = workplace;
        this.contacts = contacts;
        this.degree = degree;
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

    public Address getResidence() {

        return this.residence;
    }

    public void setResidence(final Address residence) {

        this.residence = residence;
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
}
