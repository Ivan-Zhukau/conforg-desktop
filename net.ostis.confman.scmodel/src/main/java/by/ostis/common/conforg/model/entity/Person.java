package by.ostis.common.conforg.model.entity;

import java.util.UUID;

public class Person extends BaseEntity {

    private String firstName;
    private String patronymic;
    private String lastName;
    private UUID address;
    private UUID workplace;
    private UUID academicDegree;

    public Person() {
        super();
    }

    public Person(String firstName, String patronymic, String lastName, UUID address, UUID workplace,
                  UUID academicDegree) {
        super();
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.address = address;
        this.workplace = workplace;
        this.academicDegree = academicDegree;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UUID getAddress() {
        return address;
    }

    public void setAddress(UUID address) {
        this.address = address;
    }

    public UUID getWorkplace() {
        return workplace;
    }

    public void setWorkplace(UUID workplace) {
        this.workplace = workplace;
    }

    public UUID getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(UUID academicDegree) {
        this.academicDegree = academicDegree;
    }
}
