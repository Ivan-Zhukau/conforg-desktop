package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.Report;
import net.ostis.confman.model.entity.Reports;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Participant;

public class ReportConverter {

    public static Reports convert(final FullModel model,
            final IDProvider idProvider) {

        final List<Report> reportsToStore = convertReports(model.getReports(),
                idProvider);
        final Reports reportStorage = new Reports();
        reportStorage.setReports(reportsToStore);
        return reportStorage;
    }

    private static List<Report> convertReports(
            final List<net.ostis.confman.services.common.model.Report> reports,
            final IDProvider idProvider) {

        final List<Report> reportsToStore = new ArrayList<>();
        Report reportToStore;
        for (final net.ostis.confman.services.common.model.Report report : reports) {
            reportToStore = convertReport(report, idProvider);
            reportsToStore.add(reportToStore);
        }
        return reportsToStore;
    }

    private static Report convertReport(
            final net.ostis.confman.services.common.model.Report report,
            final IDProvider idProvider) {

        final Report reportToStore = new Report();
        reportToStore.setTitle(report.getTitle());
        reportToStore.setId(idProvider.getId(report));
        reportToStore.setReporter(idProvider.getId(report.getMainAuthor()));
        reportToStore.setSectionId(idProvider.getId(report.getSection()));
        reportToStore.setParticipants(getParticipantId(report.getAllAuthors(),
                idProvider));
        return reportToStore;
    }

    private static List<Long> getParticipantId(
            final List<Participant> participants, final IDProvider idProvider) {

        final List<Long> ids = new ArrayList<>();
        for (final Participant participant : participants) {
            final Long id = idProvider.getId(participant);
            ids.add(id);
        }
        return ids;
    }
}
