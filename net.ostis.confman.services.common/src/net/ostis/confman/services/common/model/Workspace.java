package net.ostis.confman.services.common.model;


public class Workspace {

    private ConferenceViewState conferencePartState;

    public Workspace() {

        super();
    }

    public ConferenceViewState getConferencePartState() {
    
        return conferencePartState;
    }

    public void setConferencePartState(ConferenceViewState conferencePartState) {
    
        this.conferencePartState = conferencePartState;
    }

}
