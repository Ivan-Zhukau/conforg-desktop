package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.Participants;

public class ParticipantReader extends Reader implements Callable<Participants> {

    public ParticipantReader() {

        super();
    }

    @Override
    public Participants call() throws Exception {

        try {
            return (Participants) read(
                    LocalStorageConstants.PARTICIPANTS_FILE_LOCATION,
                    Participants.class);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
        throw new DatastoreException();
    }
}
