package net.ostis.confman.model.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participant")
public class Participant {

    private Long               id;

    private ParticipantRole    role;

    private ParticipantArrival arrival;

    private List<Long>         reportId;

    private Long               conferenceId;

    private Long               personId;

    public Participant() {

        super();
    }

    @XmlElement
    public Long getId() {

        return this.id;
    }

    public void setId(final Long id) {

        this.id = id;
    }

    @XmlElement(name = "partrole")
    public ParticipantRole getRole() {

        return this.role;
    }

    public void setRole(final ParticipantRole role) {

        this.role = role;
    }

    @XmlElement(name = "partarrival")
    public ParticipantArrival getArrival() {

        return this.arrival;
    }

    public void setArrival(final ParticipantArrival arrival) {

        this.arrival = arrival;
    }

    @XmlElement(name = "reports")
    public List<Long> getReportId() {

        return this.reportId;
    }

    public void setReportId(final List<Long> reportId) {

        this.reportId = reportId;
    }

    @XmlElement
    public Long getConferenceId() {

        return this.conferenceId;
    }

    public void setConferenceId(final Long conferenceId) {

        this.conferenceId = conferenceId;
    }

    @XmlElement
    public Long getPersonId() {

        return this.personId;
    }

    public void setPersonId(final Long personId) {

        this.personId = personId;
    }
}
