package net.ostis.confman.services.common.model;

public class Person {

    private Long                 id;

    private String               surname;

    private String               patronymic;

    private String               firstName;

    private Place                residence;

    private WorkplaceInformation workplace;

    private ContactInformation   contacts;

    private AcademicInformation  degree;

    public Person() {

        super();
    }

    public Long getId() {

        return this.id;
    }

    public void setId(final Long id) {

        this.id = id;
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

    public Place getResidence() {

        return this.residence;
    }

    public void setResidence(final Place residence) {

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
