package net.ostis.confman.services.common.model;

public class ConferenceModel {

    private Persons      persons;

    private Conferences  conferences;

    private Participants participants;

    private Sections     sections;

    private Reports      reports;

    public ConferenceModel() {

        super();
    }

    public Persons getPersons() {

        return this.persons;
    }

    public void setPersons(final Persons persons) {

        this.persons = persons;
    }

    public Conferences getConferences() {

        return this.conferences;
    }

    public void setConferences(final Conferences conferences) {

        this.conferences = conferences;
    }

    public Participants getParticipants() {

        return this.participants;
    }

    public void setParticipants(final Participants participants) {

        this.participants = participants;
    }

    public Sections getSections() {

        return this.sections;
    }

    public void setSections(final Sections sections) {

        this.sections = sections;
    }

    public Reports getReports() {

        return this.reports;
    }

    public void setReports(final Reports reports) {

        this.reports = reports;
    }
}
