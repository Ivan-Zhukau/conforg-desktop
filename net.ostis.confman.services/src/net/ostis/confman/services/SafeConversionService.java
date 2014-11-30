package net.ostis.confman.services;

import java.util.Date;

public interface SafeConversionService {

    public String safeConverter(String value);

    public Integer safeConverter(Integer value);

    public String safeConverter(Date date);

}
