package net.ostis.confman.ui.common.component;


public interface  ValueComboBinder {
    
    Object getValues();

    void setValues(Object value);
    
    Object getCurrentValue();
    
    void setCurrentValue(Object value);
    
}
