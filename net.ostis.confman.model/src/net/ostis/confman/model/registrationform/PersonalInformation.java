package net.ostis.confman.model.registrationform;

public class PersonalInformation {

    private String secondName;

    private String firstName;

    private String thirdName;

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

    public String getFirstName() {

        return this.firstName;
    }

    public String getSecondName() {

        return this.secondName;
    }

    public String getThirdName() {

        return this.thirdName;
    }

    public void setAcademicDegree(final String academicDegree) {

        this.academicDegree = academicDegree;
    }

    public void setAcademicTitle(final String academicTitle) {

        this.academicTitle = academicTitle;
    }

    public void setFirstName(final String firstName) {

        this.firstName = firstName;
    }

    public void setSecondName(final String secondName) {

        this.secondName = secondName;
    }

    public void setThirdName(final String thirdName) {

        this.thirdName = thirdName;
    }

}
