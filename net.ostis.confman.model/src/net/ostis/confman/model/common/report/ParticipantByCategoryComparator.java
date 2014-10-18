package net.ostis.confman.model.common.report;

import java.util.Comparator;

import net.ostis.confman.services.common.model.Participant;

public class ParticipantByCategoryComparator implements Comparator<Participant> {

    @Override
    public int compare(Participant participant1, Participant participant2) {
        if("".equals(participant1.getRole().getParticipationCategory()) 
                && !"".equals(participant2.getRole().getParticipationCategory())){
            return -1;
        }
        if(!"".equals(participant1.getRole().getParticipationCategory()) 
                && "".equals(participant2.getRole().getParticipationCategory())){
            return 1;
        }
        
        return participant1.getRole().getParticipationCategory()
                .compareTo(participant2.getRole().getParticipationCategory());        
    }

}
