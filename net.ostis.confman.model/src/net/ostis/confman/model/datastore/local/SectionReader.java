package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.Sections;

public class SectionReader extends Reader implements Callable<Sections> {

    public SectionReader() {

        super();
    }

    @Override
    public Sections call() throws Exception {

        try {
            return (Sections) read(
                    LocalStorageConstants.SECTIONS_FILE_LOCATION,
                    Sections.class);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
        throw new DatastoreException();
    }
}
