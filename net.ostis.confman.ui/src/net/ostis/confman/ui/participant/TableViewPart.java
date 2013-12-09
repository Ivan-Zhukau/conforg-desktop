package net.ostis.confman.ui.participant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ParticipantServiceImpl;
import net.ostis.confman.services.common.model.Participant;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableViewPart {

@Inject
private ESelectionService            selectionService;

private org.eclipse.swt.widgets.List confUiList;

private List<String[]> tableData = new ArrayList<String[]>();

private String[] rowData;

private ParticipantServiceImpl participantService = new ParticipantServiceImpl();

private List<Participant> participants;

@PostConstruct
public void createComposite(final Composite parent) {
    
    final TableComponent tableComponent = new TableComponent(parent, SWT.BORDER
            | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
    
    initTableData();
    tableComponent.addColumn(getTableTitles());
    tableComponent.addRow(tableData);
    
    }

private String[] getTableTitles() {
    String []titles = {
            "PersonName",
            "ParticipantRole",
            "ConfStartDate"
    };
    return titles;
}


private void initTableData() {
    participants = participantService.getParticipants();
    for(int index = 0; index < participants.size(); index++) {
        String []data = {"vasya" + String.valueOf(index),
                getParticipantRole(index),
                getConferenceStartDate(index)};
        tableData.add(data);
    }
}

private String getPersonName(int index) {
    participants = participantService.getParticipants();
    String name = new String(participants.get(index).getPerson().getSurname() + " "
            + participants.get(index).getPerson().getFirstName() + " "
            + participants.get(index).getPerson().getPatronymic());
    return name;
}

private String getParticipantRole(int index) {
    participants = participantService.getParticipants();
    String participantRole = participants.get(index).getRole().getParticipationForm();
    return participantRole;    
}

private String getConferenceStartDate(int index) {
    participants = participantService.getParticipants();
    Date startDate = participants.get(index).getConference().getStartDate();
    return startDate.toString();
}



}








/*//
String []titles = {
        "ÈÌß",
        "ÔÀÌÈËÈß",
        "ÎÒ×ÅÑÒÂÎ",
        "ÃÐÓÏÏÀ"
};



tableComponent.addColumn(titles);

List<String[]> test = new ArrayList<String[]>();
for (int i = 1; i <= 40; i++){
    String []data1 = {"x", "y", "z", String.valueOf(i)};
    test.add(data1);
}

tableComponent.addRow(test);
//*/


  /*      
        this.confUiList = new org.eclipse.swt.widgets.List(parent, SWT.BORDER
        | SWT.MULTI | SWT.V_SCROLL);
                        
        this.confUiList.addSelectionListener(new SelectionListener() {

    private int index;

    @Override
    public void widgetSelected(final SelectionEvent event) {
        
                    
        Table table = tableComponent.getTable();
        
        final org.eclipse.swt.widgets.List source = (org.eclipse.swt.widgets.List) 
                event.getSource();
        
                final Point point = new Point(event.x, event.y);
                final TableItem item = table.getItem(point);
                if (item == null) {
                        return;
                }
                for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
                        final Rectangle rectangle = item.getBounds(columnIndex);
                        if (rectangle.contains(point)) {
                                int rowIndex = table.indexOf(item);
                                this.index = rowIndex;
                                
                                 * System.out.print("Item " + index + "-" + columnIndex);
                                 * System.out.println(" or Row #" + index);
                                 
                                

                        }
                }

                final List<String> rowData = new ArrayList<String>();
                final TableItem[] tableItems = table.getItems();
                for (int idx = 0; idx < table.getColumnCount(); idx++) {
                        rowData.add(tableItems[index].getText(idx));
                        System.out.print(" " + rowData.get(idx));
                }
                System.out.println();

                                
                if (rowData != null) {
                    TableViewPart.this.selectionService
                        .setSelection(rowData);
                    }
                }

    @Override
    public void widgetDefaultSelected(final SelectionEvent e) {

    }
});  */ 