package net.ostis.confman.model.entity;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "section")
public class Section {

    private Long       id;

    private String     title;

    private Date       date;

    private Long       conferenceId;

    private List<Long> reports;

    public Section() {

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

    @XmlElement
    public Date getDate() {

        return this.date;
    }

    public void setDate(final Date date) {

        this.date = date;
    }

    @XmlElement(name = "conference")
    public Long getConferenceId() {

        return this.conferenceId;
    }

    public void setConferenceId(final Long conferenceId) {

        this.conferenceId = conferenceId;
    }

    @XmlElementWrapper(name = "reports")
    @XmlElement(name = "report")
    public List<Long> getReports() {

        return this.reports;
    }

    public void setReports(final List<Long> reports) {

        this.reports = reports;
    }
}
