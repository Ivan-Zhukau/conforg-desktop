package net.ostis.confman.services.common.model;

public class AcademicInformation {

    private String title;

    private String degree;

    public AcademicInformation() {

        super();
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public String getDegree() {

        return this.degree;
    }

    public void setDegree(final String degree) {

        this.degree = degree;
    }
}
