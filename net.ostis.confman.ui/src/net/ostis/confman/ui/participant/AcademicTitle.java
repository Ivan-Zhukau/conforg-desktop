package net.ostis.confman.ui.participant;

import net.ostis.confman.ui.common.Localizable;

public enum AcademicTitle implements Localizable {

    SENIOR("senior"),
    DOCENT("docent"),
    PROFESSOR("professor"),
    NONE("none");

    private String academicTitle;

    private AcademicTitle(final String academicTitle) {

        this.academicTitle = academicTitle;
    }

    @Override
    public String getResourceKey() {

        return this.academicTitle;
    }
}
