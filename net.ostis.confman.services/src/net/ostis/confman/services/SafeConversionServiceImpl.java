package net.ostis.confman.services;

import java.util.Date;

import net.ostis.confman.services.common.model.Conference;

public class SafeConversionServiceImpl implements SafeConversionService {

    @Override
    public String safeConverter(final String value) {

        return value == null ? "" : value;
    }

    @Override
    public Integer safeConverter(final Integer value) {

        return value == null ? 0 : value;
    }

    @Override
    public String safeConverter(final Date date) {

        return date == null ? "" : date.toString();
    }

    @Override
    public Conference safeConverter(final Conference conference) {

        return conference == null ? new Conference() : conference;
    }

}
