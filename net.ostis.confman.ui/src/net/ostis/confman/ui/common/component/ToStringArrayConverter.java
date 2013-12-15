package net.ostis.confman.ui.common.component;

import java.util.ArrayList;
import java.util.List;

public class ToStringArrayConverter {

    public ToStringArrayConverter() {

    }

    public String[] convert(final Object object) {

        final List<Object> list = (List<Object>) object;
        final String[] array = new String[list.size()];
        int index = 0;
        for (final Object element : list) {
            array[index] = new StringDataConverter().convert(element);
            ++index;
        }
        return array;
    }

    public Object convert(final String[] array) {

        final List<Object> list = new ArrayList<Object>();
        for (final String string : array) {
            list.add(new StringDataConverter().convert(string));
        }
        return list;
    }

}
