package net.ostis.confman.ui.participant;

import java.util.List;

import net.ostis.confman.ui.participant.TableComponent.Listener;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableComponent extends AbstractTableModel {

    public interface Listener {
        void onRowSelected();
    }

    private TableViewer viewer;

    private Composite   composite;

    TableComponent(final Composite parent, final int style) {

        final GridLayout layout = new GridLayout(2, false);
        parent.setLayout(layout);
        createViewer(parent, style);
        this.composite = parent;

    }

    private void createViewer(final Composite parent, final int style) {

        this.viewer = new TableViewer(parent, style);
        final Table table = this.viewer.getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.addListener(SWT.MouseDown,
                new RowSelectionListener(table, viewer));
        final GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        this.viewer.getControl().setLayoutData(gridData);
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {

                ISelection selection = viewer.getSelection();
            }
        });
    }

    public void addColumn(final String columnName) {

        final int width = 120;
        final TableColumn column = new TableColumn(this.viewer.getTable(),
                SWT.NONE);
        column.setText(columnName);
        column.setWidth(width);
    }

    public void addColumn(final String[] columnNames) {

        final Rectangle area = this.composite.getClientArea();
        final int width = 120;
        for (int columnIndex = 0; columnIndex < columnNames.length; columnIndex++) {
            final TableColumn column = new TableColumn(this.viewer.getTable(),
                    SWT.NONE);
            column.setText(columnNames[this.viewer.getTable().getColumnCount() - 1]);
            column.setWidth(width);
        }
    }

    public void addRow(final List<? extends String[]> rowData) {

        for (int index = 0; index < rowData.size(); index++) {
            final TableItem tableItem = new TableItem(this.viewer.getTable(),
                    SWT.NONE);
            tableItem.setText(rowData.get(index));
        }
    }

    public TableItem[] getRow(final int rowIndex) {

        return this.viewer.getTable().getItems();
    }

    @Override
    public int getColumnCount() {

        return this.viewer.getTable().getColumnCount();
    }

    @Override
    public int getRowCount() {

        return this.viewer.getTable().getItemCount();
    }

    @Override
    public String getColumnName(final int columnIndex) {

        final TableColumn[] columns = this.viewer.getTable().getColumns();
        return columns[columnIndex].getText();
    }

    @Override
    public Object getValue(final int row, final int column) {

        return new Object();
    }

    @Override
    void setValueAt(final String Value, final int row, final int column) {

    }

    public void addListener(Listener listener) {

        
    }
}
