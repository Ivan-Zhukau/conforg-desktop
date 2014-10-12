package net.ostis.confman.services.common.model;


public class ParticipationInConference {

    private Boolean exhibitionPresentationOfeports = false;
    
    private Boolean tourOfTheCityOfMinsk = false;
    
    private Boolean culturalProgram = false;
    
    private Boolean eveningMeetingPC = false;
    
    public Boolean getExhibitionPresentationOfeports() {

        return exhibitionPresentationOfeports;
    }
    
    public void setExhibitionPresentationOfeports(
            Boolean exhibitionPresentationOfeports) {

        this.exhibitionPresentationOfeports = exhibitionPresentationOfeports;
    }
    
    public Boolean getTourOfTheCityOfMinsk() {

        return tourOfTheCityOfMinsk;
    }
    
    public void setTourOfTheCityOfMinsk(Boolean tourOfTheCityOfMinsk) {

        this.tourOfTheCityOfMinsk = tourOfTheCityOfMinsk;
    }
    
    public Boolean getCulturalProgram() {

        return culturalProgram;
    }
    
    public void setCulturalProgram(Boolean culturalProgram) {

        this.culturalProgram = culturalProgram;
    }
    
    public Boolean getEveningMeetingPC() {

        return eveningMeetingPC;
    }
    
    public void setEveningMeetingPC(Boolean eveningMeetingPC) {

        this.eveningMeetingPC = eveningMeetingPC;
    }
    
}
