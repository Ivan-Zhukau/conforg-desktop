package net.ostis.confman.model.registrationform.wordparser.academic;

public enum AcademicDegree {

    CANDIDATE("candidate"),
    DOCTOR("doctor"),
    NONE("none");

    private String academicDegree;

    private AcademicDegree(final String academicDegree) {

        this.academicDegree = academicDegree;
    }
    
    public String getValue(){
        return this.academicDegree;
    }
}
