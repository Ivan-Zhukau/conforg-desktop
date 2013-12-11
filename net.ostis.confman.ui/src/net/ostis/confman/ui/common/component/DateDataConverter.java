package net.ostis.confman.ui.common.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateDataConverter implements DataConverter {

    private final DateFormat dateFormat;

    public DateDataConverter() {

        super();
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    }

    @Override
    public String convert(final Object val) {

        return this.dateFormat.format(val);
    }

    @Override
    public Object convert(final String val) {

        try {
            return this.dateFormat.parse(val);
        } catch (final ParseException e) {
            // TODO IZh: provide better implementation in future.
            throw new RuntimeException("invalid date");
        }

    }

}
