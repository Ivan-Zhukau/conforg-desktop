package net.ostis.confman.model.dao.impl.scmemory;

import java.util.List;
import java.util.UUID;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import net.ostis.confman.model.dao.ParticipantArrivalDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.dao.impl.scmemory.PersonDAOImpl.ScChildRelations;
import net.ostis.confman.model.entity.scmemory.ParticipantArrival;


public class ParticipantArrivalDAOImpl extends BaseDAOImpl<ParticipantArrival> implements ParticipantArrivalDAO {
    
    private enum ScChildRelations implements ScIdentifiable {

        HOUSING("conforg_participant_arrival_housing*"),
        MEETING("conforg_participant_arrival_meeting*"),
        RESIDENCE_PLACE("conforg_participant_arrival_residence_place*"),
        NEED_HOTEL_RESERVATION("conforg_participant_arrival_need_hotel_reservation*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        public String getSystemId() {

            return systemId;
        }
    }
    
    public ParticipantArrivalDAOImpl() {

        super(ScSpaces.PARTICIPANT_ARRIVAL);
    }

    @Override
    public List<ParticipantArrival> readAll() throws DAOException {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void saveFields(ParticipantArrival element, ScAddress parentNode)
            throws DAOException {

        ScString housingContent = ScStrings.wrap(element.getHousing());
        ScAddress housingNode = ScUtils.INSTANCE
                .createNodeWithContent(housingContent);
        ScUtils.INSTANCE.createRelation(parentNode, housingNode,
                ScChildRelations.HOUSING);

        ScString meetingContent = ScStrings.wrap(element.getMeeting());
        ScAddress meetingNode = ScUtils.INSTANCE
                .createNodeWithContent(meetingContent);
        ScUtils.INSTANCE.createRelation(parentNode, meetingNode,
                ScChildRelations.MEETING);
        
        ScAddress academicDegreeNode = ScUtils.INSTANCE.findElement(element
                .getResidencePlace());
        ScUtils.INSTANCE.createRelation(parentNode, academicDegreeNode,
                ScChildRelations.RESIDENCE_PLACE);

        ScString needHostelReservationContent = ScStrings.wrap(element
                .getNeedHostelReservation());
        ScAddress needHostelReservationNode = ScUtils.INSTANCE
                .createNodeWithContent(needHostelReservationContent);
        ScUtils.INSTANCE.createRelation(parentNode, needHostelReservationNode,
                ScChildRelations.NEED_HOTEL_RESERVATION);
        
    }

    @Override
    protected ParticipantArrival readFields(ScAddress elementNode)
            throws DAOException {

        ScAddress housingAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.HOUSING);
        Boolean housingContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE
                .findElementContent(housingAdr));

        ScAddress meetingAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.MEETING);
        Boolean meetingContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE
                .findElementContent(meetingAdr));

        ScAddress residencePlaceAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.RESIDENCE_PLACE);
        //TODO do something with UUID for address

        ScAddress needHostelReservationAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.NEED_HOTEL_RESERVATION);
        Boolean needHostelReservationContent = ScStrings
                .unwrapToBoolean(ScUtils.INSTANCE
                        .findElementContent(needHostelReservationAdr));
        
        //TODO change null to Address UUID 
        return new ParticipantArrival(housingContent, meetingContent, null, needHostelReservationContent);
    }

}
