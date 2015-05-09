package net.ostis.confman.model.entity.scmemory;

public class ParticipantArrival extends BaseEntity {

    private Boolean housing;

    private Boolean meeting;

    private SystemAddress residencePlace;

    private Boolean needHostelReservation;

    public ParticipantArrival() {

        super();
    }

    public ParticipantArrival(Boolean housing, Boolean meeting,
            SystemAddress residencePlace, Boolean needHostelReservation) {
     
        super();
        this.housing = housing;
        this.meeting = meeting;
        this.residencePlace = residencePlace;
        this.needHostelReservation = needHostelReservation;
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
        
        return this.residencePlace;
    }

    public void setResidencePlace(final SystemAddress residencePlace) {

        this.residencePlace = residencePlace;
    }

    public Boolean getNeedHostelReservation() {

        return this.needHostelReservation;
    }

    public void setNeedHostelReservation(final Boolean needHostelReservation) {

        this.needHostelReservation = needHostelReservation;
    }
}
