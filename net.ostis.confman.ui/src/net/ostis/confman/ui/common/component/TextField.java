package net.ostis.confman.ui.common.component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TextField extends Composite implements
        EditableComponent<TextField> {

    private Text          input;

    private DataConverter dataConverter;

    private ValueBinder   valueBinder;

    public TextField(final Composite parent, final String labelText) {

        super(parent, SWT.NONE);
        setLayout(new GridLayout(2, true));
        buildControl(labelText);
    }

    private void buildControl(final String labelText) {

        final Label label = new Label(this, SWT.RIGHT);
        label.setText(labelText);
        this.input = new Text(this, SWT.SINGLE);
    }

    @Override
    public TextField setValueBinder(final ValueBinder valueBinder) {

        this.valueBinder = valueBinder;
        return this;
    }

    @Override
    public TextField setDataConverter(final DataConverter converter) {

        this.dataConverter = converter;
        return this;
    }

    @Override
    public void apply() {

        final String toApply = this.input.getText();
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
        this.input.setText(text);
    }

}
