package net.ostis.confman.ui.common.component.table;

import java.util.List;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class DynamicalTable {

    public static final int COLUMN_WIDTH = 230;

    private TableViewer     tableViewer;

    private Pagination      pagination;

    private PagingElement   pagingElement;

    private boolean         showPaging;

    private SortComparator  comparator;

    public DynamicalTable(final Composite parent, final boolean showPaging,
            final int selectionBehaviour) {

        super();
        this.showPaging = showPaging;
        createComposite(parent, selectionBehaviour);
    }

    private void createComposite(final Composite parent,
            final int selectionBehaviour) {

        final Composite composite = createWrapper(parent);
        final GridData layoutData = createTableLayoutData();
        this.tableViewer = new TableViewer(composite, selectionBehaviour
                | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

        final Table table = this.tableViewer.getTable();
        this.tableViewer.getControl().setLayoutData(layoutData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        createPaging(composite);
    }

    private GridData createTableLayoutData() {

        final GridData layoutData = new GridData();
        layoutData.verticalAlignment = GridData.FILL;
        layoutData.grabExcessVerticalSpace = true;
        layoutData.horizontalAlignment = GridData.FILL;
        layoutData.grabExcessHorizontalSpace = true;
        return layoutData;
    }

    private Composite createWrapper(final Composite parent) {

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, true));
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL,
                Boolean.TRUE, Boolean.TRUE);
        composite.setLayoutData(gridData);
        return composite;
    }

    private void createPaging(final Composite parent) {

        if (this.showPaging) {
            this.pagingElement = new PagingElement(parent, this);
        }
    }

    public void createColumn(final String text,
            final CellLabelProvider cellLabelProvider) {

        createColumn(text, COLUMN_WIDTH, cellLabelProvider);
    }

    public TableViewerColumn createColumn(final String text,
            final int columnWidth, final CellLabelProvider cellLabelProvider) {

        final TableViewerColumn col = new TableViewerColumn(this.tableViewer,
                SWT.NONE);
        col.getColumn().setWidth(columnWidth);
        col.getColumn().setText(text);
        col.setLabelProvider(cellLabelProvider);
        return col;
    }

    public void createSortColumn(final String text, final int columnWidth,
            final CellLabelProvider cellLabelProvider, final int colNumber) {

        final TableViewerColumn col = createColumn(text, COLUMN_WIDTH,
                cellLabelProvider);
        col.getColumn().addSelectionListener(
                getSelectionAdapter(col.getColumn(), colNumber));
    }

    public TableViewer getViewer() {

        return this.tableViewer;
    }

    public void setContentProvider(final IContentProvider provider) {

        this.tableViewer.setContentProvider(provider);
    }

    public void setInput(final Object input) {

        if (input instanceof List<?>) {
            if (!((List<?>) input).isEmpty()) {
                if (showPaging) {
                    this.pagination = new Pagination((List<?>) input);
                    this.tableViewer.setInput(this.pagination.getFirstPage());
                    this.pagingElement.setPagination(this.pagination);
                } else {
                    this.tableViewer.setInput(input);                    
                }
            }
            
        }
    }

    public void addSelectionChangedListener(
            final ISelectionChangedListener listener) {

        this.tableViewer.addSelectionChangedListener(listener);
    }

    public void updateInput(final List<?> input) {

        this.tableViewer.setInput(input);
    }

    public void refresh() {

        this.tableViewer.setInput(this.pagination.getPage());
        this.tableViewer.refresh();
    }

    public void setComparator(final SortComparator comparator) {

        this.comparator = comparator;
        this.tableViewer.setComparator(comparator);
    }

    private SelectionAdapter getSelectionAdapter(final TableColumn column,
            final int index) {

        final SelectionAdapter selectionAdapter = new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                DynamicalTable.this.comparator.setColumn(index);
                final int dir = DynamicalTable.this.comparator.getDirection();
                DynamicalTable.this.tableViewer.getTable()
                        .setSortDirection(dir);
                DynamicalTable.this.tableViewer.getTable()
                        .setSortColumn(column);
                DynamicalTable.this.tableViewer.refresh();
            }
        };
        return selectionAdapter;
    }

}
