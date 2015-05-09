package net.ostis.confman.test.xml;

import java.util.Arrays;

import net.ostis.confman.model.datastore.local.ParticipantWriter;
import net.ostis.confman.model.entity.xml.Address;
import net.ostis.confman.model.entity.xml.Participant;
import net.ostis.confman.model.entity.xml.ParticipantArrival;
import net.ostis.confman.model.entity.xml.ParticipantRole;
import net.ostis.confman.model.entity.xml.Participants;

import org.junit.Test;

public class ParticipantStorageTest {

    @Test
    public void test() {

        final Participant participant = new Participant();
        participant.setId(2L);
        participant.setConferenceId(3L);
        participant.setPersonId(1L);
        participant.setReportId(Arrays.asList(5L));
        final ParticipantArrival arrival = new ParticipantArrival();
        arrival.setHousing(true);
        arrival.setMeeting(false);
        final Address address = new Address();
        address.setCity("ASDFG");
        address.setCountry("NIGERIA");
        arrival.setResidencePlace(address);
        participant.setArrival(arrival);
        final ParticipantRole role = new ParticipantRole();
        role.setParticipationForm("Reporter");
        role.setProgramCommitteeMember(true);
        participant.setRole(role);

        final Participants participants = new Participants();
        participants.setParticipants(Arrays.asList(participant));
        final Runnable command = new ParticipantWriter(participants);
        command.run();
    }
}
