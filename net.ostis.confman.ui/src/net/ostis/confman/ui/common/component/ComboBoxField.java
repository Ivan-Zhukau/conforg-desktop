package net.ostis.confman.ui.common.component;

import java.util.List;

import net.ostis.confman.services.common.model.Participant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ComboBoxField extends Composite implements
        EditableComponent<ComboBoxField> {

    private static final int DEFAULT_INDEX = 0;
    
    private Combo            comboBox;

    private DataConverter    dataConverter;

    private ValueComboBinder valueBinder;

    public ComboBoxField(final Composite parent, final String labelText,
            final String[] items, GridData gridData) {

        super(parent, SWT.NONE);
        final GridLayout layout = new GridLayout(2, false);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setLayout(layout);
        buildControl(labelText, items, gridData);
    }

    private void buildControl(final String labelText, final String[] items,
            GridData gridData) {

        final Label label = new Label(this, SWT.RIGHT);
        label.setText(labelText);

        if (gridData == null) {
            gridData = new GridData();
            gridData.grabExcessHorizontalSpace = true;
            gridData.horizontalAlignment = GridData.FILL;
        }
        this.comboBox = new Combo(this, SWT.READ_ONLY);
        this.comboBox.setLayoutData(gridData);
        this.comboBox.setItems(items);
    }

    @Override
    public void apply() {

        final Object value = this.valueBinder.getValues();
        final Object applied = ((List<Object>) value).get(this.comboBox.getSelectionIndex());
        this.valueBinder.setCurrentValue(applied);
    }

    @Override
    public void activate() {

        final Object value = this.valueBinder.getValues();
        String[] items = new String[0];
        if (value != null) {
            items = getItemsValues(value);
        }
        this.comboBox.setItems(items);
        setSelectedItem();
    }

    private String[] getItemsValues(final Object object) {

        String[] itemsValues = new String[0];

        if (((List<Object>) object).get(0) instanceof Participant) {
            ItemHelper<Participant> helper = new ItemHelper<Participant>();
            itemsValues = helper.getItems(object);
        }
        if (((List<Object>) object).get(0) instanceof String) {
            ItemHelper<String> helper = new ItemHelper<String>();
            itemsValues = helper.getItems(object);
        }

        return itemsValues;
    }

    private void setSelectedItem() {

        if (this.valueBinder.getCurrentValue() != null) {
            final List<Object> objects = (List<Object>) this.valueBinder
                    .getValues();
            for (final Object object : objects) {
                if (object.equals(this.valueBinder.getCurrentValue())) {
                    this.comboBox.select(objects.indexOf(object));
                    break;
                }
            }
        }else{
            this.comboBox.select(DEFAULT_INDEX);
        }
    }
    
    @Override
    public ComboBoxField setValueComboBinder(final ValueComboBinder valueBinder) {

        this.valueBinder = valueBinder;
        return this;
    }

    @Override
    public ComboBoxField setDataConverter(final DataConverter converter) {

        this.dataConverter = converter;
        return this;
    }

    public void setItems(final String[] items) {

        this.comboBox.setItems(items);
    }

    @Override
    public ComboBoxField setValueBinder(final ValueBinder valueBinder) {

        // should not be used, because it is a multi-value item
        throw new IllegalArgumentException();
    }

    public class ItemHelper<T> {

        public String[] getItems(Object object) {
            String[] itemsValues;
            final List<T> elems = (List<T>) object;
            itemsValues = new String[elems.size()];
            int index = 0;
            for (final T elem : elems) {

                itemsValues[index] = elem.toString();
                ++index;
            }
            return itemsValues;
        }
    }
}
