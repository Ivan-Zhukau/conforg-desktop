package net.ostis.confman.test.xml;

import java.util.Arrays;

import net.ostis.confman.model.datastore.local.ParticipantWriter;
import net.ostis.confman.model.entity.Participant;
import net.ostis.confman.model.entity.ParticipantArrival;
import net.ostis.confman.model.entity.ParticipantRole;
import net.ostis.confman.model.entity.Participants;

import org.junit.Test;

public class ParticipantStorageTest {

    @Test
    public void test() {

        final Participant participant = new Participant();
        participant.setId(1L);
        participant.setConferenceId(11L);
        participant.setPersonId(2L);
        participant.setReportId(Arrays.asList(112L));
        final ParticipantArrival arrival = new ParticipantArrival();
        arrival.setHousing(true);
        arrival.setMeeting(false);
        arrival.setResidencePlace("Minsk");
        participant.setArrival(arrival);
        final ParticipantRole role = new ParticipantRole();
        role.setExibitionStand(false);
        role.setParticipationForm("Reporter");
        role.setProgramCommitteeMember(true);
        participant.setRole(role);

        final Participants participants = new Participants();
        participants.setParticipants(Arrays.asList(participant));
        final Runnable command = new ParticipantWriter(participants);
        command.run();
    }
}
