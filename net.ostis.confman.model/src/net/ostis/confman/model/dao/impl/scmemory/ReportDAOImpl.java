package net.ostis.confman.model.dao.impl.scmemory;

import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import net.ostis.confman.model.dao.ReportDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.Report;
import net.ostis.confman.model.entity.scmemory.SystemAddress;


public class ReportDAOImpl extends BaseDAOImpl<Report> implements ReportDAO {
    
    private enum ScChildRelations implements ScIdentifiable {
        TITLE("conforg_report_title*"),
        REPORTER("conforg_report_reporter*"),
        YOUNG_SCIENTIST_REPORT("conforg_report_young_scientist_report*"),
        SECTION("conforg_report_section*"),
        REPORT("conforg_report_report*"),
        PARTICIPANT("conforg_report_participant*"),
        ACCEPTED("conforg_report_accepted*"),
        CANCELED("conforg_report_canceled*"),
        PARTICIPATION_IN_CONTEST("conforg_report_participation_in_contest*"),
        PLENARY_REPORT("conforg_report_plenary_report*"),
        NUMBER_OF_PAGES("conforg_report_number_of_pages*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        @Override
        public String getSystemId() {

            return systemId;
        }
    }
    
    public ReportDAOImpl() {

        super(ScSpaces.REPORTS);
    }

    @Override
    protected void saveFields(Report element, ScAddress parentNode)
            throws DAOException {

        ScAddress sectionNode = ScUtils.INSTANCE.findElement(element
                .getSectionId());
        ScUtils.INSTANCE.createRelation(parentNode, sectionNode,
                ScCommonRelations.SECTION);
        
        ScAddress reporterNode = ScUtils.INSTANCE.findElement(element
                .getReporter());
        ScUtils.INSTANCE.createRelation(parentNode, reporterNode,
                ScCommonRelations.REPORTER);

        for (SystemAddress participantSysAdr : element.getParticipants()) {
            ScAddress participantNode = ScUtils.INSTANCE.findElement(participantSysAdr);
            ScUtils.INSTANCE.createRelation(parentNode, participantNode,
                    ScChildRelations.PARTICIPANT);
        }
        
        ScString titleContent = ScStrings.wrap(element.getTitle());
        ScAddress titleNode = ScUtils.INSTANCE
                .createNodeWithContent(titleContent);
        ScUtils.INSTANCE.createRelation(parentNode, titleNode,
                ScChildRelations.TITLE);
        
        ScString numberOfPagesContent = ScStrings.wrap(element.getNumberOfPages());
        ScAddress numberOfPagesNode = ScUtils.INSTANCE
                .createNodeWithContent(numberOfPagesContent);
        ScUtils.INSTANCE.createRelation(parentNode, numberOfPagesNode,
                ScChildRelations.NUMBER_OF_PAGES);
        
        ScString youngScientistReportContent = ScStrings.wrap(element.getYoungScientistReport());
        ScAddress youngScientistReportNode = ScUtils.INSTANCE
                .createNodeWithContent(youngScientistReportContent);
        ScUtils.INSTANCE.createRelation(parentNode, youngScientistReportNode,
                ScChildRelations.YOUNG_SCIENTIST_REPORT);
        
        ScString acceptedContent = ScStrings.wrap(element.getAccepted());
        ScAddress acceptedNode = ScUtils.INSTANCE
                .createNodeWithContent(acceptedContent);
        ScUtils.INSTANCE.createRelation(parentNode, acceptedNode,
                ScChildRelations.ACCEPTED);

        ScString participationInContestContent = ScStrings.wrap(element.getParticipationInContest());
        ScAddress participationInContestNode = ScUtils.INSTANCE
                .createNodeWithContent(participationInContestContent);
        ScUtils.INSTANCE.createRelation(parentNode, participationInContestNode,
                ScChildRelations.PARTICIPATION_IN_CONTEST);
        
        ScString plenaryReportContent = ScStrings.wrap(element.getPlenaryReport());
        ScAddress plenaryReportNode = ScUtils.INSTANCE
                .createNodeWithContent(plenaryReportContent);
        ScUtils.INSTANCE.createRelation(parentNode, plenaryReportNode,
                ScChildRelations.PLENARY_REPORT);
        
    }

    @Override
    protected Report readFields(ScAddress elementNode) throws DAOException {
        
        SystemAddress sectionSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.SECTION);
        
        SystemAddress reporterSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.REPORTER);

        List<SystemAddress> participantSysAdrs = loadLinkedElementsByRel(elementNode, ScChildRelations.PARTICIPANT);
        
        ScAddress titleAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.TITLE);
        String titleContent = ScUtils.INSTANCE.findElementContent(titleAdr);
        
        ScAddress numberOfPagesAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.NUMBER_OF_PAGES);
        String numberOfPagesContent = ScUtils.INSTANCE.findElementContent(numberOfPagesAdr);
        
        ScAddress youngScientistReportAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.YOUNG_SCIENTIST_REPORT);
        Boolean youngScientistReportContent = ScStrings
                .unwrapToBoolean(ScUtils.INSTANCE
                        .findElementContent(youngScientistReportAdr));
        
        ScAddress acceptedAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.ACCEPTED);
        Boolean acceptedContent = ScStrings
                .unwrapToBoolean(ScUtils.INSTANCE
                        .findElementContent(acceptedAdr));
        
        ScAddress plenaryReportAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.PLENARY_REPORT);
        Boolean plenaryReportContent = ScStrings
                .unwrapToBoolean(ScUtils.INSTANCE
                        .findElementContent(plenaryReportAdr));
        
        ScAddress participationInContestAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.PARTICIPATION_IN_CONTEST);
        Boolean participationInContestContent = ScStrings
                .unwrapToBoolean(ScUtils.INSTANCE
                        .findElementContent(participationInContestAdr));
        
        return new Report(titleContent, sectionSysAdr, participantSysAdrs, reporterSysAdr, youngScientistReportContent, 
                acceptedContent, participationInContestContent, plenaryReportContent, numberOfPagesContent);
    }

}
