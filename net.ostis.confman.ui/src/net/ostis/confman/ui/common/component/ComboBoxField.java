package net.ostis.confman.ui.common.component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ComboBoxField extends Composite implements
        EditableComponent<ComboBoxField> {

    private Combo         comboBox;

    private DataConverter dataConverter;

    private ValueBinder   valueBinder;

    public ComboBoxField(final Composite parent, final String labelText,
            final String[] items) {

        super(parent, SWT.NONE);
        final GridLayout layout = new GridLayout(2, false);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setLayout(layout);
        buildControl(labelText, items);
    }

    private void buildControl(final String labelText, final String[] items) {

        final Label label = new Label(this, SWT.RIGHT);
        label.setText(labelText);

        final GridData dataGridInput = new GridData();
        dataGridInput.grabExcessHorizontalSpace = true;
        dataGridInput.horizontalAlignment = GridData.FILL;
        this.comboBox = new Combo(this, SWT.READ_ONLY);
        this.comboBox.setLayoutData(dataGridInput);
        this.comboBox.setItems(items);
    }

    @Override
    public void apply() {

        final String toApply = this.comboBox.getItem(this.comboBox
                .getSelectionIndex());
        final Object applied = this.dataConverter.convert(toApply);
        this.valueBinder.setValue(applied);
    }

    @Override
    public void activate() {

        final Object value = this.valueBinder.getValue();
        String[] items = new String[0];
        if (value != null) {
            items = new ToStringArrayConverter().convert(value);
        }
        this.comboBox.setItems(items);
    }

    @Override
    public ComboBoxField setValueBinder(final ValueBinder valueBinder) {

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

}
