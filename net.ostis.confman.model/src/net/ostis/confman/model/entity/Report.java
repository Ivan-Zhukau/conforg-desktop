package net.ostis.confman.model.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
public class Report {

    private Long       id;

    private String     title;

    private Long       sectionId;

    private List<Long> participants;

    private Long       reporter;

    private Boolean    youngScientistReport;

    private Boolean    accepted;

    private Boolean    canceled;

    public Report() {

        super();
    }

    @XmlElement
    public Long getId() {

        return this.id;
    }

    public void setId(final Long id) {

        this.id = id;
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

    @XmlElement(name = "young-sc-rep")
    public Boolean getYoungScientistReport() {

        return this.youngScientistReport;
    }

    public void setYoungScientistReport(final Boolean youngScientistReport) {

        this.youngScientistReport = youngScientistReport;
    }

    public Boolean getAccepted() {

        return this.accepted;
    }

    public void setAccepted(final Boolean accepted) {

        this.accepted = accepted;
    }

    public Boolean getCanceled() {

        return this.canceled;
    }

    public void setCanceled(final Boolean canceled) {

        this.canceled = canceled;
    }
}
