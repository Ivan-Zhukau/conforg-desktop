package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.Persons;

public class PersonWriter extends Writer implements Runnable {

    private Persons persons;

    public PersonWriter(final Persons persons) {

        super();
        this.persons = persons;
    }

    @Override
    public void run() {

        try {
            write(new File(LocalStorageConstants.PERSONS_FILE_LOCATION),
                    this.persons);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
    }
}
