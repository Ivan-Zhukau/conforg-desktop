package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;

public interface ConferenceService {

    List<Conference> getConferences();

    List<Conference> getOpenedConferences();

    void updateConference(Conference storedConference,
            Conference updatedConference);

    void addSection(Conference conference, Section section);

    void deleteSection(Section selectedElement);

    void addReport(Section section, Report report);

    void moveReport(Report report, Section from, Section to);

    void deleteReport(Report report);

    List<Report> getReports();

    void fireData();

    int getSectionOrder(Section section);

    void addConference(Conference conference);

    void deleteConference(Conference conference);

    void openConference(Conference conference);

    void closeConference(Conference conference);

    List<Conference> getClosedConferences();

}
