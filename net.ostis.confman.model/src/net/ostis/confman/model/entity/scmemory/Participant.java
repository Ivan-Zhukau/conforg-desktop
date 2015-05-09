package net.ostis.confman.model.entity.scmemory;

import java.util.List;

public class Participant extends BaseEntity {

    private SystemAddress       role;

    private SystemAddress       arrival;

    private SystemAddress       SystemAddress;

    private List<SystemAddress> reportId;

    private SystemAddress       conferenceId;

    private SystemAddress       personId;

    public Participant() {

        super();
    }

    public Participant(SystemAddress role, SystemAddress arrival,
            SystemAddress SystemAddress, List<SystemAddress> reportId,
            SystemAddress conferenceId, SystemAddress personId) {

        super();
        this.role = role;
        this.arrival = arrival;
        this.SystemAddress = SystemAddress;
        this.reportId = reportId;
        this.conferenceId = conferenceId;
        this.personId = personId;
    }

    public SystemAddress getRole() {

        return this.role;
    }

    public void setRole(final SystemAddress role) {

        this.role = role;
    }

    public SystemAddress getArrival() {

        return this.arrival;
    }

    public void setArrival(final SystemAddress arrival) {

        this.arrival = arrival;
    }

    public List<SystemAddress> getReportId() {

        return this.reportId;
    }

    public void setReportId(final List<SystemAddress> reportId) {

        this.reportId = reportId;
    }

    public SystemAddress getConferenceId() {

        return this.conferenceId;
    }

    public void setConferenceId(final SystemAddress conferenceId) {

        this.conferenceId = conferenceId;
    }

    public SystemAddress getPersonId() {

        return this.personId;
    }

    public void setPersonId(final SystemAddress personId) {

        this.personId = personId;
    }

    public SystemAddress getSystemAddress() {

        return this.SystemAddress;
    }

    public void setSystemAddress(final SystemAddress SystemAddress) {

        this.SystemAddress = SystemAddress;
    }
}
