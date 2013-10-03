package net.ostis.confman.ui.common.component;

public class StringDataConverter implements DataConverter {

    public StringDataConverter() {

        super();
    }

    @Override
    public String convert(final Object val) {

        return val.toString();
    }

    @Override
    public Object convert(final String val) {

        return val;
    }

}
