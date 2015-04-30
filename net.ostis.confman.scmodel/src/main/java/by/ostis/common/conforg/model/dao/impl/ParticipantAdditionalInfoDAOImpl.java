package by.ostis.common.conforg.model.dao.impl;

import by.ostis.common.conforg.model.dao.ParticipantAdditionalInfoDAO;
import by.ostis.common.conforg.model.dao.exception.DAOException;
import by.ostis.common.conforg.model.entity.ParticipantAdditionalInfo;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.utils.constants.ScElementType;

public class ParticipantAdditionalInfoDAOImpl extends BaseDAOImpl<ParticipantAdditionalInfo> implements ParticipantAdditionalInfoDAO {

    private enum ScChildRelations implements ScIdentifiable {

        HOUSING("conforg_participant_additional_info_housing*"),
        MEETING("conforg_participant_additional_info_meeting*"),
        CULTURAL_PROGRAM("conforg_participant_additional_info_cultural_program*"),
        EVENING_MEETING_PC("conforg_participant_additional_info_evening_meeting_pc*"),
        EXHIBITION_PRESENTATION_OF_REPORTS("conforg_participant_additional_info_exhibition_presentation_of_reports*"),
        TOUR_OF_THE_CITY_OF_MINSK("conforg_participant_additional_info_tour_of_the_city_of_minsk*"),
        EXHIBITION_STAND("conforg_participant_additional_info_exhibition_stand*"),
        PROGRAM_COMMITTEE_MEMBER("conforg_participant_additional_info_program_committee_member*");

        private String systemId;

        ScChildRelations(String systemId) {
            this.systemId = systemId;
        }

        public String getSystemId() {
            return systemId;
        }
    }

    public ParticipantAdditionalInfoDAOImpl() {
        super(ScSpaces.PARTICIPANT_ADDITIONAL_INFO);
    }

    @Override
    protected void saveFields(ParticipantAdditionalInfo element, ScAddress parentNode) throws DAOException {
        ScString housingContent = ScStrings.wrap(element.getHousing());
        ScAddress housingNode = ScUtils.INSTANCE.createNodeWithContent(housingContent);
        ScUtils.INSTANCE.createRelation(parentNode, housingNode, ScChildRelations.HOUSING);

        ScString meetingContent = ScStrings.wrap(element.getMeeting());
        ScAddress meetingNode = ScUtils.INSTANCE.createNodeWithContent(meetingContent);
        ScUtils.INSTANCE.createRelation(parentNode, meetingNode, ScChildRelations.MEETING);

        ScString culturalProgramContent = ScStrings.wrap(element.getCulturalProgramm());
        ScAddress culturalProgramNode = ScUtils.INSTANCE.createNodeWithContent(culturalProgramContent);
        ScUtils.INSTANCE.createRelation(parentNode, culturalProgramNode, ScChildRelations.CULTURAL_PROGRAM);

        ScString eveningMeetingPcContent = ScStrings.wrap(element.getEveningMeetingPC());
        ScAddress eveningMeetingPcNode = ScUtils.INSTANCE.createNodeWithContent(eveningMeetingPcContent);
        ScUtils.INSTANCE.createRelation(parentNode, eveningMeetingPcNode, ScChildRelations.EVENING_MEETING_PC);

        ScString exhibitionPresentationOfReportsContent = ScStrings.wrap(element.getExhibitionPresentationOfReports());
        ScAddress exhibitionPresentationOfReportsNode = ScUtils.INSTANCE.createNodeWithContent(exhibitionPresentationOfReportsContent);
        ScUtils.INSTANCE.createRelation(parentNode, exhibitionPresentationOfReportsNode, ScChildRelations.EXHIBITION_PRESENTATION_OF_REPORTS);

        ScString tourOfTheCityOfMinskContent = ScStrings.wrap(element.getTourOfTheCityOfMinsk());
        ScAddress tourOfTheCityOfMinskNode = ScUtils.INSTANCE.createNodeWithContent(tourOfTheCityOfMinskContent);
        ScUtils.INSTANCE.createRelation(parentNode, tourOfTheCityOfMinskNode, ScChildRelations.TOUR_OF_THE_CITY_OF_MINSK);

        ScString exhibitionStandContent = ScStrings.wrap(element.getExhibitionStand());
        ScAddress exhibitionStandNode = ScUtils.INSTANCE.createNodeWithContent(exhibitionStandContent);
        ScUtils.INSTANCE.createRelation(parentNode, exhibitionStandNode, ScChildRelations.EXHIBITION_STAND);

        ScString programCommitteeMemberContent = ScStrings.wrap(element.getProgramCommitteeMember());
        ScAddress programCommitteeMemberNode = ScUtils.INSTANCE.createNodeWithContent(programCommitteeMemberContent);
        ScUtils.INSTANCE.createRelation(parentNode, programCommitteeMemberNode, ScChildRelations.PROGRAM_COMMITTEE_MEMBER);

        ScAddress addressAdr = ScUtils.INSTANCE.findElement(element.getAddress());
        ScUtils.INSTANCE.createArc(ScElementType.SC_TYPE_ARC_POS, parentNode, addressAdr);
    }

    @Override
    protected ParticipantAdditionalInfo readFields(ScAddress addressElement) throws DAOException {
        ParticipantAdditionalInfo.ParticipantAdditionalInfoBuilder builder = new ParticipantAdditionalInfo.ParticipantAdditionalInfoBuilder();

        ScAddress housingAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.HOUSING);
        Boolean housingContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE.findElementContent(housingAdr));

        ScAddress meetingAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.MEETING);
        Boolean meetingContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE.findElementContent(meetingAdr));

        ScAddress culturalProgramAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.CULTURAL_PROGRAM);
        Boolean culturalProgramContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE.findElementContent(culturalProgramAdr));

        ScAddress eveningMeetingAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.EVENING_MEETING_PC);
        Boolean eveningMeetingContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE.findElementContent(eveningMeetingAdr));

        ScAddress exhibitionPresentationOfReportsAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.EXHIBITION_PRESENTATION_OF_REPORTS);
        Boolean exhibitionPresentationOfReportsContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE.findElementContent(exhibitionPresentationOfReportsAdr));

        ScAddress tourOfTheCityMinskAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.TOUR_OF_THE_CITY_OF_MINSK);
        Boolean tourOfTheCityMinskContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE.findElementContent(tourOfTheCityMinskAdr));

        ScAddress exhibitionStandAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.EXHIBITION_STAND);
        Boolean exhibitionStandContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE.findElementContent(exhibitionStandAdr));

        ScAddress programCommitteeMemberAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.PROGRAM_COMMITTEE_MEMBER);
        Boolean programCommitteeMemberContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE.findElementContent(programCommitteeMemberAdr));

        //TODO: do something with UUID for Address

        builder.setHousing(housingContent).setMeeting(meetingContent).setCulturalProgramm(culturalProgramContent).
                setEveningMeetingPC(eveningMeetingContent).
                setExhibitionPresentationOfReports(exhibitionPresentationOfReportsContent).
                setTourOfTheCityOfMinsk(tourOfTheCityMinskContent).setExhibitionStand(exhibitionStandContent).
                setProgramCommitteeMember(programCommitteeMemberContent);

        return builder.build();
    }
}
