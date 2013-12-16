package net.ostis.confman.ui.common.component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class CheckBoxField extends Composite implements
        EditableComponent<CheckBoxField> {

    private Button        checkBox;

    private DataConverter dataConverter;

    private ValueBinder   valueBinder;

    public CheckBoxField(final Composite parent, final String buttonText) {

        super(parent, SWT.NONE);
        setLayout(new FillLayout());
        buildControl(buttonText);
    }

    private void buildControl(final String buttonText) {

        this.checkBox = new Button(this, SWT.CHECK);
        this.checkBox.setText(buttonText);
    }

    @Override
    public void apply() {

        final Boolean toApply = this.checkBox.getSelection();
        this.valueBinder.setValue(toApply);
    }

    @Override
    public void activate() {

        final Object value = this.valueBinder.getValue();
        this.checkBox.setSelection((Boolean) value);
    }

    @Override
    public CheckBoxField setValueBinder(final ValueBinder valueBinder) {

        this.valueBinder = valueBinder;
        return this;
    }

    @Override
    public CheckBoxField setDataConverter(final DataConverter converter) {

        this.dataConverter = converter;
        return this;
    }

    @Override
    public CheckBoxField setValueComboBinder(ValueComboBinder valueBinder) {

        // TODO Auto-generated method stub
        return null;
    }

}
