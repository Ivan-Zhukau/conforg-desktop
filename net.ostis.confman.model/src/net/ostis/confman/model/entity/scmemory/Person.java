package net.ostis.confman.model.entity.scmemory;


public class Person extends BaseEntity {

    private String surname;

    private String patronymic;

    private String firstName;

    private SystemAddress   residenceSystemAddress;

    private SystemAddress   workplaceSystemAddress;

    private SystemAddress   contactSystemAddress;

    private SystemAddress   academicInfoSystemAddress;

    public Person() {

        super();
    }

    public Person(String surname, String patronymic, String firstName,
            SystemAddress residence, SystemAddress workplace, SystemAddress contacts, SystemAddress degree) {

        super();
        this.surname = surname;
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.residenceSystemAddress = residence;
        this.workplaceSystemAddress = workplace;
        this.contactSystemAddress = contacts;
        this.academicInfoSystemAddress = degree;
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

    public SystemAddress getResidenceSystemAddress() {

        return residenceSystemAddress;
    }

    public void setResidenceSystemAddress(SystemAddress residenceAdrSystemAddress) {

        this.residenceSystemAddress = residenceAdrSystemAddress;
    }

    public SystemAddress getWorkplaceSystemAddress() {

        return workplaceSystemAddress;
    }

    public void setWorkplaceSystemAddress(SystemAddress workplaceSystemAddress) {

        this.workplaceSystemAddress = workplaceSystemAddress;
    }

    public SystemAddress getContactSystemAddress() {

        return contactSystemAddress;
    }

    public void setContactSystemAddress(SystemAddress contactSystemAddress) {

        this.contactSystemAddress = contactSystemAddress;
    }

    public SystemAddress getAcademicInfoSystemAddress() {

        return academicInfoSystemAddress;
    }

    public void setAcademicInfoSystemAddress(SystemAddress academicInfoSystemAddress) {

        this.academicInfoSystemAddress = academicInfoSystemAddress;
    }

}
