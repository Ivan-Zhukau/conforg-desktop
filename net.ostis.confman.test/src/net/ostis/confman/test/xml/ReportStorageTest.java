package net.ostis.confman.test.xml;

import java.util.Arrays;

import net.ostis.confman.model.datastore.local.ReportWriter;
import net.ostis.confman.model.entity.Report;
import net.ostis.confman.model.entity.Reports;

import org.junit.Test;

public class ReportStorageTest {

    @Test
    public void test() {

        final Report report = new Report();
        report.setTitle("Some Semantic Technologies");
        report.setSectionId(11L);
        report.setReporter(1L);
        report.setParticipants(Arrays.asList(1L, 2L));

        final Reports reports = new Reports();
        reports.setReports(Arrays.asList(report));
        final Runnable command = new ReportWriter(reports);
        command.run();
    }
}
