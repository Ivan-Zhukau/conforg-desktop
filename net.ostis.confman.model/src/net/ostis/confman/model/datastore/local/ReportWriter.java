package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.xml.Reports;

public class ReportWriter extends Writer implements Runnable {

    private Reports reports;

    public ReportWriter(final Reports reports) {

        super();
        this.reports = reports;
    }

    @Override
    public void run() {

        try {
            write(new File(LocalStorageConstants.REPORTS_FILE_LOCATION),
                    this.reports);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
    }
}
