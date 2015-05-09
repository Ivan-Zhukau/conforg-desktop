package net.ostis.confman.model.entity.scmemory;

public class ParticipantArrival extends BaseEntity {

    private Boolean housing;

    private Boolean meeting;

    private SystemAddress residencePlaceAdr;

    private Boolean isHostelReservation;

    public ParticipantArrival() {

        super();
    }

    public ParticipantArrival(Boolean housing, Boolean meeting,
            SystemAddress residencePlace, Boolean isHostelReservation) {

        super();
        this.housing = housing;
        this.meeting = meeting;
        this.residencePlaceAdr = residencePlace;
        this.isHostelReservation = isHostelReservation;
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
    
    public SystemAddress getResidencePlace() {

        return this.residencePlaceAdr;
    }

    public void setResidencePlace(final SystemAddress residencePlace) {

        this.residencePlaceAdr = residencePlace;
    }

    public Boolean getIsHostelReservation() {

        return this.isHostelReservation;
    }

    public void setIsHostelReservation(final Boolean isHostelReservation) {

        this.isHostelReservation = isHostelReservation;
    }
}
