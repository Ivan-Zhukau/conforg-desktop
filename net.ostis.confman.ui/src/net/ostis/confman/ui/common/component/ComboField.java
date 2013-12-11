package net.ostis.confman.ui.common.component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ComboField extends Composite implements
        EditableComponent<ComboField> {

    private Combo          combo;

    private DataConverter dataConverter;

    private ValueBinder   valueBinder;

    public ComboField(final Composite parent, final String labelText) {

        super(parent, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setLayout(layout);
        buildControl(labelText);
    }

    private void buildControl(final String labelText) {

        final Label label = new Label(this, SWT.RIGHT);
        label.setText(labelText);
        
        GridData dataGridInput = new GridData();
        dataGridInput.grabExcessHorizontalSpace = true;
        dataGridInput.horizontalAlignment = GridData.FILL;
        this.combo = new Combo(this, SWT.BORDER);
        this.combo.setLayoutData(dataGridInput);
        combo.setItems(new String[] { "Test 1", "Test 2", "Test 3" });
    }

    @Override
    public ComboField setValueBinder(final ValueBinder valueBinder) {

        this.valueBinder = valueBinder;
        return this;
    }

    @Override
    public ComboField setDataConverter(final DataConverter converter) {

        this.dataConverter = converter;
        return this;
    }

    @Override
    public void apply() {

        final String toApply = this.combo.getText();
        final Object applied = this.dataConverter.convert(toApply);
        this.valueBinder.setValue(applied);
    }

    @Override
    public void activate() {

        final Object value = this.valueBinder.getValue();
        String text = "";
        if (value != null) {
            text = this.dataConverter.convert(value);
        }
        this.combo.setText(text);
    }

}
