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

        return this.exhibitionPresentationOfeports;
    }

    public void setExhibitionPresentationOfeports(
            final Boolean exhibitionPresentationOfeports) {

        this.exhibitionPresentationOfeports = exhibitionPresentationOfeports;
    }

    @XmlElement
    public Boolean getTourOfTheCityOfMinsk() {

        return this.tourOfTheCityOfMinsk;
    }

    public void setTourOfTheCityOfMinsk(final Boolean tourOfTheCityOfMinsk) {

        this.tourOfTheCityOfMinsk = tourOfTheCityOfMinsk;
    }

    @XmlElement
    public Boolean getCulturalProgram() {

        return this.culturalProgram;
    }

    public void setCulturalProgram(final Boolean culturalProgram) {

        this.culturalProgram = culturalProgram;
    }

    @XmlElement
    public Boolean getEveningMeetingPC() {

        return this.eveningMeetingPC;
    }

    public void setEveningMeetingPC(final Boolean eveningMeetingPC) {

        this.eveningMeetingPC = eveningMeetingPC;
    }

}
