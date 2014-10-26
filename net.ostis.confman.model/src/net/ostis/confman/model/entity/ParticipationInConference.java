package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participation")
public class ParticipationInConference {

    private Boolean exhibitionPresentationOfeports;
    
    private Boolean tourOfTheCityOfMinsk;
    
    private Boolean culturalProgram;
    
    private Boolean eveningMeetingPC;

    public ParticipationInConference() {

        super();
    }    
    
    @XmlElement
    public Boolean getExhibitionPresentationOfeports() {

        return exhibitionPresentationOfeports;
    }
    
    public void setExhibitionPresentationOfeports(
            Boolean exhibitionPresentationOfeports) {

        this.exhibitionPresentationOfeports = exhibitionPresentationOfeports;
    }
    
    @XmlElement
    public Boolean getTourOfTheCityOfMinsk() {

        return tourOfTheCityOfMinsk;
    }
    
    public void setTourOfTheCityOfMinsk(Boolean tourOfTheCityOfMinsk) {

        this.tourOfTheCityOfMinsk = tourOfTheCityOfMinsk;
    }
    
    @XmlElement
    public Boolean getCulturalProgram() {

        return culturalProgram;
    }
    
    public void setCulturalProgram(Boolean culturalProgram) {

        this.culturalProgram = culturalProgram;
    }
    
    @XmlElement
    public Boolean getEveningMeetingPC() {

        return eveningMeetingPC;
    }
    
    public void setEveningMeetingPC(Boolean eveningMeetingPC) {

        this.eveningMeetingPC = eveningMeetingPC;
    }

}
