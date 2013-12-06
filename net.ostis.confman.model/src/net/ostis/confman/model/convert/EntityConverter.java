package net.ostis.confman.model.convert;

import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.Conferences;
import net.ostis.confman.model.entity.Participants;
import net.ostis.confman.model.entity.Persons;
import net.ostis.confman.model.entity.Reports;
import net.ostis.confman.model.entity.Sections;
import net.ostis.confman.services.common.model.FullModel;

public class EntityConverter {

    private Conferences  conferences;

    private Sections     sections;

    private Reports      reports;

    private Persons      persons;

    private Participants participants;

    public EntityConverter() {

        super();
    }

    public void convertModel(final FullModel model) {

        final IDProvider idProvider = new IDProvider();
        idProvider.init(model, this);
        final Persons persons = PersonConverter.convert(model, idProvider);
        final Participants participants = ParticipantConverter.convert(model,
                idProvider);
        final Conferences conferences = ConferenceConverter.convert(model,
                idProvider);
        final Sections sections = SectionConverter.convert(model, idProvider);
        final Reports reports = ReportConverter.convert(model, idProvider);
        update(persons, participants, conferences, sections, reports);
    }

    private void update(final Persons persons, final Participants participants,
            final Conferences conferences, final Sections sections,
            final Reports reports) {

        this.persons = persons;
        this.participants = participants;
        this.conferences = conferences;
        this.sections = sections;
        this.reports = reports;
    }

    public Conferences getConferences() {

        return this.conferences;
    }

    public Sections getSections() {

        return this.sections;
    }

    public Reports getReports() {

        return this.reports;
    }

    public Persons getPersons() {

        return this.persons;
    }

    public Participants getParticipants() {

        return this.participants;
    }
}
