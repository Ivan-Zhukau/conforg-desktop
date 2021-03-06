package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private ParticipantRole           role;

    private ParticipantArrival        arrival;

    private ParticipationInConference participationInConference;

    private List<Report>              reports;

    private Conference                conference;

    private Person                    person;

    public Participant() {

        super();
        this.role = new ParticipantRole();
        this.arrival = new ParticipantArrival();
        this.participationInConference = new ParticipationInConference();
        this.conference = new Conference();
        this.person = new Person();
        this.reports = new ArrayList<>();
    }

    public ParticipantRole getRole() {

        return this.role;
    }

    public void setRole(final ParticipantRole role) {

        this.role = role;
    }

    public ParticipantArrival getArrival() {

        return this.arrival;
    }

    public void setArrival(final ParticipantArrival arrival) {

        this.arrival = arrival;
    }

    public Conference getConference() {

        return this.conference;
    }

    public void setConference(final Conference conference) {

        this.conference = conference;
    }

    public Person getPerson() {

        return this.person;
    }

    public void setPerson(final Person person) {

        this.person = person;
    }

    public List<Report> getReports() {

        return this.reports;
    }

    public void setReports(final List<Report> reports) {

        this.reports = reports;
    }

    @Override
    public String toString() {

        return getPerson().getFullName();
    }

    public ParticipationInConference getParticipationInConference() {

        return this.participationInConference;
    }

    public void setParticipationInConference(
            final ParticipationInConference participationInConference) {

        this.participationInConference = participationInConference;
    }
}
