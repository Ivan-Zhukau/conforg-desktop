package by.ostis.common.conforg.model.entity;

import java.util.UUID;

public class ParticipantAdditionalInfo extends BaseEntity {

    private Boolean housing;
    private Boolean meeting;
    private UUID address;
    private Boolean culturalProgramm;
    private Boolean eveningMeetingPC;
    private Boolean exhibitionPresentationOfReports;
    private Boolean tourOfTheCityOfMinsk;
    private Boolean exhibitionStand;
    private Boolean programCommitteeMember;

    private ParticipantAdditionalInfo(Boolean housing, Boolean meeting, UUID address,
                                      Boolean culturalProgramm, Boolean eveningMeetingPC,
                                      Boolean exhibitionPresentationOfReports, Boolean tourOfTheCityOfMinsk,
                                      Boolean exhibitionStand, Boolean programCommitteeMember) {
        super();
        this.housing = housing;
        this.meeting = meeting;
        this.address = address;
        this.culturalProgramm = culturalProgramm;
        this.eveningMeetingPC = eveningMeetingPC;
        this.exhibitionPresentationOfReports = exhibitionPresentationOfReports;
        this.tourOfTheCityOfMinsk = tourOfTheCityOfMinsk;
        this.exhibitionStand = exhibitionStand;
        this.programCommitteeMember = programCommitteeMember;
    }

    public Boolean getHousing() {
        return housing;
    }

    public Boolean getMeeting() {
        return meeting;
    }

    public UUID getAddress() {
        return address;
    }

    public Boolean getCulturalProgramm() {
        return culturalProgramm;
    }

    public Boolean getEveningMeetingPC() {
        return eveningMeetingPC;
    }

    public Boolean getExhibitionPresentationOfReports() {
        return exhibitionPresentationOfReports;
    }

    public Boolean getTourOfTheCityOfMinsk() {
        return tourOfTheCityOfMinsk;
    }

    public Boolean getExhibitionStand() {
        return exhibitionStand;
    }

    public Boolean getProgramCommitteeMember() {
        return programCommitteeMember;
    }

    public static class ParticipantAdditionalInfoBuilder {

        private Boolean housing;
        private Boolean meeting;
        private UUID address;
        private Boolean culturalProgramm;
        private Boolean eveningMeetingPC;
        private Boolean exhibitionPresentationOfReports;
        private Boolean tourOfTheCityOfMinsk;
        private Boolean exhibitionStand;
        private Boolean programCommitteeMember;

        public ParticipantAdditionalInfoBuilder setHousing(Boolean housing) {
            this.housing = housing;
            return this;
        }

        public ParticipantAdditionalInfoBuilder setMeeting(Boolean meeting) {
            this.meeting = meeting;
            return this;
        }

        public ParticipantAdditionalInfoBuilder setAddress(UUID address) {
            this.address = address;
            return this;
        }

        public ParticipantAdditionalInfoBuilder setCulturalProgramm(Boolean culturalProgramm) {
            this.culturalProgramm = culturalProgramm;
            return this;
        }

        public ParticipantAdditionalInfoBuilder setEveningMeetingPC(Boolean eveningMeetingPC) {
            this.eveningMeetingPC = eveningMeetingPC;
            return this;
        }

        public ParticipantAdditionalInfoBuilder setExhibitionPresentationOfReports(Boolean exhibitionPresentationOfReports) {
            this.exhibitionPresentationOfReports = exhibitionPresentationOfReports;
            return this;
        }

        public ParticipantAdditionalInfoBuilder setTourOfTheCityOfMinsk(Boolean tourOfTheCityOfMinsk) {
            this.tourOfTheCityOfMinsk = tourOfTheCityOfMinsk;
            return this;
        }

        public ParticipantAdditionalInfoBuilder setExhibitionStand(Boolean exhibitionStand) {
            this.exhibitionStand = exhibitionStand;
            return this;
        }

        public ParticipantAdditionalInfoBuilder setProgramCommitteeMember(Boolean programCommitteeMember) {
            this.programCommitteeMember = programCommitteeMember;
            return this;
        }

        public ParticipantAdditionalInfo build() {
            return new ParticipantAdditionalInfo(housing, meeting, address, culturalProgramm,
                    eveningMeetingPC, exhibitionPresentationOfReports, tourOfTheCityOfMinsk,
                    exhibitionStand, programCommitteeMember);
        }
    }
}
