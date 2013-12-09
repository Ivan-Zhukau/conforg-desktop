package net.ostis.confman.model.datastore.util;

import net.ostis.confman.model.datastore.StorageProvider;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;

public class EntityComparer {

    public EntityComparer() {

        super();
    }

    public boolean isSamePersons(final Person person,
            final net.ostis.confman.model.entity.Person storedPerson) {

        if (person.getFirstName() == null || person.getPatronymic() == null
                || person.getSurname() == null
                || storedPerson.getFirstName() == null
                || storedPerson.getPatronymic() == null
                || storedPerson.getSurname() == null) {
            return false;
        }
        return storedPerson.getFirstName().equals(person.getFirstName())
                && storedPerson.getPatronymic().equals(person.getPatronymic())
                && storedPerson.getSurname().equals(person.getSurname());
    }

    public boolean isSameParticipants(final Participant participant,
            final net.ostis.confman.model.entity.Participant storedParticipant) {

        final StorageProvider storageProvider = StorageProvider.getInstance();
        if (participant.getConference() != null
                && participant.getPerson() != null
                && storedParticipant.getConferenceId() != null
                && storedParticipant.getPersonId() != null) {
            return isSameConferences(participant.getConference(),
                    storageProvider.getConference(storedParticipant
                            .getConferenceId()))
                    && isSamePersons(participant.getPerson(),
                            storageProvider.getPerson(storedParticipant
                                    .getPersonId()));
        }
        return false;
    }

    public boolean isSameReports(final Report report,
            final net.ostis.confman.model.entity.Report storedReport) {

        final StorageProvider storageProvider = StorageProvider.getInstance();
        if (report.getMainAuthor() != null && report.getTitle() != null
                && storedReport.getReporter() != null
                && storedReport.getTitle() != null) {
            return isSameParticipants(report.getMainAuthor(),
                    storageProvider.getParticipant(storedReport.getReporter()))
                    && report.getTitle().equals(storedReport.getTitle());
        }
        return false;
    }

    public boolean isSameSections(final Section section,
            final net.ostis.confman.model.entity.Section storedSection) {

        final StorageProvider storageProvider = StorageProvider.getInstance();
        if (section.getConference() != null && section.getTitle() != null
                && storedSection.getConferenceId() != null
                && storedSection.getTitle() != null) {
            return isSameConferences(section.getConference(),
                    storageProvider.getConference(storedSection
                            .getConferenceId()))
                    && section.getTitle().equals(storedSection.getTitle());
        }
        return false;
    }

    public boolean isSameConferences(final Conference conference,
            final net.ostis.confman.model.entity.Conference storedConference) {

        if (conference.getTitle() != null && conference.getStartDate() != null
                && storedConference.getTitle() != null
                && storedConference.getStartDate() != null) {
            return conference.getTitle().equals(storedConference.getTitle())
                    && conference.getStartDate().equals(
                            storedConference.getStartDate());
        }
        return false;
    }
}
