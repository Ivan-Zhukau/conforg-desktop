package net.ostis.confman.services.common.model;

public enum AcademicTitle {

    SENIOR("Senior Researcher"),
    DOCENT("Docent"),
    PROFESSOR("Professor"),
    NON("non");

    private String academicTitle;

    private AcademicTitle(final String academicTitle) {

        this.academicTitle = academicTitle;
    }

    public String getAcademicTitle() {

        return this.academicTitle;
    }

}
