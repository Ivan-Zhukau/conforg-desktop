package net.ostis.confman.model.entity.scmemory;

import java.util.List;
import java.util.UUID;

public class Participant extends BaseEntity {

    private ParticipantRole           role;

    private ParticipantArrival        arrival;

    private ParticipationInConference participationInConference;

    private List<UUID>                reportId;

    private UUID                      conferenceId;

    private UUID                      personId;

    public Participant() {

        super();
    }
    
    public Participant(ParticipantRole role, ParticipantArrival arrival,
            ParticipationInConference participationInConference,
            List<UUID> reportId, UUID conferenceId, UUID personId) {

        super();
        this.role = role;
        this.arrival = arrival;
        this.participationInConference = participationInConference;
        this.reportId = reportId;
        this.conferenceId = conferenceId;
        this.personId = personId;
    }

    public ParticipantRole getRole() {

        return this.role;
    }

    public void setRole(final ParticipantRole role) {

        this.role = role;
    }

    public ParticipantArrival getArrival() {

        return this.arrival;
    }

    public void setArrival(final ParticipantArrival arrival) {

        this.arrival = arrival;
    }

    public List<UUID> getReportId() {

        return this.reportId;
    }

    public void setReportId(final List<UUID> reportId) {

        this.reportId = reportId;
    }

    public UUID getConferenceId() {

        return this.conferenceId;
    }

    public void setConferenceId(final UUID conferenceId) {

        this.conferenceId = conferenceId;
    }

    public UUID getPersonId() {

        return this.personId;
    }

    public void setPersonId(final UUID personId) {

        this.personId = personId;
    }

    public ParticipationInConference getParticipationInConference() {

        return this.participationInConference;
    }

    public void setParticipationInConference(
            final ParticipationInConference participationInConference) {

        this.participationInConference = participationInConference;
    }
}
