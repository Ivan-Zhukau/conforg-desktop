package net.ostis.confman.ui.common.component;

public interface EditableComponent<T> {

    void apply();

    void activate();

    T setValueBinder(ValueBinder valueBinder);

    T setValueComboBinder(ValueComboBinder valueBinder);

    T setDataConverter(DataConverter converter);
}
