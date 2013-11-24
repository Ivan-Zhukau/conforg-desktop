package net.ostis.confman.services.common.model;

public class ParticipantRole {

    private Boolean programCommitteeMember;

    private String  participationForm;

    private Boolean exibitionStand;

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
}
