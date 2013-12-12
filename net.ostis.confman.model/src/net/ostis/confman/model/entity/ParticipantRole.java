package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "partrole")
public class ParticipantRole {

    private Boolean programCommitteeMember;

    private String  participationForm;

    private Boolean exhibitionStand;

    public ParticipantRole() {

        super();
    }

    
    public Boolean getProgramCommitteeMember() {
    
        return programCommitteeMember;
    }

    
    public void setProgramCommitteeMember(Boolean programCommitteeMember) {
    
        this.programCommitteeMember = programCommitteeMember;
    }

    
    public String getParticipationForm() {
    
        return participationForm;
    }

    
    public void setParticipationForm(String participationForm) {
    
        this.participationForm = participationForm;
    }

    
    public Boolean getExhibitionStand() {
    
        return exhibitionStand;
    }

    
    public void setExhibitionStand(Boolean exhibitionStand) {
    
        this.exhibitionStand = exhibitionStand;
    }
}
