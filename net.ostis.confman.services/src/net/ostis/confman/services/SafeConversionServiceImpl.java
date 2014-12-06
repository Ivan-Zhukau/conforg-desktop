package net.ostis.confman.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SafeConversionServiceImpl implements SafeConversionService {

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

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

        return date == null ? "" : DATE_FORMAT.format(date);
    }

    @Override
    public Boolean safeConverter(Boolean bool) {

        return bool == null ? false : bool;
    }
}
