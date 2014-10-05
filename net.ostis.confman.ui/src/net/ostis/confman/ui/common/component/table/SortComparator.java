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
        direction = DESCENDING;
    }

    public int getDirection() {

        return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(int column) {

        if (column == this.propertyIndex) {
            // Same column as last sort; toggle the direction
            direction = 1 - direction;
        } else {
            // New column; do an ascending sort
            this.propertyIndex = column;
            direction = DESCENDING;
        }
    }

    public abstract int compareAll(int columnNumber, Object e1, Object e2);

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {

        int rc = compareAll(propertyIndex, e1, e2);
        if (direction == DESCENDING) {
            rc = -rc;
        }
        return rc;
    }

}
