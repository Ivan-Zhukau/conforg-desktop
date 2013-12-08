package net.ostis.confman.model.datastore.local.convert;

import net.ostis.confman.model.datastore.StorageProvider;
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
        idProvider.init(model);
        this.persons = PersonConverter.convert(model, idProvider);
        this.participants = ParticipantConverter.convert(model, idProvider);
        this.conferences = ConferenceConverter.convert(model, idProvider);
        this.sections = SectionConverter.convert(model, idProvider);
        this.reports = ReportConverter.convert(model, idProvider);
        StorageProvider.getInstance().update(this.persons, this.participants,
                this.conferences, this.sections, this.reports);
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