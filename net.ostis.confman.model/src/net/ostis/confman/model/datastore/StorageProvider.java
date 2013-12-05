package net.ostis.confman.model.datastore;

import java.util.List;
import java.util.concurrent.Callable;

import net.ostis.confman.model.common.concurrency.ConcurrencyThreadExecutor;
import net.ostis.confman.model.convert.EntityConverter;
import net.ostis.confman.model.datastore.local.ConferenceReader;
import net.ostis.confman.model.datastore.local.ConferenceWriter;
import net.ostis.confman.model.datastore.local.ParticipantReader;
import net.ostis.confman.model.datastore.local.ParticipantWriter;
import net.ostis.confman.model.datastore.local.PersonReader;
import net.ostis.confman.model.datastore.local.PersonWriter;
import net.ostis.confman.model.datastore.local.ReportReader;
import net.ostis.confman.model.datastore.local.ReportWriter;
import net.ostis.confman.model.datastore.local.SectionReader;
import net.ostis.confman.model.datastore.local.SectionWriter;
import net.ostis.confman.model.entity.Conference;
import net.ostis.confman.model.entity.Conferences;
import net.ostis.confman.model.entity.Participant;
import net.ostis.confman.model.entity.Participants;
import net.ostis.confman.model.entity.Person;
import net.ostis.confman.model.entity.Persons;
import net.ostis.confman.model.entity.Report;
import net.ostis.confman.model.entity.Reports;
import net.ostis.confman.model.entity.Section;
import net.ostis.confman.model.entity.Sections;
import net.ostis.confman.services.common.model.FullModel;

import org.apache.log4j.Logger;

public class StorageProvider {

    public static final Logger     LOGGER = Logger.getLogger(StorageProvider.class);

    private static StorageProvider INSTANCE;

    private Persons                persons;

    private Participants           participants;

    private Reports                reports;

    private Sections               sections;

    private Conferences            conferences;

    private StorageProvider() {

        super();
    }

    public static StorageProvider getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new StorageProvider();
            INSTANCE.loadOnStartup();
        }
        return INSTANCE;
    }

    private void loadOnStartup() {

        this.persons = loadPersons();
        this.participants = loadParticipants();
        this.reports = loadReports();
        this.sections = loadSections();
        this.conferences = loadConferences();
    }

    private Persons loadPersons() {

        final PersonReader reader = new PersonReader();
        final Persons loadedData = (Persons) load(reader);
        return loadedData;
    }

    private Participants loadParticipants() {

        final ParticipantReader reader = new ParticipantReader();
        final Participants loadedData = (Participants) load(reader);
        return loadedData;
    }

    private Reports loadReports() {

        final ReportReader reader = new ReportReader();
        final Reports loadedData = (Reports) load(reader);
        return loadedData;
    }

    private Sections loadSections() {

        final SectionReader reader = new SectionReader();
        final Sections loadedData = (Sections) load(reader);
        return loadedData;
    }

    private Conferences loadConferences() {

        final ConferenceReader reader = new ConferenceReader();
        final Conferences loadedData = (Conferences) load(reader);
        return loadedData;
    }

    private Object load(final Callable<?> reader) {

        try {
            return reader.call();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void persist(final FullModel model) {

        final EntityConverter converter = new EntityConverter();
        converter.convertModel(model);
        saveData(converter);
    }

    private synchronized void saveData(final EntityConverter converter) {

        saveConferences(converter.getConferences());
        saveSections(converter.getSections());
        saveReports(converter.getReports());
        savePersons(converter.getPersons());
        saveParticipants(converter.getParticipants());
    }

    private synchronized void saveConferences(
            final Conferences convertedConferences) {

        final ConferenceWriter writer = new ConferenceWriter(
                convertedConferences);
        save(writer);
    }

    private synchronized void saveSections(final Sections convertedSections) {

        final SectionWriter writer = new SectionWriter(convertedSections);
        save(writer);
    }

    private synchronized void saveReports(final Reports convertedReports) {

        final ReportWriter writer = new ReportWriter(convertedReports);
        save(writer);
    }

    private synchronized void savePersons(final Persons convertedPersons) {

        final PersonWriter writer = new PersonWriter(convertedPersons);
        save(writer);
    }

    private synchronized void saveParticipants(
            final Participants convertedParticipants) {

        final ParticipantWriter writer = new ParticipantWriter(
                convertedParticipants);
        save(writer);
    }

    private synchronized void save(final Runnable command) {

        final ConcurrencyThreadExecutor threadExecutor = ConcurrencyThreadExecutor
                .getInstance();
        threadExecutor.runTask(command);
    }

    public List<Person> getPersons() {

        return this.persons.getPersons();
    }

    public List<Participant> getParticipants() {

        return this.participants.getParticipants();
    }

    public List<Report> getReports() {

        return this.reports.getReports();
    }

    public List<Section> getSections() {

        return this.sections.getSections();
    }

    public List<Conference> getConferences() {

        return this.conferences.getConferences();
    }
}
