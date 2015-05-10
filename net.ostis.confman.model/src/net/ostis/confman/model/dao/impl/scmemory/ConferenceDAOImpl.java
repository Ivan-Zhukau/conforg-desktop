package net.ostis.confman.model.dao.impl.scmemory;

import java.util.Date;
import java.util.List;

import net.ostis.confman.model.dao.ConferenceDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.Conference;
import net.ostis.confman.model.entity.scmemory.SystemAddress;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;


public class ConferenceDAOImpl extends BaseDAOImpl<Conference> implements
        ConferenceDAO {

    private enum ScChildRelations implements ScIdentifiable {
        TITLE("conforg_conferences_title*"),
        START_DATE("conforg_conferences_start_date*"),
        END_DATE("conforg_conferences_end_date*"),
        SECTION("conforg_conferences_section*"),
        REPORT("conforg_conferences_report*"),
        PARTICIPANT("conforg_conferences_participant*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        @Override
        public String getSystemId() {

            return systemId;
        }
    }

    public ConferenceDAOImpl() {

        super(ScSpaces.CONFERENCES);
    }

    @Override
    protected void saveFields(Conference element, ScAddress parentNode)
            throws DAOException {

        ScString titleContent = ScStrings.wrap(element.getTitle());
        ScAddress titleNode = ScUtils.INSTANCE
                .createNodeWithContent(titleContent);
        ScUtils.INSTANCE.createRelation(parentNode, titleNode,
                ScChildRelations.TITLE);

        ScString startDateContent = ScStrings.wrap(element.getStartDate());
        ScAddress startDateNode = ScUtils.INSTANCE
                .createNodeWithContent(startDateContent);
        ScUtils.INSTANCE.createRelation(parentNode, startDateNode,
                ScChildRelations.START_DATE);

        ScString endDateContent = ScStrings.wrap(element.getEndDate());
        ScAddress endDateNode = ScUtils.INSTANCE
                .createNodeWithContent(endDateContent);
        ScUtils.INSTANCE.createRelation(parentNode, endDateNode,
                ScChildRelations.END_DATE);

        ScAddress residencePlaceNode = ScUtils.INSTANCE.findElement(element
                .getResidence());
        ScUtils.INSTANCE.createRelation(parentNode, residencePlaceNode,
                ScCommonRelations.ADDRESS);

        for (SystemAddress sectionSysAdr : element.getSections()) {
            ScAddress sectionNode = ScUtils.INSTANCE.findElement(sectionSysAdr);
            ScUtils.INSTANCE.createRelation(parentNode, sectionNode,
                    ScChildRelations.SECTION);
        }

        for (SystemAddress reportSysAdr : element.getReports()) {
            ScAddress reportNode = ScUtils.INSTANCE.findElement(reportSysAdr);
            ScUtils.INSTANCE.createRelation(parentNode, reportNode,
                    ScChildRelations.REPORT);
        }
        
        for (SystemAddress participantSysAdr : element.getSections()) {
            ScAddress participantNode = ScUtils.INSTANCE.findElement(participantSysAdr);
            ScUtils.INSTANCE.createRelation(parentNode, participantNode,
                    ScChildRelations.PARTICIPANT);
        }
    }

    @Override
    protected Conference readFields(ScAddress elementNode) throws DAOException {

        ScAddress titleAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.TITLE);
        String titleContent = ScUtils.INSTANCE.findElementContent(titleAdr);

        ScAddress startDateAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.START_DATE);
        String startDateContent = ScUtils.INSTANCE.findElementContent(startDateAdr);
        Date startDate = ScStrings.unwrapToDate(startDateContent); 

        ScAddress endDateAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.END_DATE);
        String endDateContent = ScUtils.INSTANCE.findElementContent(endDateAdr);
        Date endDate = ScStrings.unwrapToDate(endDateContent);

        SystemAddress residenceSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.ADDRESS);

        List<SystemAddress> sectionSysAdrs = loadLinkedElementsByRel(elementNode, ScChildRelations.SECTION);
        List<SystemAddress> participantSysAdrs = loadLinkedElementsByRel(elementNode, ScChildRelations.PARTICIPANT);
        List<SystemAddress> reportSysAdrs = loadLinkedElementsByRel(elementNode, ScChildRelations.REPORT);
        
        return new Conference(titleContent, startDate, endDate, residenceSysAdr, sectionSysAdrs,
                reportSysAdrs, participantSysAdrs);
    }
}
