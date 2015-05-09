package net.ostis.confman.model.dao.impl.scmemory;

import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import net.ostis.confman.model.dao.ParticipationInConferenceDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.ParticipationInConference;


public class ParticipationInConferenceDAOImpl extends BaseDAOImpl<ParticipationInConference> implements ParticipationInConferenceDAO {
    
    private enum ScChildRelations implements ScIdentifiable {

        EXHIBITION_PRESENTATION_OF_REPORTS("conforg_participation_in_conference_exhibition_presentation_of_reports*"),
        TOUR_OF_THE_CITY_OF_MINSK("conforg_participation_in_conference_tour_of_the_city_of_minsk*"),
        CULTURAL_PROGRAMM("conforg_participation_in_conference_cultural_programm*"),
        EVENING_MEETING_PC("conforg_participation_in_conference_evening_meeting_pc*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        public String getSystemId() {

            return systemId;
        }
    }
    
    public ParticipationInConferenceDAOImpl(ScSpaces space) {

        super(ScSpaces.PARTICIPATION_IN_CONFERENCE);
    }

    @Override
    public List<ParticipationInConference> readAll() throws DAOException {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void saveFields(ParticipationInConference element,
            ScAddress parentNode) throws DAOException {

        ScString exhibitionPresentationOfeportsContent = ScStrings.wrap(element.getExhibitionPresentationOfReports());
        ScAddress exhibitionPresentationOfeportsNode = ScUtils.INSTANCE
                .createNodeWithContent(exhibitionPresentationOfeportsContent);
        ScUtils.INSTANCE.createRelation(parentNode, exhibitionPresentationOfeportsNode,
                ScChildRelations.EXHIBITION_PRESENTATION_OF_REPORTS);
        
        ScString tourOfTheCityOfMinskContent = ScStrings.wrap(element.getTourOfTheCityOfMinsk());
        ScAddress tourOfTheCityOfMinskNode = ScUtils.INSTANCE
                .createNodeWithContent(tourOfTheCityOfMinskContent);
        ScUtils.INSTANCE.createRelation(parentNode, tourOfTheCityOfMinskNode,
                ScChildRelations.TOUR_OF_THE_CITY_OF_MINSK);
        
        ScString culturalProgramContent = ScStrings.wrap(element.getCulturalProgram());
        ScAddress culturalProgramNode = ScUtils.INSTANCE
                .createNodeWithContent(culturalProgramContent);
        ScUtils.INSTANCE.createRelation(parentNode, culturalProgramNode,
                ScChildRelations.CULTURAL_PROGRAMM);
        
        ScString eveningMeetingPCContent = ScStrings.wrap(element.getEveningMeetingPC());
        ScAddress eveningMeetingPCNode = ScUtils.INSTANCE
                .createNodeWithContent(eveningMeetingPCContent);
        ScUtils.INSTANCE.createRelation(parentNode, eveningMeetingPCNode,
                ScChildRelations.EVENING_MEETING_PC);
        
    }

    @Override
    protected ParticipationInConference readFields(ScAddress elementNode)
            throws DAOException {

        ScAddress exhibitionPresentationOfReportsAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.EXHIBITION_PRESENTATION_OF_REPORTS);
        Boolean exhibitionPresentationOfReportsContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE
                .findElementContent(exhibitionPresentationOfReportsAdr));
        
        ScAddress tourOfTheCityOfMinskAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.TOUR_OF_THE_CITY_OF_MINSK);
        Boolean tourOfTheCityOfMinskContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE
                .findElementContent(tourOfTheCityOfMinskAdr));
        
        ScAddress culturalProgramAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.CULTURAL_PROGRAMM);
        Boolean culturalProgramContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE
                .findElementContent(culturalProgramAdr));
        
        ScAddress eveningMeetingPCAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.EVENING_MEETING_PC);
        Boolean eveningMeetingPCContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE
                .findElementContent(eveningMeetingPCAdr));
        
        return new ParticipationInConference(exhibitionPresentationOfReportsContent, tourOfTheCityOfMinskContent, 
                culturalProgramContent, eveningMeetingPCContent);
    }

}
