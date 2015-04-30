package by.ostis.common.conforg.model.entity;

public class AcademicDegree extends BaseEntity {

    private String degree;
    private String title;

    public AcademicDegree() {
        super();
    }

    public AcademicDegree(String degree, String title) {
        super();
        this.degree = degree;
        this.title = title;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
