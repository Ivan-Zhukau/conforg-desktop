package net.ostis.confman.model.entity.scmemory;

public class ParticipantRole extends BaseEntity {

    private Boolean programCommitteeMember;

    private String  participationForm;

    private String  participationCategory;

    private Boolean exhibitionStand;

    public ParticipantRole() {

        super();
    }

    public ParticipantRole(Boolean programCommitteeMember,
            String participationForm, String participationCategory,
            Boolean exhibitionStand) {

        super();
        this.programCommitteeMember = programCommitteeMember;
        this.participationForm = participationForm;
        this.participationCategory = participationCategory;
        this.exhibitionStand = exhibitionStand;
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

        return this.exhibitionStand;
    }

    public void setExhibitionStand(final Boolean exhibitionStand) {

        this.exhibitionStand = exhibitionStand;
    }

    public String getParticipationCategory() {

        return this.participationCategory;
    }

    public void setParticipationCategory(final String participationCategory) {

        this.participationCategory = participationCategory;
    }
}
