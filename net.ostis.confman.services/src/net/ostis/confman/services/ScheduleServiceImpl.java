package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;


public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public List<Conference> getConferences() {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateConference(Conference storedConference,
            Conference updatedConference) {

        // TODO Auto-generated method stub
        
    }

    @Override
    public void addSection(Conference conference, Section section) {

        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteSection(Section selectedElement) {

        // TODO Auto-generated method stub
        
    }

    @Override
    public void addReport(Section section, Report report) {

        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveReport(Report report, Section from, Section to) {

        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteReport(Report report) {

        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Report> getReports() {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void fireData() {

        // TODO Auto-generated method stub
        
    }

}
