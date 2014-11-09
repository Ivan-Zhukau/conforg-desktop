package net.ostis.confman.services.common.model;

import java.util.List;


public class ConferenceViewState {

    private List<Conference> openedConferences;

    public ConferenceViewState() {

        super();
    }

    public List<Conference> getOpenedConferences() {
    
        return openedConferences;
    }

    public void setOpenedConferences(List<Conference> openedConferences) {
    
        this.openedConferences = openedConferences;
    }

}
