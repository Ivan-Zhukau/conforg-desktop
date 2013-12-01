package net.ostis.confman.model.entity;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "conference")
public class Conference {

    private String     title;

    private String     conferenceVenue;

    private Date       startDate;

    private Date       endDate;

    private Address    residence;

    private List<Long> sections;

    private List<Long> reports;

    private List<Long> participants;

    public Conference() {

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
    public Date getStartDate() {

        return this.startDate;
    }

    public void setStartDate(final Date startDate) {

        this.startDate = startDate;
    }

    @XmlElement
    public Date getEndDate() {

        return this.endDate;
    }

    public void setEndDate(final Date endDate) {

        this.endDate = endDate;
    }

    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "section")
    public List<Long> getSections() {

        return this.sections;
    }

    public void setSections(final List<Long> sections) {

        this.sections = sections;
    }

    @XmlElementWrapper(name = "reports")
    @XmlElement(name = "report")
    public List<Long> getReports() {

        return this.reports;
    }

    public void setReports(final List<Long> reports) {

        this.reports = reports;
    }

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    public List<Long> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<Long> participants) {

        this.participants = participants;
    }

    @XmlElement(name = "confvenue")
    public String getConferenceVenue() {

        return this.conferenceVenue;
    }

    public void setConferenceVenue(final String conferenceVenue) {

        this.conferenceVenue = conferenceVenue;
    }

    @XmlElement(name = "address")
    public Address getResidence() {

        return this.residence;
    }

    public void setResidence(final Address residence) {

        this.residence = residence;
    }
}
