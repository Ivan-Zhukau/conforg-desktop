package net.ostis.confman.model.entity.xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workspace")
public class Workspace {

    private ConferenceViewState conferencePartState;

    public Workspace() {
        super();
        conferencePartState = new ConferenceViewState();
    }

    public ConferenceViewState getConferencePartState() {
    
        return conferencePartState;
    }

    public void setConferencePartState(ConferenceViewState conferencePartState) {
    
        this.conferencePartState = conferencePartState;
    }

}
