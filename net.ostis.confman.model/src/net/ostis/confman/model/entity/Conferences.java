package net.ostis.confman.model.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "conferences")
public class Conferences {

    private List<Conference> conferences;

    public Conferences() {

        super();
    }

    @XmlElement(name = "conference")
    public List<Conference> getConferences() {

        return this.conferences;
    }

    public void setConferences(final List<Conference> conferences) {

        this.conferences = conferences;
    }
}
