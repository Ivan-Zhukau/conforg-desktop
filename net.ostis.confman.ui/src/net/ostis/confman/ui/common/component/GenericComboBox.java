package net.ostis.confman.ui.common.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


public abstract class GenericComboBox <T> extends Composite {

    private static final int LAYOUT_WIDTH = 2;
    
    private Combo            comboBox;

    private Map<String, T>   elementsMap = new HashMap<String, T>();

    public GenericComboBox(final Composite parent, final String labelText,
            final List<? extends T> items, GridData gridData) {

        super(parent, SWT.NONE);
        final GridLayout layout = new GridLayout(LAYOUT_WIDTH, false);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setLayout(layout);
        buildControl(labelText, items, gridData);
    }

    private void buildControl(final String labelText, final List<? extends T> items,
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
        String[] captions = convertCaptions(items);
        this.comboBox.setItems(captions);
        this.comboBox.addSelectionListener(new SelectionListener() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
            
                String itemName = comboBox.getText();
                T selectedItem = elementsMap.get(itemName);
                selectionChanged(selectedItem);
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            
                String itemName = comboBox.getText();
                T selectedItem = elementsMap.get(itemName);
                selectionChanged(selectedItem);
            }
        });
    }

    private String[] convertCaptions(List<? extends T> items) {

        String[] result = new String[items.size()];
        int counter = 0;
        String caption;
        for(T item : items) {
            caption = counter + 1 + ". " + getCaption(item);
            result[counter++] = caption;
            elementsMap.put(caption, item);
        }
        return result;
    }

    protected abstract String getCaption(T item);

    protected abstract void selectionChanged(T item);
}
