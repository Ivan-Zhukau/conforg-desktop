package net.ostis.confman.model.entity.scmemory;

public class AcademicInformation extends BaseEntity {

    private String title;

    private String degree;

    public AcademicInformation() {

        super();
    }
    
    public AcademicInformation(String title, String degree) {
        
        super();
        this.title = title;
        this.degree = degree;
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
