package net.ostis.confman.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TemplateContextServiceLocator {

    private static final int                     DEF_SERV_COUNT = 100;

    private static TemplateContextServiceLocator INSTANCE;

    private final Map<String, Object>            serviceImpls;

    private TemplateContextServiceLocator() {

        super();
        this.serviceImpls = new ConcurrentHashMap<>(DEF_SERV_COUNT);
    }

    private void initialize() {

        this.serviceImpls.put("Test", new TestTemplateContextServiceImpl());
    }

    public static TemplateContextServiceLocator getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new TemplateContextServiceLocator();
            INSTANCE.initialize();
        }
        return INSTANCE;
    }

    public Object getService(final String templateName) {
        
        if(this.serviceImpls.containsKey(templateName)){
            return this.serviceImpls.get(templateName); 
        } else {
            return new DefaultTemplateContextServiceImpl();
        }
    }
}