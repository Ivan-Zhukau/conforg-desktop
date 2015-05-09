package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.xml.Section;
import net.ostis.confman.model.entity.xml.Sections;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Report;

public class SectionConverter {

    public static Sections convert(final FullModel model,
            final IDProvider idProvider) {

        final List<Section> sectionsToStore = convertSections(
                model.getSections(), idProvider);
        final Sections sectionStorage = new Sections();
        sectionStorage.setSections(sectionsToStore);
        return sectionStorage;
    }

    private static List<Section> convertSections(
            final List<net.ostis.confman.services.common.model.Section> sections,
            final IDProvider idProvider) {

        final List<Section> sectionsToStore = new ArrayList<>();
        Section sectionToStore;
        for (final net.ostis.confman.services.common.model.Section section : sections) {
            sectionToStore = convertSection(section, idProvider);
            sectionsToStore.add(sectionToStore);
        }
        return sectionsToStore;
    }

    private static Section convertSection(
            final net.ostis.confman.services.common.model.Section section,
            final IDProvider idProvider) {

        final Section sectionToStore = new Section();
        sectionToStore.setTitle(section.getTitle());
        sectionToStore.setDate(section.getDate());
        sectionToStore.setId(idProvider.getId(section));
        sectionToStore
                .setConferenceId(idProvider.getId(section.getConference()));
        sectionToStore
                .setReports(getReportId(section.getReports(), idProvider));
        return sectionToStore;
    }

    private static List<Long> getReportId(final List<Report> reports,
            final IDProvider idProvider) {

        final List<Long> ids = new ArrayList<>();
        for (final Report report : reports) {
            final Long id = idProvider.getId(report);
            ids.add(id);
        }
        return ids;
    }
}
