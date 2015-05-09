package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.xml.Workspace;


public class WorkspaceReader extends Reader implements Callable<Workspace> {

    public WorkspaceReader() {

        super();
    }

    @Override
    public Workspace call() throws Exception {

        try {
            return (Workspace) read(LocalStorageConstants.WORKSPACE_FILE_LOCATION,
                    Workspace.class);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
        throw new DatastoreException();
    }

}
