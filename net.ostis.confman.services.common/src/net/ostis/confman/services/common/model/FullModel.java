package net.ostis.confman.services.common.model;

import java.util.List;

public class FullModel {

    private List<Conference>  conferences;

    private List<Section>     sections;

    private List<Participant> participants;

    private List<Person>      persons;

    private List<Report>      reports;

    public FullModel() {

        super();
    }

    public List<Conference> getConferences() {

        return this.conferences;
    }

    public void setConferences(final List<Conference> conferences) {

        this.conferences = conferences;
    }

    public List<Section> getSections() {

        return this.sections;
    }

    public void setSections(final List<Section> sections) {

        this.sections = sections;
    }

    public List<Participant> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<Participant> participants) {

        this.participants = participants;
    }

    public List<Person> getPersons() {

        return this.persons;
    }

    public void setPersons(final List<Person> persons) {

        this.persons = persons;
    }

    public List<Report> getReports() {

        return this.reports;
    }

    public void setReports(final List<Report> reports) {

        this.reports = reports;
    }

}
