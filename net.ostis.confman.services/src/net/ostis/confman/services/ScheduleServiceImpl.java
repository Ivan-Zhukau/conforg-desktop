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
    public void updateConference(final Conference storedConference,
            final Conference updatedConference) {

        // TODO Auto-generated method stub

    }

    @Override
    public void addSection(final Conference conference, final Section section) {

        // TODO Auto-generated method stub

    }

    @Override
    public void deleteSection(final Section selectedElement) {

        // TODO Auto-generated method stub

    }

    @Override
    public void addReport(final Section section, final Report report) {

        // TODO Auto-generated method stub

    }

    @Override
    public void moveReport(final Report report, final Section from,
            final Section to) {

        // TODO Auto-generated method stub

    }

    @Override
    public void deleteReport(final Report report) {

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
