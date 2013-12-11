package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.model.datastore.StorageProvider;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;

class ConferenceServiceImpl implements ConferenceService {

    private List<Conference> conferences;

    private FullModel        model;

    public ConferenceServiceImpl() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        this.conferences = this.model.getConferences();
    }

    @Override
    public List<Conference> getConferences() {

        return this.conferences;
    }

    @Override
    public void addSection(final Conference conference, final Section section) {

        conference.getSections().add(section);
        fireData();
    }

    @Override
    public void addReport(final Section section, final Report report) {

        section.getConference().getReports().add(report);
        section.getReports().add(report);
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

    }

    @Override
    public List<Report> getReports() {

        // TODO kfs: provide better solution in future.
        return this.conferences.get(0).getReports();
    }
}
