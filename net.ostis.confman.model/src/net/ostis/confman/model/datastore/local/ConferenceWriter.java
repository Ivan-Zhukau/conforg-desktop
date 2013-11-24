package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.Conferences;

public class ConferenceWriter extends Writer implements Runnable {

    private Conferences conferences;

    public ConferenceWriter(final Conferences conferences) {

        super();
        this.conferences = conferences;
    }

    @Override
    public void run() {

        try {
            write(new File(LocalStorageConstants.CONFERENCES_FILE_LOCATION),
                    this.conferences);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
    }
}
