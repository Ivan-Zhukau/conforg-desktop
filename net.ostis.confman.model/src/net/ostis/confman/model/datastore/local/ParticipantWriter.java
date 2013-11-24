package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.Participants;

public class ParticipantWriter extends Writer implements Runnable {

    private Participants patricipants;

    public ParticipantWriter(final Participants patricipants) {

        super();
        this.patricipants = patricipants;
    }

    @Override
    public void run() {

        try {
            write(new File(LocalStorageConstants.PARTICIPANTS_FILE_LOCATION),
                    this.patricipants);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
    }
}
