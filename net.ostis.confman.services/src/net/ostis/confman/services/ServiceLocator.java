package net.ostis.confman.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLocator {

    private static final int         DEF_SERV_COUNT = 100;

    private static ServiceLocator    INSTANCE;

    private final Map<Class, Object> serviceImpls;

    private ServiceLocator() {

        super();
        this.serviceImpls = new ConcurrentHashMap<>(DEF_SERV_COUNT);
    }

    private void initialize() {

        this.serviceImpls.put(RegistrationService.class,
                new RegistrationServiceImpl());
    }

    public static ServiceLocator getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new ServiceLocator();
            INSTANCE.initialize();
        }
        return INSTANCE;
    }

    public Object getService(final Class c) {

        return this.serviceImpls.get(c);
    }

}
