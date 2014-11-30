package net.ostis.confman.services;

import java.util.HashMap;
import java.util.Map;

import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Participant;


public class PersonalInvitationTemplateContextServiceImpl implements TemplateContextService {
    
    public Map<String, Object> templateContext;

    public PersonalInvitationTemplateContextServiceImpl() {

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
            final Participant participant = (Participant) object;
            final Conference conference = (Conference)object2;
            this.templateContext.put("conferenceTitle",
                    safeConversionService.safeConverter(conference.getTitle()));
            this.templateContext.put("reports", participant.getReports());
            this.templateContext.put("fullName", safeConversionService
                    .safeConverter(participant.getPerson().getFullName()));
            String startDate = safeConversionService.safeConverter(conference.getStartDate());
            String endDate = safeConversionService.safeConverter(conference.getEndDate());
            this.templateContext.put("conferenceDate", startDate + "-" + endDate);
            final StringBuilder address = new StringBuilder();
            final Address conferenceAddress = conference.getConferenceVenue();
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
