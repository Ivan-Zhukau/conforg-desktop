package net.ostis.confman.ui.table;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class DynamicalTable {

    private static final int COLUMN_WIDTH = 300;

    private TableViewer      tableViewer;

    public DynamicalTable(final Composite parent) {

        super();
        createComposite(parent);
    }

    private void createComposite(final Composite parent) {

        this.tableViewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL
                | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        final Table table = this.tableViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
    }

    public void createColumn(final String text,
            final CellLabelProvider cellLabelProvider) {

        createColumn(text, COLUMN_WIDTH, cellLabelProvider);
    }

    public void createColumn(final String text, final int columnWidth,
            final CellLabelProvider cellLabelProvider) {

        final TableViewerColumn col = new TableViewerColumn(this.tableViewer,
                SWT.NONE);
        col.getColumn().setWidth(columnWidth);
        col.getColumn().setText(text);
        col.setLabelProvider(cellLabelProvider);
    }

    public TableViewer getViewer() {

        return this.tableViewer;
    }
}
