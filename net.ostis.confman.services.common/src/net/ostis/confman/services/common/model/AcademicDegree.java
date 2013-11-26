package net.ostis.confman.services.common.model;

public enum AcademicDegree {

    CANDIDATE("Candidate of Sciences"),
    DOCTOR("Doctor of Science"),
    NONE("None");

    private String academicDegree;

    private AcademicDegree(final String academicDegree) {

        this.academicDegree = academicDegree;
    }

    public String getAcademicDegree() {

        return this.academicDegree;
    }

}
