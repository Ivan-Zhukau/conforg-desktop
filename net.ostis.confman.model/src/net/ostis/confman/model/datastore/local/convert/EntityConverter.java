package net.ostis.confman.model.datastore.local.convert;

import net.ostis.confman.model.datastore.StorageProvider;
import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.xml.Conferences;
import net.ostis.confman.model.entity.xml.Participants;
import net.ostis.confman.model.entity.xml.Persons;
import net.ostis.confman.model.entity.xml.Reports;
import net.ostis.confman.model.entity.xml.SectionSettings;
import net.ostis.confman.model.entity.xml.Sections;
import net.ostis.confman.model.entity.xml.Workspace;
import net.ostis.confman.services.common.model.FullModel;

public class EntityConverter {

    private Conferences     conferences;

    private Sections        sections;

    private Reports         reports;

    private Persons         persons;

    private Participants    participants;

    private SectionSettings sectionSettings;

    private Workspace       workspace;

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
        this.sectionSettings = SectionSettingsConverter.convert(model,
                idProvider);
        this.workspace = WorkspaceConverter.convert(model, idProvider);
        StorageProvider.getInstance().update(this.persons, this.participants,
                this.conferences, this.sections, this.reports,
                this.sectionSettings, this.workspace);
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

    public SectionSettings getSectionSettings() {

        return this.sectionSettings;
    }

    public Workspace getWorkspace() {

        return this.workspace;
    }
}
