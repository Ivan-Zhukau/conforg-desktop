package net.ostis.confman.model.dao.impl.scmemory;

import java.util.Date;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import net.ostis.confman.model.dao.SectionSettingsDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.SectionSettings;
import net.ostis.confman.model.entity.scmemory.SystemAddress;


public class SectionSettingsDAOImpl extends BaseDAOImpl<SectionSettings> implements SectionSettingsDAO {
    
    private enum ScChildRelations implements ScIdentifiable {
        CHAIRMAN_TIME("conforg_section_settings_chairman_time*"),
        REPORT_TIME("conforg_section_settings_report_time*"),
        PLENARY_REPORT_TIME("conforg_section_settings_plenary_report_time*"),
        SECTION("conforg_section_settings_section*"),
        BREAL_TIME("conforg_section_settings_break_time*"),
        COFFEE_BREAKS_NUM("conforg_section_settings_coffee_breaks_num*"),
        COFEE_BREAKS_TIME("conforg_section_settings_coffee_breaks_time*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        @Override
        public String getSystemId() {

            return systemId;
        }
    }
    
    public SectionSettingsDAOImpl() {

        super(ScSpaces.SECTION_SETTINGS);
    }

    @Override
    protected void saveFields(SectionSettings element, ScAddress parentNode)
            throws DAOException {

        ScString chairmanTimeContent = ScStrings.wrap(element.getChairmanTime());
        ScAddress chairmanTimeNode = ScUtils.INSTANCE
                .createNodeWithContent(chairmanTimeContent);
        ScUtils.INSTANCE.createRelation(parentNode, chairmanTimeNode,
                ScChildRelations.CHAIRMAN_TIME);
        
        ScString reportTimeContent = ScStrings.wrap(element.getReportTime());
        ScAddress reportTimeNode = ScUtils.INSTANCE
                .createNodeWithContent(reportTimeContent);
        ScUtils.INSTANCE.createRelation(parentNode, reportTimeNode,
                ScChildRelations.REPORT_TIME);
        
        ScString plenaryReportTimeContent = ScStrings.wrap(element.getPlenaryReportTime());
        ScAddress plenaryReportTimeNode = ScUtils.INSTANCE
                .createNodeWithContent(plenaryReportTimeContent);
        ScUtils.INSTANCE.createRelation(parentNode, plenaryReportTimeNode,
                ScChildRelations.PLENARY_REPORT_TIME);
        
        ScString breakTimeContent = ScStrings.wrap(element.getBreakTime());
        ScAddress breakTimeNode = ScUtils.INSTANCE
                .createNodeWithContent(breakTimeContent);
        ScUtils.INSTANCE.createRelation(parentNode, breakTimeNode,
                ScChildRelations.BREAL_TIME);
        
        ScString coffeeBreaksNumContent = ScStrings.wrap(element.getCoffeeBreakNumber());
        ScAddress coffeeBreaksNumNode = ScUtils.INSTANCE
                .createNodeWithContent(coffeeBreaksNumContent);
        ScUtils.INSTANCE.createRelation(parentNode, coffeeBreaksNumNode,
                ScChildRelations.COFFEE_BREAKS_NUM);
        
        ScString coffeeBreaksTimeContent = ScStrings.wrap(element.getCoffeeBreakTime());
        ScAddress coffeeBreaksTimeNode = ScUtils.INSTANCE
                .createNodeWithContent(coffeeBreaksTimeContent);
        ScUtils.INSTANCE.createRelation(parentNode, coffeeBreaksTimeNode,
                ScChildRelations.COFEE_BREAKS_TIME);
        
        ScAddress sectionNode = ScUtils.INSTANCE.findElement(element
                .getSectionId());
        ScUtils.INSTANCE.createRelation(parentNode, sectionNode,
                ScCommonRelations.SECTION);
        
    }

    @Override
    protected SectionSettings readFields(ScAddress elementNode)
            throws DAOException {

        ScAddress chairmanTimeAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.CHAIRMAN_TIME);
        String chairmanTimeContent = ScUtils.INSTANCE.findElementContent(chairmanTimeAdr);
        Integer chairmanTime = ScStrings.unwrapToInteger(chairmanTimeContent);
        
        ScAddress reportTimeAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.REPORT_TIME);
        String reportTimeContent = ScUtils.INSTANCE.findElementContent(reportTimeAdr);
        Integer reportTime = ScStrings.unwrapToInteger(reportTimeContent);
        
        ScAddress plenaryReportTimeAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.PLENARY_REPORT_TIME);
        String plenaryReportTimeContent = ScUtils.INSTANCE.findElementContent(plenaryReportTimeAdr);
        Integer plenaryReportTime = ScStrings.unwrapToInteger(plenaryReportTimeContent);
        
        ScAddress breakTimeAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.BREAL_TIME);
        String breakTimeContent = ScUtils.INSTANCE.findElementContent(breakTimeAdr);
        Integer breakTime = ScStrings.unwrapToInteger(breakTimeContent);
        
        ScAddress coffeeBreaksNumAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.COFFEE_BREAKS_NUM);
        String coffeeBreaksNumContent = ScUtils.INSTANCE.findElementContent(coffeeBreaksNumAdr);
        Integer coffeeBreaksNum = ScStrings.unwrapToInteger(coffeeBreaksNumContent);
        
        ScAddress coffeeBreaksTimeAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.COFEE_BREAKS_TIME);
        String coffeeBreaksTimeContent = ScUtils.INSTANCE.findElementContent(coffeeBreaksTimeAdr);
        Integer coffeeBreaksTime = ScStrings.unwrapToInteger(coffeeBreaksTimeContent);
        
        SystemAddress sectionSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.SECTION);
        
        return new SectionSettings(sectionSysAdr, chairmanTime, reportTime, plenaryReportTime, 
                breakTime, coffeeBreaksNum, coffeeBreaksTime);
    }

}
