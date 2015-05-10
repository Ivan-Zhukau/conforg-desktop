package net.ostis.confman.model.entity.scmemory;

public class ParticipationInConference extends BaseEntity {

    private Boolean exhibitionPresentationOfReports;

    private Boolean tourOfTheCityOfMinsk;

    private Boolean culturalProgram;

    private Boolean eveningMeetingPC;

    public ParticipationInConference() {

        super();
    }

    public ParticipationInConference(Boolean exhibitionPresentationOfReports,
            Boolean tourOfTheCityOfMinsk, Boolean culturalProgram,
            Boolean eveningMeetingPC) {

        super();
        this.exhibitionPresentationOfReports = exhibitionPresentationOfReports;
        this.tourOfTheCityOfMinsk = tourOfTheCityOfMinsk;
        this.culturalProgram = culturalProgram;
        this.eveningMeetingPC = eveningMeetingPC;
    }

    public Boolean getExhibitionPresentationOfReports() {

        return this.exhibitionPresentationOfReports;
    }

    public void setExhibitionPresentationOfReports(
            final Boolean exhibitionPresentationOfReports) {

        this.exhibitionPresentationOfReports = exhibitionPresentationOfReports;
    }

    public Boolean getTourOfTheCityOfMinsk() {

        return this.tourOfTheCityOfMinsk;
    }

    public void setTourOfTheCityOfMinsk(final Boolean tourOfTheCityOfMinsk) {

        this.tourOfTheCityOfMinsk = tourOfTheCityOfMinsk;
    }

    public Boolean getCulturalProgram() {

        return this.culturalProgram;
    }

    public void setCulturalProgram(final Boolean culturalProgram) {

        this.culturalProgram = culturalProgram;
    }

    public Boolean getEveningMeetingPC() {

        return this.eveningMeetingPC;
    }

    public void setEveningMeetingPC(final Boolean eveningMeetingPC) {

        this.eveningMeetingPC = eveningMeetingPC;
    }

}