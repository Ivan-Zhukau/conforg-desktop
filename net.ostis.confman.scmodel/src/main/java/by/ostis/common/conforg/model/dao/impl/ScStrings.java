package by.ostis.common.conforg.model.dao.impl;

import by.ostis.common.sctpclient.model.ScString;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.UUID;

final class ScStrings {

    private ScStrings() {}

    public static ScString wrap(String value) {
        return StringUtils.isNotEmpty(value) ? new ScString(value) : new ScString(StringUtils.EMPTY);
    }


    public static ScString wrap(Boolean value) {
        return value == null ? new ScString("0") : new ScString(value ? "1" : "0");
    }

    public static ScString wrap(UUID uuid) {
        final String systemId = uuid.toString();
        return new ScString(systemId);
    }

    public static ScString wrap(DateTime dateTime) {
        ScString result;
        if(dateTime != null){
            DateTimeFormatter formatter = ISODateTimeFormat.basicDateTime();
            String stringDate = formatter.print(dateTime);
            result = new ScString(stringDate);
        } else {
            result = new ScString(StringUtils.EMPTY);
        }
        return result;
    }

    public static ScString wrap(Integer value) {
        return value == null ? new ScString(StringUtils.EMPTY) : new ScString(value.toString());
    }

    public static String unwrapToString(String scString) {
        return StringUtils.EMPTY.equals(scString) ? StringUtils.EMPTY : scString;
    }

    public static DateTime unwrapToDateTime(String scString) {
        DateTime result;
        if(!scString.equals(StringUtils.EMPTY)){
            DateTimeFormatter formatter = ISODateTimeFormat.basicDateTime();
            result = formatter.parseDateTime(scString);
        } else {
            result = null;
        }
        return result;
    }

    public static Integer unwrapToInteger(String scString) {
        return scString.equals(StringUtils.EMPTY) ? null : new Integer(scString);
    }

    public static Boolean unwrapToBoolean(String value) {
        return value.equals("0") ? Boolean.FALSE : Boolean.TRUE;
    }
}
