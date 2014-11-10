package net.ostis.confman.ui.common.component;

import java.util.List;

import net.ostis.confman.services.common.model.Participant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class RadioButtonGroup extends Composite implements
        EditableComponent<RadioButtonGroup> {

    private static final int DEFAULT_INDEX = 0;

    private Group            group;

    private DataConverter    dataConverter;

    private ValueComboBinder valueBinder;

    public RadioButtonGroup(final Composite parent, final String labelText) {

        super(parent, SWT.NONE);
        final GridLayout layout = new GridLayout(2, false);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setLayout(layout);
        buildControl(labelText);
    }

    private void buildControl(final String labelText) {

        final GridData dataGridInput = new GridData();
        dataGridInput.grabExcessHorizontalSpace = true;
        dataGridInput.horizontalAlignment = GridData.FILL;
        this.group = new Group(this, SWT.READ_ONLY);
        this.group.setLayoutData(dataGridInput);
        this.group.setText(labelText);
        final Button button = new Button(this.group, SWT.RADIO);
        final Button button1 = new Button(this.group, SWT.CHECK);
        button.setText("Привет");
        button.setLocation(20, 45);
        button.pack();

        button1.setText("Привет1");
        button1.setLocation(20, 25);
        button1.pack();
        this.group.pack();
        // this.group.setItems(items);
    }

    @Override
    public void apply() {

        /*
         * final Object value = this.valueBinder.getValues(); final Object
         * applied = ((List<Object>) value).get(new ArrayList<>());
         * this.valueBinder.setCurrentValue(applied);
         */
    }

    @Override
    public void activate() {

        final Object value = this.valueBinder.getValues();
        String[] items = new String[0];
        if (value != null) {
            items = getItemsValues(value);
        }
        // this.group.setItems(items);
        setSelectedItem();
    }

    private String[] getItemsValues(final Object object) {

        String[] itemsValues = new String[0];

        if (((List<Object>) object).get(0) instanceof Participant) {
            final ItemHelper<Participant> helper = new ItemHelper<Participant>();
            itemsValues = helper.getItems(object);
        }
        if (((List<Object>) object).get(0) instanceof String) {
            final ItemHelper<String> helper = new ItemHelper<String>();
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
                    // this.group.select(objects.indexOf(object));
                    break;
                }
            }
        } else {
            // this.group.select(DEFAULT_INDEX);
        }
    }

    @Override
    public RadioButtonGroup setValueComboBinder(
            final ValueComboBinder valueBinder) {

        this.valueBinder = valueBinder;
        return this;
    }

    @Override
    public RadioButtonGroup setDataConverter(final DataConverter converter) {

        this.dataConverter = converter;
        return this;
    }

    public void setItems(final String[] items) {

        // this.group.setItems(items);
    }

    @Override
    public RadioButtonGroup setValueBinder(final ValueBinder valueBinder) {

        // TODO Auto-generated method stub
        return null;
    }

    public class ItemHelper<T> {

        public String[] getItems(final Object object) {

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
