package net.ostis.confman.model.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sections")
public class Sections {

    private List<Section> sections;

    public Sections() {

        super();
    }

    public Sections(final List<Section> sections) {

        super();
        this.sections = sections;
    }

    @XmlElement(name = "section")
    public List<Section> getSections() {

        return this.sections;
    }

    public void setSections(final List<Section> sections) {

        this.sections = sections;
    }
}
