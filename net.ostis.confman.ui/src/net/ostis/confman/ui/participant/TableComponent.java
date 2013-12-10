package net.ostis.confman.ui.participant;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableComponent extends AbstractTableModel {

    private Table          table;

    private List<String>   columnIdentifies;

    private List<String[]> rowData;

    TableComponent(final Composite composite, final int style) {

        this.table = new Table(composite, style);
        this.columnIdentifies = new ArrayList<String>();
        this.rowData = new ArrayList<String[]>();
        this.table.setLinesVisible(true);
        this.table.setHeaderVisible(true);
        final GridData layoutData = new GridData(GridData.FILL_BOTH);
        this.table.setLayoutData(layoutData);
        this.table.addListener(SWT.MouseDown, new RowSelectionListener(
                this.table));

    }
    
    public Table getTable() {
        return this.table;
    }

    public void addColumn(final String columnName) {

        this.columnIdentifies.add(columnName);
        final TableColumn column = new TableColumn(this.table, SWT.NONE);
        column.setText(this.columnIdentifies.get(this.table.getColumnCount() - 1));
        column.pack();
    }

    public void addColumn(final String[] columnNames) {

        for (int columnIndex = 0; columnIndex < columnNames.length; columnIndex++) {
            this.columnIdentifies.add(columnNames[columnIndex]);
            final TableColumn column = new TableColumn(this.table, SWT.NONE);
            column.setText(this.columnIdentifies.get(this.table
                    .getColumnCount() - 1));
            column.pack();
        }
    }

    public void addRow(final List<? extends String[]> rowData) {

        this.rowData.addAll(rowData);
        for (int index = 0; index < rowData.size(); index++) {
            final TableItem tableItem = new TableItem(this.table, SWT.NONE);
            tableItem.setText(this.rowData.get(index));
        }
    }
    
    public String[] getRow(int rowIndex) {
        return rowData.get(rowIndex);
    }

    @Override
    public int getColumnCount() {

        return this.columnIdentifies.size();
    }

    @Override
    public int getRowCount() {

        return this.rowData.size();
    }

    @Override
    public String getColumnName(final int columnIndex) {

        return this.columnIdentifies.get(columnIndex);
    }

    @Override
    public Object getValue(final int row, final int column) {

        return this.rowData.get(row - 1)[column - 1];
    }

    @Override
    void setValueAt(final String Value, final int row, final int column) {

        final String[] rowValue = this.rowData.get(row - 1);
        rowValue[column - 1] = Value.toString();
        this.rowData.set(row, rowValue);
    }
}
