package net.ostis.confman.model.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
public class Report {

    private String     title;

    private Long       sectionId;

    private List<Long> participants;

    private Long       reporter;

    public Report() {

        super();
    }

    @XmlElement
    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    @XmlElement(name = "section")
    public Long getSectionId() {

        return this.sectionId;
    }

    public void setSectionId(final Long sectionId) {

        this.sectionId = sectionId;
    }

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    public List<Long> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<Long> participants) {

        this.participants = participants;
    }

    public Long getReporter() {

        return this.reporter;
    }

    public void setReporter(final Long reporter) {

        this.reporter = reporter;
    }
}
