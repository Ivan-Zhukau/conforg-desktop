package net.ostis.confman.model.datastore;

import java.util.List;
import java.util.concurrent.Callable;

import net.ostis.confman.model.common.concurrency.ConcurrencyThreadExecutor;
import net.ostis.confman.model.datastore.local.ConferenceReader;
import net.ostis.confman.model.datastore.local.ConferenceWriter;
import net.ostis.confman.model.datastore.local.ParticipantReader;
import net.ostis.confman.model.datastore.local.ParticipantWriter;
import net.ostis.confman.model.datastore.local.PersonReader;
import net.ostis.confman.model.datastore.local.PersonWriter;
import net.ostis.confman.model.datastore.local.ReportReader;
import net.ostis.confman.model.datastore.local.ReportWriter;
import net.ostis.confman.model.datastore.local.SectionReader;
import net.ostis.confman.model.datastore.local.SectionSettingsReader;
import net.ostis.confman.model.datastore.local.SectionSettingsWriter;
import net.ostis.confman.model.datastore.local.SectionWriter;
import net.ostis.confman.model.datastore.local.WorkspaceReader;
import net.ostis.confman.model.datastore.local.WorkspaceWriter;
import net.ostis.confman.model.datastore.local.convert.EntityConverter;
import net.ostis.confman.model.entity.Conference;
import net.ostis.confman.model.entity.Conferences;
import net.ostis.confman.model.entity.Participant;
import net.ostis.confman.model.entity.Participants;
import net.ostis.confman.model.entity.Person;
import net.ostis.confman.model.entity.Persons;
import net.ostis.confman.model.entity.Report;
import net.ostis.confman.model.entity.Reports;
import net.ostis.confman.model.entity.Section;
import net.ostis.confman.model.entity.SectionBreaks;
import net.ostis.confman.model.entity.SectionSettings;
import net.ostis.confman.model.entity.Sections;
import net.ostis.confman.model.entity.Workspace;
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

    private SectionSettings        sectionSettings;

    private Workspace              workspace;

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
        this.sectionSettings = loadSectionSettings();
        this.workspace = loadWorkspace();
    }

    private Workspace loadWorkspace() {

        final WorkspaceReader reader = new WorkspaceReader();
        final Workspace loadedData = (Workspace) load(reader);
        return loadedData;
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

    private SectionSettings loadSectionSettings() {

        final SectionSettingsReader reader = new SectionSettingsReader();
        final SectionSettings settings = (SectionSettings) load(reader);
        return settings;
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
        updateSectionSettings(model);
        converter.convertModel(model);
        saveData(converter);
    }

    private void updateSectionSettings(final FullModel model) {

        final List<net.ostis.confman.services.common.model.Section> sectionList = model
                .getSections();
        final List<net.ostis.confman.services.common.model.SectionSettings> settings = model
                .getSectionSettings();
        settings.clear();
        for (final net.ostis.confman.services.common.model.Section section : sectionList) {
            settings.add(section.getSettings());
        }
    }

    private synchronized void saveData(final EntityConverter converter) {

        saveConferences(converter.getConferences());
        saveSections(converter.getSections());
        saveReports(converter.getReports());
        savePersons(converter.getPersons());
        saveParticipants(converter.getParticipants());
        saveSectionSettings(converter.getSectionSettings());
        saveWorkspace(converter.getWorkspace());
    }

    private synchronized void saveWorkspace(final Workspace workspace) {

        final WorkspaceWriter writer = new WorkspaceWriter(workspace);
        save(writer);
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

    private synchronized void saveSectionSettings(
            final SectionSettings sectionSettings) {

        final SectionSettingsWriter writer = new SectionSettingsWriter(
                sectionSettings);
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

    public List<SectionBreaks> getSectionBreaks() {

        return this.sectionSettings.getSectionBreaks();
    }

    public Workspace getWorkspace() {
    
        return this.workspace;
    }

    public Person getPerson(final long id) {

        final List<Person> personsList = this.persons.getPersons();
        for (final Person person : personsList) {
            if (id == person.getId()) {
                return person;
            }
        }
        return null;
    }

    public Participant getParticipant(final long id) {

        final List<Participant> participantsList = this.participants
                .getParticipants();
        for (final Participant participant : participantsList) {
            if (id == participant.getId()) {
                return participant;
            }
        }
        return null;
    }

    public Report getReport(final long id) {

        final List<Report> reportsList = this.reports.getReports();
        for (final Report report : reportsList) {
            if (id == report.getId()) {
                return report;
            }
        }
        return null;
    }

    public Section getSection(final long id) {

        final List<Section> sectionsList = this.sections.getSections();
        for (final Section section : sectionsList) {
            if (id == section.getId()) {
                return section;
            }
        }
        return null;
    }

    public Conference getConference(final long id) {

        final List<Conference> conferencesList = this.conferences
                .getConferences();
        for (final Conference conference : conferencesList) {
            if (id == conference.getId()) {
                return conference;
            }
        }
        return null;
    }

    public void update(final Persons persons, final Participants participants,
            final Conferences conferences, final Sections sections,
            final Reports reports, final SectionSettings sectionSettings,
            final Workspace workspace) {

        this.persons = persons;
        this.participants = participants;
        this.conferences = conferences;
        this.sections = sections;
        this.reports = reports;
        this.sectionSettings = sectionSettings;
        this.workspace = workspace;
    }
}
