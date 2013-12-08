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
    
    private FullModel model;

    public ConferenceServiceImpl() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        model = converter.convertData();
        this.conferences = model.getConferences();
    }

    @Override
    public List<Conference> getConferences() {

        return this.conferences;
    }

    @Override
    public void addSection(Conference conference, Section section) {

        conference.getSections().add(section);
        fireData();
    }

    @Override
    public void addReport(Section section, Report report) {

        section.getConference().getReports().add(report);
        section.getReports().add(report);
        fireData();
    }

    @Override
    public void moveReport(Report report, Section from, Section to) {

        from.getReports().remove(report);
        to.getReports().add(report);
        report.setSection(to);
        fireData();
    }

    @Override
    public void deleteReport(Report report) {

        Section section = report.getSection();
        section.getConference().getReports().remove(report);
        section.getReports().remove(report);
        fireData();
    }
    
    private void fireData() {
        StorageProvider storageProvider = StorageProvider.getInstance();
        storageProvider.persist(model);
    }
}
