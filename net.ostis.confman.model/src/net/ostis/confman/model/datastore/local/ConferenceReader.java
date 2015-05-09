package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.xml.Conferences;

public class ConferenceReader extends Reader implements Callable<Conferences> {

    public ConferenceReader() {

        super();
    }

    @Override
    public Conferences call() throws Exception {

        try {
            return (Conferences) read(
                    LocalStorageConstants.CONFERENCES_FILE_LOCATION,
                    Conferences.class);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
        throw new DatastoreException();
    }

}
