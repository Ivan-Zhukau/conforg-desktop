package net.ostis.confman.services.common.model;

public class ParticipantArrival {

    private Boolean housing             = false;

    private Boolean meeting             = false;

    private Address residencePlace;

    private Boolean isHostelReservation = false;

    public ParticipantArrival() {

        super();
        this.residencePlace = new Address();
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

    public void setHostelReservation(final Boolean value) {

        this.isHostelReservation = value;

    }

    public Boolean getHostelReservation() {

        return this.isHostelReservation;
    }
}
