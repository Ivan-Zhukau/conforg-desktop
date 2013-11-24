package net.ostis.confman.services.common.model;

import java.util.List;

public class Conferences {

    private List<Conference> conferences;

    public Conferences() {

        super();
    }

    public List<Conference> getConferences() {

        return this.conferences;
    }

    public void setConferences(final List<Conference> conferences) {

        this.conferences = conferences;
    }
}
