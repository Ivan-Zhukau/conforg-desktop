package net.ostis.confman.model.datastore.dao;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import net.ostis.confman.model.common.concurrency.ConcurrencyThreadExecutor;
import net.ostis.confman.model.datastore.local.AuthorsReader;
import net.ostis.confman.model.datastore.local.AuthorsWriter;
import net.ostis.confman.model.entity.Authors;

public class AuthorsDAO {

    public AuthorsDAO() {

        super();
    }

    public Authors getAuthors() {

        final Callable<Authors> task = new AuthorsReader();
        Authors result;
        try {
            result = (Authors) ConcurrencyThreadExecutor.getInstance()
                    .callTask(task);
        } catch (InterruptedException | ExecutionException e) {
            StorageProvider.LOGGER.error(e);
            result = new Authors();
        }
        return result;
    }

    public void saveAuthors(final Authors authors) {

        final Runnable command = new AuthorsWriter(authors);
        ConcurrencyThreadExecutor.getInstance().runTask(command);
    }
}
