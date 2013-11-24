package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workplace")
public class WorkplaceInformation {

    private String workplace;

    private String position;

    public WorkplaceInformation() {

        super();
    }

    @XmlElement
    public String getWorkplace() {

        return this.workplace;
    }

    public void setWorkplace(final String workplace) {

        this.workplace = workplace;
    }

    @XmlElement
    public String getPosition() {

        return this.position;
    }

    public void setPosition(final String position) {

        this.position = position;
    }
}
