package net.ostis.confman.model.datastore.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ostis.confman.model.datastore.StorageProvider;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;

public class IDProvider {

    private Map<Object, Long> idStorage;

    private EntityComparer    comparer;

    public IDProvider() {

        super();
        this.idStorage = new HashMap<Object, Long>();
        this.comparer = new EntityComparer();
    }

    public void init(final FullModel model) {

        final StorageProvider storageProvider = StorageProvider.getInstance();
        formPersons(model.getPersons(), storageProvider.getPersons());
        formParticipants(model.getParticipants(),
                storageProvider.getParticipants());
        formReports(model.getReports(), storageProvider.getReports());
        formSections(model.getSections(), storageProvider.getSections());
        formConferences(model.getConferences(),
                storageProvider.getConferences());
    }

    private void formConferences(
            final List<Conference> conferences,
            final List<net.ostis.confman.model.entity.Conference> storedConferences) {

        for (final Conference conference : conferences) {
            final long id = findConferenceId(conference, storedConferences);
            this.idStorage.put(conference, id);
        }
    }

    private long findConferenceId(final Conference conference,
            final List<net.ostis.confman.model.entity.Conference> conferences) {

        for (final net.ostis.confman.model.entity.Conference storedConference : conferences) {
            if (this.comparer.isSameConferences(conference, storedConference)) {
                return storedConference.getId();
            }
        }
        return IDGenerator.getInstance().nextId();
    }

    private void formSections(final List<Section> sections,
            final List<net.ostis.confman.model.entity.Section> storedSections) {

        for (final Section section : sections) {
            final long id = findSectionId(section, storedSections);
            this.idStorage.put(section, id);
        }
    }

    private long findSectionId(final Section section,
            final List<net.ostis.confman.model.entity.Section> sections) {

        for (final net.ostis.confman.model.entity.Section storedSection : sections) {
            if (this.comparer.isSameSections(section, storedSection)) {
                return storedSection.getId();
            }
        }
        return IDGenerator.getInstance().nextId();
    }

    private void formReports(final List<Report> reports,
            final List<net.ostis.confman.model.entity.Report> storedReports) {

        for (final Report report : reports) {
            final long id = findPersonId(report, storedReports);
            this.idStorage.put(report, id);
        }
    }

    private long findPersonId(final Report report,
            final List<net.ostis.confman.model.entity.Report> reports) {

        final Long existingId = this.idStorage.get(report);
        if (existingId != null) {
            return existingId;
        }
        for (final net.ostis.confman.model.entity.Report storedReport : reports) {
            if (this.comparer.isSameReports(report, storedReport)) {
                return storedReport.getId();
            }
        }
        return IDGenerator.getInstance().nextId();
    }

    private void formPersons(final List<Person> persons,
            final List<net.ostis.confman.model.entity.Person> storedPersons) {

        for (final Person person : persons) {
            final long id = findPersonId(person, storedPersons);
            this.idStorage.put(person, id);
        }
    }

    private long findPersonId(final Person person,
            final List<net.ostis.confman.model.entity.Person> persons) {

        final Long existingId = this.idStorage.get(person);
        if (existingId != null) {
            return existingId;
        }
        for (final net.ostis.confman.model.entity.Person storedPerson : persons) {
            if (this.comparer.isSamePersons(person, storedPerson)) {
                return storedPerson.getId();
            }
        }
        return IDGenerator.getInstance().nextId();
    }

    private void formParticipants(
            final List<Participant> participants,
            final List<net.ostis.confman.model.entity.Participant> storedParticipants) {

        for (final Participant participant : participants) {
            final long id = findParticipantId(participant, storedParticipants);
            this.idStorage.put(participant, id);
        }
    }

    private long findParticipantId(final Participant participant,
            final List<net.ostis.confman.model.entity.Participant> participants) {

        final Long existingId = this.idStorage.get(participant);
        if (existingId != null) {
            return existingId;
        }
        for (final net.ostis.confman.model.entity.Participant storedParticipant : participants) {
            if (this.comparer
                    .isSameParticipants(participant, storedParticipant)) {
                return storedParticipant.getId();
            }
        }
        return IDGenerator.getInstance().nextId();
    }

    public Long getId(final Object object) {

        return this.idStorage.get(object);
    }
}
