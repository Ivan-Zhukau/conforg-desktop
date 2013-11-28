package net.ostis.confman.services.common.model;

public class ParticipantArrival {

    private Boolean housing;

    private Boolean meeting;

    private Address residencePlace;

    public ParticipantArrival() {

        super();
    }

    public Boolean getHousing() {

        return this.housing;
    }

    public void setHousing(final Boolean housing) {

        this.housing = housing;
    }

    public Boolean getMeeting() {

        return this.meeting;
    }

    public void setMeeting(final Boolean meeting) {

        this.meeting = meeting;
    }

    public Address getResidencePlace() {

        return this.residencePlace;
    }

    public void setResidencePlace(final Address residencePlace) {

        this.residencePlace = residencePlace;
    }
}
