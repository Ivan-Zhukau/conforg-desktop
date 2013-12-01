package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "partarrival")
public class ParticipantArrival {

    private Boolean housing;

    private Boolean meeting;

    private Address residencePlace;

    public ParticipantArrival() {

        super();
    }

    @XmlElement
    public Boolean getHousing() {

        return this.housing;
    }

    public void setHousing(final Boolean housing) {

        this.housing = housing;
    }

    @XmlElement
    public Boolean getMeeting() {

        return this.meeting;
    }

    public void setMeeting(final Boolean meeting) {

        this.meeting = meeting;
    }

    @XmlElement(name = "address")
    public Address getResidencePlace() {

        return this.residencePlace;
    }

    public void setResidencePlace(final Address residencePlace) {

        this.residencePlace = residencePlace;
    }
}
