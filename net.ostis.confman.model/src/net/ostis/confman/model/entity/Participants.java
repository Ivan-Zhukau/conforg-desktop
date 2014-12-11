package net.ostis.confman.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participants")
public class Participants {

    private List<Participant> participants;

    public Participants() {

        super();
        participants = new ArrayList<Participant>();
    }

    @XmlElement(name = "participant")
    public List<Participant> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<Participant> participants) {

        this.participants = participants;
    }

}
