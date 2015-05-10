package net.ostis.confman.model.dao.impl.scmemory;

import java.util.Date;
import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import net.ostis.confman.model.dao.SectionDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.Section;
import net.ostis.confman.model.entity.scmemory.SystemAddress;


public class SectionDAOImpl extends BaseDAOImpl<Section> implements SectionDAO {
    
    private enum ScChildRelations implements ScIdentifiable {
        TITLE("conforg_section_title*"),
        DATE("conforg_section_date*"),
        REPORT("conforg_section_conference*"),
        CONFERENCE("conforg_section_report*");
        
        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        @Override
        public String getSystemId() {

            return systemId;
        }
    }
    
    public SectionDAOImpl() {

        super(ScSpaces.SECTIONS);
    }

    @Override
    protected void saveFields(Section element, ScAddress parentNode)
            throws DAOException {
        
        ScString titleContent = ScStrings.wrap(element.getTitle());
        ScAddress titleNode = ScUtils.INSTANCE
                .createNodeWithContent(titleContent);
        ScUtils.INSTANCE.createRelation(parentNode, titleNode,
                ScChildRelations.TITLE);

        ScString dateContent = ScStrings.wrap(element.getDate());
        ScAddress dateNode = ScUtils.INSTANCE
                .createNodeWithContent(dateContent);
        ScUtils.INSTANCE.createRelation(parentNode, dateNode,
                ScChildRelations.DATE);

        ScAddress conferenceNode = ScUtils.INSTANCE.findElement(element
                .getConferenceId());
        ScUtils.INSTANCE.createRelation(parentNode, conferenceNode,
                ScCommonRelations.CONFERENCE);

        for (SystemAddress reportSysAdr : element.getReports()) {
            ScAddress reportNode = ScUtils.INSTANCE.findElement(reportSysAdr);
            ScUtils.INSTANCE.createRelation(parentNode, reportNode,
                    ScChildRelations.REPORT);
        }
        
    }

    @Override
    protected Section readFields(ScAddress elementNode) throws DAOException {

        ScAddress titleAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.TITLE);
        String titleContent = ScUtils.INSTANCE.findElementContent(titleAdr);

        ScAddress dateAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.DATE);
        String dateContent = ScUtils.INSTANCE.findElementContent(dateAdr);
        Date date = ScStrings.unwrapToDate(dateContent); 
        
        SystemAddress conferenceSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.CONFERENCE);

        List<SystemAddress> reportSysAdrs = loadLinkedElementsByRel(elementNode, ScChildRelations.REPORT);
        
        return new Section(titleContent, date, conferenceSysAdr, reportSysAdrs);
    }

}
