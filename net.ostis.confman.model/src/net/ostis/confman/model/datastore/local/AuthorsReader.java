package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.Authors;

public class AuthorsReader extends Reader implements Callable<Authors> {

    public AuthorsReader() {

        super();
    }

    @Override
    public Authors call() throws Exception {

        try {
            return (Authors) read(LocalStorageConstants.AUTHORS_FILE_LOCATION,
                    Authors.class);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
        return new Authors();
    }
}
