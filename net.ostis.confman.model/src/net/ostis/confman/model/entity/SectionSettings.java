package net.ostis.confman.model.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "section-report-data")
public class SectionSettings {

    private List<SectionBreaks> sectionBreaks;

    public SectionSettings() {

        super();
    }

    @XmlElement(name = "section-report-break")
    public List<SectionBreaks> getSectionBreaks() {

        return this.sectionBreaks;
    }

    public void setSectionBreaks(final List<SectionBreaks> sectionBreaks) {

        this.sectionBreaks = sectionBreaks;
    }

}
