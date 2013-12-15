package net.ostis.confman.model.common.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrencyThreadExecutor {

    private static final int                 POOL_SIZE = 10;

    private static ConcurrencyThreadExecutor instance;

    private ExecutorService                  executor;

    private ConcurrencyThreadExecutor() {

        super();
    }

    public static ConcurrencyThreadExecutor getInstance() {

        if (instance == null) {
            instance = new ConcurrencyThreadExecutor();
            instance.init();
        }
        return instance;
    }

    private void init() {

        this.executor = Executors.newFixedThreadPool(POOL_SIZE);
    }

    public void runTask(final Runnable task) {

        this.executor.execute(task);
    }

    public Object callTask(final Callable<?> task) throws InterruptedException,
            ExecutionException {

        final Future<?> result = this.executor.submit(task);
        return result.get();
    }

    public void shutdownAll() {

        this.executor.shutdown();
    }
}
