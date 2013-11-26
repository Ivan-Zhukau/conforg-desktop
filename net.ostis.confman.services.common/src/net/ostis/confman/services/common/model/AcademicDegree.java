package net.ostis.confman.services.common.model;

public enum AcademicDegree {

    CANDIDATE("candidate of sciences"),
    DOCTOR("doctor of Science"),
    NON("non");

    private String academicDegree;

    private AcademicDegree(final String academicDegree) {

        this.academicDegree = academicDegree;
    }

    public String getAcademicDegree() {

        return this.academicDegree;
    }

}
