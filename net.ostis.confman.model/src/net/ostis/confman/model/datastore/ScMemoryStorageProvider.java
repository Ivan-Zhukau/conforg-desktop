package net.ostis.confman.model.datastore;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.ostis.confman.model.dao.ConferenceDAO;
import net.ostis.confman.model.dao.ParticipantDAO;
import net.ostis.confman.model.dao.PersonDAO;
import net.ostis.confman.model.dao.ReportDAO;
import net.ostis.confman.model.dao.SectionDAO;
import net.ostis.confman.model.dao.SectionSettingsDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.dao.impl.scmemory.ConferenceDAOImpl;
import net.ostis.confman.model.dao.impl.scmemory.ParticipantDAOImpl;
import net.ostis.confman.model.dao.impl.scmemory.PersonDAOImpl;
import net.ostis.confman.model.dao.impl.scmemory.ReportDAOImpl;
import net.ostis.confman.model.dao.impl.scmemory.SectionDAOImpl;
import net.ostis.confman.model.dao.impl.scmemory.SectionSettingsDAOImpl;
import net.ostis.confman.model.entity.scmemory.Conference;
import net.ostis.confman.model.entity.scmemory.Participant;
import net.ostis.confman.model.entity.scmemory.Person;
import net.ostis.confman.model.entity.scmemory.Report;
import net.ostis.confman.model.entity.scmemory.Section;
import net.ostis.confman.model.entity.scmemory.SectionSettings;

public enum ScMemoryStorageProvider {

    INSTANCE;

    private static final Logger         LOGGER = Logger.getLogger(ScMemoryStorageProvider.class);

    private final List<Conference>      conferences;

    private final List<Participant>     participants;

    private final List<Person>          persons;

    private final List<Section>         sections;

    private final List<Report>          reports;

    private final List<SectionSettings> sectionSettings;

    private ScMemoryStorageProvider() {

        conferences = loadConferences();
        participants = loadParticipants();
        persons = loadPersons();
        sections = loadSections();
        reports = loadReports();
        sectionSettings = loadSectionSettings();
    }

    private List<SectionSettings> loadSectionSettings() {

        SectionSettingsDAO sectionSettingsDAO = new SectionSettingsDAOImpl();
        try {
            return sectionSettingsDAO.readAll();
        } catch (DAOException e) {
            LOGGER.error("cannot load section settings via sc connector", e);
        }
        return new ArrayList<SectionSettings>();
    }

    private List<Report> loadReports() {

        ReportDAO reportDAO = new ReportDAOImpl();
        try {
            return reportDAO.readAll();
        } catch (DAOException e) {
            LOGGER.error("cannot load reports via sc connector", e);
        }
        return new ArrayList<Report>();
    }

    private List<Section> loadSections() {

        SectionDAO sectionDAO = new SectionDAOImpl();
        try {
            return sectionDAO.readAll();
        } catch (DAOException e) {
            LOGGER.error("cannot load sections via sc connector", e);
        }
        return new ArrayList<Section>();
    }

    private List<Person> loadPersons() {

        PersonDAO personDAO = new PersonDAOImpl();
        try {
            return personDAO.readAll();
        } catch (DAOException e) {
            LOGGER.error("cannot load persons via sc connector", e);
        }
        return new ArrayList<Person>();
    }

    private List<Participant> loadParticipants() {

        ParticipantDAO participantDAO = new ParticipantDAOImpl();
        try {
            return participantDAO.readAll();
        } catch (DAOException e) {
            LOGGER.error("cannot load participants via sc connector", e);
        }
        return new ArrayList<Participant>();
    }

    private List<Conference> loadConferences() {

        ConferenceDAO conferenceDAO = new ConferenceDAOImpl();
        try {
            List<Conference> conferences = conferenceDAO.readAll();
            return conferences;
        } catch (DAOException e) {
            LOGGER.error("cannot load conferences via sc connector", e);
        }
        return new ArrayList<Conference>();
    }

    public List<Conference> getConferences() {

        return conferences;
    }

    public List<Participant> getParticipants() {

        return participants;
    }

    public List<Person> getPersons() {

        return persons;
    }

    public List<Section> getSections() {

        return sections;
    }

    public List<Report> getReports() {

        return reports;
    }

    public List<SectionSettings> getSectionSettings() {

        return sectionSettings;
    }

}
