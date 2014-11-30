package net.ostis.confman.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import net.ostis.confman.model.datastore.StorageProvider;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.ConferenceViewState;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.services.common.model.Workspace;

class ConferenceServiceImpl implements ConferenceService {

    private List<Conference> conferences;

    private List<Section>    sections;

    private List<Report>     reports;

    private Workspace        workspace;

    private FullModel        model;

    public ConferenceServiceImpl() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        this.conferences = this.model.getConferences();
        this.sections = this.model.getSections();
        this.reports = this.model.getReports();
        this.workspace = this.model.getWorkspace();
    }

    @Override
    public List<Conference> getOpenedConferences() {

        return workspace.getConferencePartState().getOpenedConferences();
    }

    @Override
    public List<Conference> getConferences() {

        return this.conferences;
    }

    @Override
    public void addSection(final Conference conference, final Section section) {

        conference.getSections().add(section);
        section.setConference(conference);
        if (!this.sections.contains(section)) {
            this.sections.add(section);
        }
        // fireData();
    }

    @Override
    public void addReport(final Section section, final Report report) {

        if (report.getSection() != null) {
            report.getSection().getReports().remove(report);
        }
        final Conference conf = section.getConference();
        if (!conf.getReports().contains(report)) {
            conf.getReports().add(report);
        }
        if (!section.getReports().contains(report)) {
            section.getReports().add(report);
        }
        report.setSection(section);
        if (!this.reports.contains(report)) {
            this.reports.add(report);
        }
        fireData();
    }

    @Override
    public void moveReport(final Report report, final Section from,
            final Section to) {

        from.getReports().remove(report);
        to.getReports().add(report);
        report.setSection(to);
        fireData();
    }

    @Override
    public void deleteReport(final Report report) {

        final Section section = report.getSection();
        section.getConference().getReports().remove(report);
        section.getReports().remove(report);
        report.setSection(null);
        fireData();
    }

    @Override
    public void fireData() {

        final StorageProvider storageProvider = StorageProvider.getInstance();
        storageProvider.persist(this.model);
    }

    @Override
    public void updateConference(final Conference storedConference,
            final Conference updatedConference) {

    }

    @Override
    public void deleteSection(final Section selectedElement) {

        selectedElement.getConference().getSections().remove(selectedElement);
        selectedElement.setConference(null);
    }

    @Override
    public List<Report> getReports() {

        return this.reports;
    }

    @Override
    public int getSectionOrder(Section section) {

        for (Conference conf : conferences) {
            List<Section> sections = conf.getSections();
            ListIterator<Section> iterator = sections.listIterator();
            while(iterator.hasNext()) {
                Section currentSection = iterator.next();
                if (isSameSections(currentSection, section)) {
                    return iterator.previousIndex();
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private boolean isSameSections(Section first, Section second) {

        Date firstStartDate = first.getDate();
        Date secondStartDate = second.getDate();
        String firstTitle = first.getTitle();
        String secondTitle = second.getTitle();
        return firstStartDate.equals(secondStartDate)
                && firstTitle.equals(secondTitle);
    }

    @Override
    public void addConference(Conference conference) {

        if (!this.conferences.contains(conference)) {
            this.conferences.add(conference);
        }
        ConferenceViewState conferenceViewState = workspace.getConferencePartState();
        List<Conference> openedConferences = conferenceViewState.getOpenedConferences();
        if (!openedConferences.contains(conference)) {
            openedConferences.add(conference);
        }
        fireData();
    }

    @Override
    public void deleteConference(Conference conference) {

        this.conferences.remove(conference);
        closeConference(conference);
    }

    @Override
    public void closeConference(Conference conference) {

        ConferenceViewState conferenceViewState = workspace.getConferencePartState();
        List<Conference> openedConferences = conferenceViewState.getOpenedConferences();
        openedConferences.remove(conference);
        fireData();
    }

    @Override
    public void openConference(Conference conference) {

        ConferenceViewState conferenceViewState = workspace.getConferencePartState();
        List<Conference> openedConferences = conferenceViewState.getOpenedConferences();
        openedConferences.add(conference);
        fireData();
    }

    @Override
    public List<Conference> getClosedConferences() {

        List<Conference> conferences = new ArrayList<Conference>(this.conferences);
        List<Conference> openedConferences = workspace.getConferencePartState()
                .getOpenedConferences();
        for (Conference openedConference : openedConferences) {
            conferences.remove(openedConference);
        }
        return conferences;
    }

    @Override
    public void addParticipant(Conference conf, Participant participant) {

        conf.getParticipants().add(participant);
        participant.setConference(conf);
    }
}
