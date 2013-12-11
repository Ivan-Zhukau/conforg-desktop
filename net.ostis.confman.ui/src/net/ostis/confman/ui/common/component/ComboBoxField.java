package net.ostis.confman.ui.common.component;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


public class ComboBoxField extends Composite implements EditableComponent<ComboBoxField>{
    
    private Combo comboBox;
    
    private DataConverter dataConverter;

    private ValueBinder   valueBinder;
    
    public ComboBoxField(Composite parent, String labelText, String [] items){
        
        super(parent, SWT.NONE);
        setLayout(new GridLayout(2, true));
        buildControl(labelText,items);
    }

    private void buildControl(final String labelText, String [] items) {

        final Label label = new Label(this, SWT.RIGHT);
        label.setText(labelText);
        this.comboBox = new Combo(this, SWT.READ_ONLY);
        comboBox.setItems(items);
    }

    @Override
    public void apply() {

        final String toApply = this.comboBox.getText();
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
        this.comboBox.setText(text);
    }

    @Override
    public ComboBoxField setValueBinder(ValueBinder valueBinder) {

        this.valueBinder = valueBinder;
        return this;
    }

    @Override
    public ComboBoxField setDataConverter(DataConverter converter) {

        this.dataConverter = converter;
        return this;
    }

}
