package net.ostis.confman.services.common.model;

public class ParticipantRole {

    private Boolean programCommitteeMember = false;

    private String  participationForm;
    
    private String  participationCategory;

    private Boolean exibitionStand         = false;

    public ParticipantRole() {

        super();
    }

    public Boolean getProgramCommitteeMember() {

        return this.programCommitteeMember;
    }

    public void setProgramCommitteeMember(final Boolean programCommitteeMember) {

        this.programCommitteeMember = programCommitteeMember;
    }

    public String getParticipationForm() {

        return this.participationForm;
    }

    public void setParticipationForm(final String participationForm) {

        this.participationForm = participationForm;
    }

    public Boolean getExhibitionStand() {

        return this.exibitionStand;
    }

    public void setExibitionStand(final Boolean exibitionStand) {

        this.exibitionStand = exibitionStand;
    }

    public String getParticipationCategory() {

        return participationCategory;
    }

    public void setParticipationCategory(String participationCategory) {

        this.participationCategory = participationCategory;
    }
}
