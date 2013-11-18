package net.ostis.confman.ui.participant;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class RowSelectionListener implements Listener {

    private Table table;

    private int   rowIndex;

    RowSelectionListener(final Table table) {

        this.table = table;
    }

    @Override
    public void handleEvent(final Event event) {

        final Point point = new Point(event.x, event.y);
        final TableItem item = this.table.getItem(point);
        if (item == null) {
            return;
        }
        for (int columnIndex = 0; columnIndex < this.table.getColumnCount(); columnIndex++) {
            final Rectangle rectangle = item.getBounds(columnIndex);
            if (rectangle.contains(point)) {
                final int index = this.table.indexOf(item);
                /*
                 * System.out.print("Item " + index + "-" + columnIndex);
                 * System.out.println(" or Row #" + index);
                 */
                this.rowIndex = index;

            }
        }

        final List<String> rowData = new ArrayList<String>();
        final TableItem[] tableItems = this.table.getItems();
        for (int index = 0; index < this.table.getColumnCount(); index++) {
            rowData.add(tableItems[this.rowIndex].getText(index));
            System.out.print(" " + rowData.get(index));
        }
        System.out.println();

    }
}
