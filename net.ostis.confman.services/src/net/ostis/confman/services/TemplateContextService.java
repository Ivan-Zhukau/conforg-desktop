package net.ostis.confman.services;

import java.util.Map;

public interface TemplateContextService {

    public void setTemplateContext(Map<String, Object> templateContext);

    public Map<String, Object> getTemplateContext();

    public void addValueToTemplateContext(String key, Object value);

    public void initTemplateContext(Object object, Object object2);

}
