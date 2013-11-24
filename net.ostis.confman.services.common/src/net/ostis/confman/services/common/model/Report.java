package net.ostis.confman.services.common.model;

import java.util.List;

public class Report {

    private String            title;

    private Section           section;

    private List<Participant> participants;

    private Participant       participant;

    public Report() {

        super();
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public List<Participant> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<Participant> participants) {

        this.participants = participants;
    }

    public Participant getReporter() {

        return this.participant;
    }

    public void setReporter(final Participant participant) {

        this.participant = participant;
    }

    public Section getSection() {

        return this.section;
    }

    public void setSection(final Section section) {

        this.section = section;
    }
}
