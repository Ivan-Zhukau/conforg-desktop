package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "partrole")
public class ParticipantRole {

    private Boolean programCommitteeMember;

    private String  participationForm;

    private Boolean exibitionStand;

    public ParticipantRole() {

        super();
    }

    @XmlElement(name = "pcmember")
    public Boolean getProgramCommitteeMember() {

        return this.programCommitteeMember;
    }

    public void setProgramCommitteeMember(final Boolean programCommitteeMember) {

        this.programCommitteeMember = programCommitteeMember;
    }

    @XmlElement(name = "form")
    public String getParticipationForm() {

        return this.participationForm;
    }

    public void setParticipationForm(final String participationForm) {

        this.participationForm = participationForm;
    }

    @XmlElement(name = "exhibition")
    public Boolean getExhibitionStand() {

        return this.exibitionStand;
    }

    public void setExibitionStand(final Boolean exibitionStand) {

        this.exibitionStand = exibitionStand;
    }
}
