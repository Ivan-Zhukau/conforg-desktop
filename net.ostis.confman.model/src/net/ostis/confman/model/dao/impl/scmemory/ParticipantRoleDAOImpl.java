package net.ostis.confman.model.dao.impl.scmemory;

import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import net.ostis.confman.model.dao.ParticipantRoleDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.ParticipantRole;


class ParticipantRoleDAOImpl extends BaseDAOImpl<ParticipantRole> implements ParticipantRoleDAO {
    
    private enum ScChildRelations implements ScIdentifiable {

        PROGRAMM_COMMITTEE_MEMBER("conforg_participant_role_programm_committee_member*"),
        PARTICIPATION_FORM("conforg_participant_role_participation_form*"),
        PARTICIPATION_CATEGORY("conforg_participant_role_participation_category*"),
        EXHIBITION_STAND("conforg_participant_role_exhibition_stand*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        public String getSystemId() {

            return systemId;
        }
    }
    
    public ParticipantRoleDAOImpl() {

        super(ScSpaces.PARTICIPANT_ROLE);
    }

    @Override
    public List<ParticipantRole> readAll() throws DAOException {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void saveFields(ParticipantRole element, ScAddress parentNode)
            throws DAOException {

        ScString programmCommitteeMemberContent = ScStrings.wrap(element.getProgramCommitteeMember());
        ScAddress programmCommitteeMemberNode = ScUtils.INSTANCE
                .createNodeWithContent(programmCommitteeMemberContent);
        ScUtils.INSTANCE.createRelation(parentNode, programmCommitteeMemberNode,
                ScChildRelations.PROGRAMM_COMMITTEE_MEMBER);

        ScString exhibitionStandContent = ScStrings.wrap(element.getExhibitionStand());
        ScAddress exhibitionStandNode = ScUtils.INSTANCE
                .createNodeWithContent(exhibitionStandContent);
        ScUtils.INSTANCE.createRelation(parentNode, exhibitionStandNode,
                ScChildRelations.EXHIBITION_STAND);

        ScString participationCategoryContent = ScStrings.wrap(element
                .getParticipationCategory());
        ScAddress participationCategoryNode = ScUtils.INSTANCE
                .createNodeWithContent(participationCategoryContent);
        ScUtils.INSTANCE.createRelation(parentNode, participationCategoryNode,
                ScChildRelations.PARTICIPATION_CATEGORY);

        ScString participationFormContent = ScStrings.wrap(element
                .getParticipationForm());
        ScAddress participationFormNode = ScUtils.INSTANCE
                .createNodeWithContent(participationFormContent);
        ScUtils.INSTANCE.createRelation(parentNode, participationFormNode,
                ScChildRelations.PARTICIPATION_FORM);
        
    }

    @Override
    protected ParticipantRole readFields(ScAddress elementNode)
            throws DAOException {

        ScAddress programmCommitteeMemberAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.PROGRAMM_COMMITTEE_MEMBER);
        Boolean programmCommitteeMemberContent = ScStrings.unwrapToBoolean(ScUtils.INSTANCE
                .findElementContent(programmCommitteeMemberAdr));

        ScAddress participationFormAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.PARTICIPATION_FORM);
        String participationFormContent = ScStrings.unwrapToString(ScUtils.INSTANCE
                .findElementContent(participationFormAdr));

        ScAddress participationCategoryAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.PARTICIPATION_CATEGORY);
        String participationCategoryContent = ScStrings
                .unwrapToString(ScUtils.INSTANCE
                        .findElementContent(participationCategoryAdr));

        ScAddress exhibitionStandAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.EXHIBITION_STAND);
        Boolean exhibitionStandContent = ScStrings
                .unwrapToBoolean(ScUtils.INSTANCE
                        .findElementContent(exhibitionStandAdr));
        
        return new ParticipantRole(programmCommitteeMemberContent, participationFormContent, participationCategoryContent, exhibitionStandContent);
    }

}
