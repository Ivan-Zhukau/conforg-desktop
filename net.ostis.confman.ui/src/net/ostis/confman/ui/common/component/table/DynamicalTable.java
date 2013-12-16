package net.ostis.confman.ui.common.component.table;

import java.util.List;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class DynamicalTable {

    private static final int COLUMN_WIDTH = 230;

    private TableViewer      tableViewer;

    private Pagination       pagination;

    private PagingElement    pagingElement;

    private boolean          showPaging;

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

    public void setContentProvider(final IContentProvider provider) {

        this.tableViewer.setContentProvider(provider);
    }

    public void setInput(final Object input) {

        if (input instanceof List<?>) {
            this.pagination = new Pagination((List<?>) input);
            this.tableViewer.setInput(this.pagination.getFirstPage());
            this.pagingElement.setPagination(this.pagination);
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
}
