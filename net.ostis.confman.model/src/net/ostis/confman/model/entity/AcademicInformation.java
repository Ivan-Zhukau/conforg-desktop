package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "acdegree")
public class AcademicInformation {

    private String title;

    private String degree;

    public AcademicInformation() {

        super();
    }

    @XmlElement
    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    @XmlElement
    public String getDegree() {

        return this.degree;
    }

    public void setDegree(final String degree) {

        this.degree = degree;
    }
}
