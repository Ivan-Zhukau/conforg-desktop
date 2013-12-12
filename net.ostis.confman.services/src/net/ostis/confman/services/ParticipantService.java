package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;

public interface ParticipantService {

    List<Participant> getParticipants();

    void addParticipant(Participant participant);

    void fireData();

    void addPerson(Person person);

}
