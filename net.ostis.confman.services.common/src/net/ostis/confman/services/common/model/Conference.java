package net.ostis.confman.services.common.model;

import java.util.Date;
import java.util.List;

public class Conference {

    private String            title;

    private Date              startDate;

    private Date              endDate;

    private List<Section>     sections;

    private List<Report>      reports;

    private List<Participant> participants;

    private String            conferenceVenue;

    public Conference() {

        super();
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public Date getStartDate() {

        return this.startDate;
    }

    public void setStartDate(final Date startDate) {

        this.startDate = startDate;
    }

    public Date getEndDate() {

        return this.endDate;
    }

    public void setEndDate(final Date endDate) {

        this.endDate = endDate;
    }

    public List<Section> getSections() {

        return this.sections;
    }

    public void setSections(final List<Section> sections) {

        this.sections = sections;
    }

    public List<Report> getReports() {

        return this.reports;
    }

    public void setReports(final List<Report> reports) {

        this.reports = reports;
    }

    public List<Participant> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<Participant> participants) {

        this.participants = participants;
    }

    public String getConferenceVenue() {

        return this.conferenceVenue;
    }

    public void setConferenceVenue(final String conferenceVenue) {

        this.conferenceVenue = conferenceVenue;
    }
}
