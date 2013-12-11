package net.ostis.confman.ui.participant;

import net.ostis.confman.ui.common.Localizable;

public enum AcademicDegree implements Localizable {

    CANDIDATE("candidate"),
    DOCTOR("doctor"),
    NONE("none");

    private String academicDegree;

    private AcademicDegree(final String academicDegree) {

        this.academicDegree = academicDegree;
    }

    @Override
    public String getResourceKey() {

        return this.academicDegree;
    }
}
