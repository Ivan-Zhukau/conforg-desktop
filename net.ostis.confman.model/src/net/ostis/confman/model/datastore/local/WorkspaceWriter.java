package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.xml.Workspace;


public class WorkspaceWriter extends Writer implements Runnable {

    private Workspace workspace;

    public WorkspaceWriter(final Workspace workspace) {

        super();
        this.workspace = workspace;
    }

    @Override
    public void run() {

        try {
            write(new File(LocalStorageConstants.WORKSPACE_FILE_LOCATION),
                    this.workspace);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
    }
}
