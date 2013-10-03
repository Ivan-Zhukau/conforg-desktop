package net.ostis.confman.ui.common.component;

public interface DataConverter {

    String convert(Object val);

    Object convert(String val);
}
