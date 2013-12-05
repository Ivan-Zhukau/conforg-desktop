package net.ostis.confman.model.convert;

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

        this.conferences = ConferenceConverter.convert(model);
        this.sections = SectionConverter.convert(model);
        this.reports = ReportConverter.convert(model);
        this.persons = PersonConverter.convert(model);
        this.participants = ParticipantConverter.convert(model);
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
