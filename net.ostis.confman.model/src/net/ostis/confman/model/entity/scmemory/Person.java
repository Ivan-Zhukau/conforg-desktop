package net.ostis.confman.model.entity.scmemory;

import java.util.UUID;

public class Person extends BaseEntity {

    private String surname;

    private String patronymic;

    private String firstName;

    private UUID   residenceAdrUuid;

    private UUID   workplaceUuid;

    private UUID   contactUuid;

    private UUID   academicInfoUuid;

    public Person() {

        super();
    }

    public Person(String surname, String patronymic, String firstName,
            UUID residence, UUID workplace, UUID contacts, UUID degree) {

        super();
        this.surname = surname;
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.residenceAdrUuid = residence;
        this.workplaceUuid = workplace;
        this.contactUuid = contacts;
        this.academicInfoUuid = degree;
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

    public UUID getResidenceAdrUuid() {

        return residenceAdrUuid;
    }

    public void setResidenceAdrUuid(UUID residenceAdrUuid) {

        this.residenceAdrUuid = residenceAdrUuid;
    }

    public UUID getWorkplaceUuid() {

        return workplaceUuid;
    }

    public void setWorkplaceUuid(UUID workplaceUuid) {

        this.workplaceUuid = workplaceUuid;
    }

    public UUID getContactUuid() {

        return contactUuid;
    }

    public void setContactUuid(UUID contactUuid) {

        this.contactUuid = contactUuid;
    }

    public UUID getAcademicInfoUuid() {

        return academicInfoUuid;
    }

    public void setAcademicInfoUuid(UUID academicInfoUuid) {

        this.academicInfoUuid = academicInfoUuid;
    }

}
