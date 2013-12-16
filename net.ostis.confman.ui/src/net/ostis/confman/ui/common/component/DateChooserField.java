package net.ostis.confman.ui.common.component;

import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DateChooserField extends Composite implements
        EditableComponent<DateChooserField> {

    private enum Buttons implements Localizable {
        SELECT_DATE("selectDate");

        private String rk;

        private Buttons(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }

    }

    private Text           input;

    private DateTimeWidget dateTimeWidget;

    private DataConverter  dataConverter;

    private ValueBinder    valueBinder;

    public DateChooserField(final Composite parent, final String labelText) {

        super(parent, SWT.NONE);

        final GridLayout layout = new GridLayout(3, false);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setLayout(layout);
        buildControl(labelText, parent);
    }

    private void buildControl(final String labelText, final Composite parent) {

        final Label label = new Label(this, SWT.RIGHT);
        label.setText(labelText);

        final GridData dataGridInput = new GridData();
        dataGridInput.grabExcessHorizontalSpace = true;
        dataGridInput.horizontalAlignment = GridData.FILL;
        this.input = new Text(this, SWT.BORDER | SWT.READ_ONLY);
        this.input.setLayoutData(dataGridInput);

        final LocalizationUtil util = LocalizationUtil.getInstance();
        final Button button = new Button(this, SWT.PUSH | SWT.LEFT);
        button.setText(util.translate(Buttons.SELECT_DATE));

        button.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                showCalendar(parent);
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                showCalendar(parent);
            }
        });
    }

    @Override
    public DateChooserField setValueBinder(final ValueBinder valueBinder) {

        this.valueBinder = valueBinder;
        return this;
    }

    @Override
    public DateChooserField setDataConverter(final DataConverter converter) {

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

    private void showCalendar(final Composite parent) {

        if (this.valueBinder != null) {
            this.dateTimeWidget = new DateTimeWidget(parent.getShell());
            this.dateTimeWidget.create();
            this.dateTimeWidget.getShell().pack();
            this.dateTimeWidget.setDate(this.valueBinder.getValue());
            if (this.dateTimeWidget.open() == Window.OK) {
                this.valueBinder.setValue(this.dateTimeWidget.getDate());
                this.activate();
            }
        }
    }

    @Override
    public DateChooserField setValueComboBinder(ValueComboBinder valueBinder) {

        // TODO Auto-generated method stub
        return null;
    }
}
