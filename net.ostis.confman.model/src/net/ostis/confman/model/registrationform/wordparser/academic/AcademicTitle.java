package net.ostis.confman.model.registrationform.wordparser.academic;

public enum AcademicTitle{

    SENIOR("senior"),
    DOCENT("docent"),
    PROFESSOR("professor"),
    NONE("none");

    private String academicTitle;

    private AcademicTitle(final String academicTitle) {

        this.academicTitle = academicTitle;
    }
    
    public String getValue(){
        return this.academicTitle;
    }
}
