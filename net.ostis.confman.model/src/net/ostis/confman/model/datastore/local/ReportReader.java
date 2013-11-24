package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.Reports;

public class ReportReader extends Reader implements Callable<Reports> {

    public ReportReader() {

        super();
    }

    @Override
    public Reports call() throws Exception {

        try {
            return (Reports) read(LocalStorageConstants.REPORTS_FILE_LOCATION,
                    Reports.class);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
        throw new DatastoreException();
    }
}
