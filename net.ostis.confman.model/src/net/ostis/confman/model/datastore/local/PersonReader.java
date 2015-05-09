package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.xml.Persons;

public class PersonReader extends Reader implements Callable<Persons> {

    public PersonReader() {

        super();
    }

    @Override
    public Persons call() throws Exception {

        try {
            return (Persons) read(LocalStorageConstants.PERSONS_FILE_LOCATION,
                    Persons.class);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
        throw new DatastoreException();
    }
}
