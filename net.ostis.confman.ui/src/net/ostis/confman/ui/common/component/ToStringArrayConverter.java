package net.ostis.confman.ui.common.component;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.services.common.model.Participant;

public class ToStringArrayConverter {

    public ToStringArrayConverter() {

    }

    public String[] convert(final Object object) {

        final List<Participant> list = (List<Participant>) object;
        final String[] array = new String[list.size()];
        int index = 0;
        for (final Participant partisipant : list) {
            array[index] = new StringDataConverter().convert(object);
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
