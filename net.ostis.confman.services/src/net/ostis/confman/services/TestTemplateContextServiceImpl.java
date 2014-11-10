package net.ostis.confman.services;

import java.util.HashMap;
import java.util.Map;

import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Participant;

public class TestTemplateContextServiceImpl implements TemplateContextService {

    public Map<String, Object> templateContext;

    public TestTemplateContextServiceImpl() {

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
    public void initTemplateContext(final Object object) {

        if (object instanceof Participant) {
            final ServiceLocator serviceLocator = ServiceLocator.getInstance();
            final SafeConversionService safeConversionService = (SafeConversionService) serviceLocator
                    .getService(SafeConversionService.class);
            final Participant participant = (Participant) object;
            if (participant.getConference() == null) {
                participant.setConference(new Conference());
            }
            this.templateContext.put("conferenceTitle", safeConversionService
                    .safeConverter(participant.getConference().getTitle()));
            this.templateContext.put("fullName", safeConversionService
                    .safeConverter(participant.getPerson().getFullName()));
            this.templateContext.put("conferenceDate", safeConversionService
                    .safeConverter(participant.getConference().getStartDate()));
            final StringBuilder address = new StringBuilder();
            final Address conferenceAddress = participant.getConference()
                    .getConferenceVenue();
            address.append(safeConversionService
                    .safeConverter(conferenceAddress.getCountry()) + ", ");
            address.append(safeConversionService
                    .safeConverter(conferenceAddress.getCity()) + ", ");
            address.append(safeConversionService
                    .safeConverter(conferenceAddress.getStreet()) + ", ");
            address.append(safeConversionService
                    .safeConverter(conferenceAddress.getHouseNumber()));
            this.templateContext.put("conferenceAddress", address.toString());
        }
    }

}
