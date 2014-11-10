package net.ostis.confman.ui.common.component.table;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public abstract class SortComparator extends ViewerComparator {

    private int              propertyIndex;

    private static final int DESCENDING = 1;

    private int              direction  = DESCENDING;

    public SortComparator() {

        this.propertyIndex = 0;
        this.direction = DESCENDING;
    }

    public int getDirection() {

        return this.direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(final int column) {

        if (column == this.propertyIndex) {
            // Same column as last sort; toggle the direction
            this.direction = 1 - this.direction;
        } else {
            // New column; do an ascending sort
            this.propertyIndex = column;
            this.direction = DESCENDING;
        }
    }

    public abstract int compareAll(int columnNumber, Object e1, Object e2);

    @Override
    public int compare(final Viewer viewer, final Object e1, final Object e2) {

        int rc = compareAll(this.propertyIndex, e1, e2);
        if (this.direction == DESCENDING) {
            rc = -rc;
        }
        return rc;
    }

}
