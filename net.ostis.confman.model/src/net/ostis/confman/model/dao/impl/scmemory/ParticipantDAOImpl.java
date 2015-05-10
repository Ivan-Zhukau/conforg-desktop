package net.ostis.confman.model.dao.impl.scmemory;

import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import net.ostis.confman.model.dao.ParticipantDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.Participant;
import net.ostis.confman.model.entity.scmemory.SystemAddress;


public class ParticipantDAOImpl extends BaseDAOImpl<Participant> implements ParticipantDAO {
    
    private enum ScChildRelations implements ScIdentifiable {
        ROLE("conforg_participant_role*"),
        ARRIVAL("conforg_participant_arrival*"),
        PARTICIPATION_IN_CONFERENCE("conforg_paricipant_participation_in_conference*"),
        REPORT("conforg_participant_report*"),
        CONFERENCE("conforg_participant_conference*"),
        PERSON("conforg_paricipant_person*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        @Override
        public String getSystemId() {

            return systemId;
        }
    }
    
    public ParticipantDAOImpl() {

        super(ScSpaces.PARTICIPANTS);
    }

    @Override
    protected void saveFields(Participant element, ScAddress parentNode)
            throws DAOException {

        ScAddress roleNode = ScUtils.INSTANCE.findElement(element
                .getRole());
        ScUtils.INSTANCE.createRelation(parentNode, roleNode,
                ScCommonRelations.PARTICIPANT_ROLE);
        
        ScAddress arrivalNode = ScUtils.INSTANCE.findElement(element
                .getArrival());
        ScUtils.INSTANCE.createRelation(parentNode, arrivalNode,
                ScCommonRelations.PARTICIPANT_ARRIVAL);
        
        ScAddress participationInConferenceNode = ScUtils.INSTANCE.findElement(element
                .getParticipationInConference());
        ScUtils.INSTANCE.createRelation(parentNode, participationInConferenceNode,
                ScCommonRelations.PARTICIPATION_IN_CONFERENCE);
        
        ScAddress conferenceNode = ScUtils.INSTANCE.findElement(element
                .getConferenceId());
        ScUtils.INSTANCE.createRelation(parentNode, conferenceNode,
                ScCommonRelations.CONFERENCE);
        
        ScAddress personNode = ScUtils.INSTANCE.findElement(element
                .getPersonId());
        ScUtils.INSTANCE.createRelation(parentNode, personNode,
                ScCommonRelations.PERSON);

        for (SystemAddress reportSysAdr : element.getReportId()) {
            ScAddress reportNode = ScUtils.INSTANCE.findElement(reportSysAdr);
            ScUtils.INSTANCE.createRelation(parentNode, reportNode,
                    ScChildRelations.REPORT);
        }
        
    }

    @Override
    protected Participant readFields(ScAddress elementNode) throws DAOException {

        SystemAddress participantRoleSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.PARTICIPANT_ROLE);
        
        SystemAddress participantArrivalSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.PARTICIPANT_ARRIVAL);

        SystemAddress participationInConferenceSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.PARTICIPATION_IN_CONFERENCE);
        
        SystemAddress conferenceSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.CONFERENCE);
        
        SystemAddress personSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.PERSON);

        List<SystemAddress> reportSysAdrs = loadLinkedElementsByRel(elementNode, ScChildRelations.REPORT);
        
        return new Participant(participantRoleSysAdr, participantArrivalSysAdr, participationInConferenceSysAdr, 
                reportSysAdrs, conferenceSysAdr, personSysAdr);
    }

}
