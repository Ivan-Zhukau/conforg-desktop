package net.ostis.confman.model.entity.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participants")
public class Participants {

    private List<Participant> participants;

    public Participants() {

        super();
    }

    @XmlElement(name = "participant")
    public List<Participant> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<Participant> participants) {

        this.participants = participants;
    }

}
