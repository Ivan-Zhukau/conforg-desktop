package net.ostis.confman.ui.participant;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.services.ParticipantServiceImpl;
import net.ostis.confman.services.common.model.Participant;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class RowSelectionListener implements Listener {

    private Table                  table;

    private int                    rowIndex;

    private ParticipantServiceImpl participantService = new ParticipantServiceImpl();

    private TableViewer            viewer;

    RowSelectionListener(final Table table, TableViewer viewer) {

        this.table = table;
        this.viewer = viewer;
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

        List<Participant> participants = participantService.getParticipants();
        System.out.println(participants.get(rowIndex).getConference()
                .getStartDate().toString());
        viewer.setSelection(new StructuredSelection(participants));
    }
}
