package net.ostis.confman.services;

import java.util.HashMap;
import java.util.Map;

public class DefaultTemplateContextServiceImpl implements TemplateContextService {
    
    public Map<String, Object> templateContext;

    public DefaultTemplateContextServiceImpl() {

        super();
        this.templateContext = new HashMap<>();
    }

    @Override
    public void setTemplateContext(Map<String, Object> templateContext) {

        this.templateContext = templateContext;
        
    }

    @Override
    public Map<String, Object> getTemplateContext() {

        return this.templateContext;
    }

    @Override
    public void addValueToTemplateContext(String key, Object value) {

        this.templateContext.put(key, value);
        
    }

    @Override
    public void initTemplateContext(Object object, Object object2) {
        
        //Nothing to do
    }

}
