package net.ostis.confman.services;

import java.util.HashMap;
import java.util.Map;

import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Participant;


public class AdditionalInfoTemplateContextServiceImpl implements TemplateContextService {
    
    public Map<String, Object> templateContext;

    public AdditionalInfoTemplateContextServiceImpl() {

        super();
        this.templateContext = new HashMap<>();
    }

    @Override
    public void setTemplateContext(final Map<String, Object> templateContext) {

        this.templateContext = templateContext;
    }

    @Override
    public Map<String, Object> getTemplateContext() {

        return this.templateContext;
    }

    @Override
    public void addValueToTemplateContext(final String key, final Object value) {

        this.templateContext.put(key, value);
    }

    @Override
    public void initTemplateContext(final Object object, Object object2) {

        if (object instanceof Participant && object2 instanceof Conference) {
            final ServiceLocator serviceLocator = ServiceLocator.getInstance();
            final SafeConversionService safeConversionService = (SafeConversionService) serviceLocator
                    .getService(SafeConversionService.class);
            final Conference conference = (Conference)object2;
            this.templateContext.put("conferenceTitle",
                    safeConversionService.safeConverter(conference.getTitle()));
        }
    }
}
