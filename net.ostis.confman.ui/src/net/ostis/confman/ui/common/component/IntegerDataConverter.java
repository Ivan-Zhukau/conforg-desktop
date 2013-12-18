package net.ostis.confman.ui.common.component;

public class IntegerDataConverter implements DataConverter {

    @Override
    public String convert(final Object val) {

        return val.toString();
    }

    @Override
    public Object convert(final String val) {

        return Integer.parseInt(val);
    }

}
