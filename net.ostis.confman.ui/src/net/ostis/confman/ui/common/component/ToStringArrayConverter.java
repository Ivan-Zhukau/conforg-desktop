package net.ostis.confman.ui.common.component;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.services.common.model.Participant;


public class ToStringArrayConverter {
    
    public ToStringArrayConverter() {
    }
    
    public String[] convert(List<Participant> list){
        String[] array = new String[list.size()];
        int index = 0;
        for(Participant object: list){
            array[index] = new StringDataConverter().convert(object);
            ++index;
        }
        return array;
    }
    
    public List<Object> convert(String[] array){
        List<Object> list = new ArrayList<Object>();
        for(String string: array){
            list.add(new StringDataConverter().convert(string));
        }
        return list;
    }
    
}
