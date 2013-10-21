package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.Authors;

public class AuthorsWriter extends Writer implements Runnable {

    private Authors authors;

    public AuthorsWriter(final Authors authors) {

        super();
        this.authors = authors;
    }

    @Override
    public void run() {

        try {
            write(new File(LocalStorageConstants.AUTHORS_FILE_LOCATION),
                    this.authors);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
    }
}
