package net.ostis.confman.services.editing;

public class PersonalInformation {

	private String surname;

    private String name;

    private String patronymic;

    private String academicDegree;

    private String academicTitle;

    public PersonalInformation() {

        super();
    }

    public String getAcademicDegree() {

        return this.academicDegree;
    }

    public String getAcademicTitle() {

        return this.academicTitle;
    }

    public String getName() {

        return this.name;
    }

    public String getSurname() {

        return this.surname;
    }

    public String getPatronymic () {

        return this.patronymic;
    }

    public void setAcademicDegree(final String academicDegree) {

        this.academicDegree = academicDegree;
    }

    public void setAcademicTitle(final String academicTitle) {

        this.academicTitle = academicTitle;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public void setSurname(final String surname) {

        this.surname = surname;
    }

    public void setPatronymic(final String patronymic) {

        this.patronymic = patronymic;
    }

}
