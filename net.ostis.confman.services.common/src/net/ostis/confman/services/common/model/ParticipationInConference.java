package net.ostis.confman.services.common.model;

public class ParticipationInConference {

    private Boolean exhibitionPresentationOfeports = false;

    private Boolean tourOfTheCityOfMinsk           = false;

    private Boolean culturalProgram                = false;

    private Boolean eveningMeetingPC               = false;

    public Boolean getExhibitionPresentationOfeports() {

        return this.exhibitionPresentationOfeports;
    }

    public void setExhibitionPresentationOfeports(
            final Boolean exhibitionPresentationOfeports) {

        this.exhibitionPresentationOfeports = exhibitionPresentationOfeports;
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
