package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.services.common.model.Participant;


public interface ParticipantService {
    
    List<Participant> getParticipants();
    
    void addParticipant(Participant participant);

}
