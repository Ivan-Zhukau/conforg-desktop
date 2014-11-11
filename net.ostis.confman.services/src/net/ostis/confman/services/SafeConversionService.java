package net.ostis.confman.services;

import java.util.Date;

import net.ostis.confman.services.common.model.Conference;

public interface SafeConversionService {

    public String safeConverter(String value);

    public Integer safeConverter(Integer value);

    public Date safeConverter(Date date);

    public Conference safeConverter(Conference conference);

}
