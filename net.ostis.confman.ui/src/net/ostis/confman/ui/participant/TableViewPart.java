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
import org.eclipse.swt.widgets.Composite;

public class TableViewPart {

    @Inject
    private ESelectionService            selectionService;

    private org.eclipse.swt.widgets.List confUiList;

    private List<String[]>               tableData          = new ArrayList<String[]>();

    private String[]                     rowData;

    private ParticipantServiceImpl       participantService = new ParticipantServiceImpl();

    private List<Participant>            participants;

    @PostConstruct
    public void createComposite(final Composite parent) {

        final TableComponent tableComponent = new TableComponent(parent,
                SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);

        initTableData();
        tableComponent.addColumn(getTableTitles());
        tableComponent.addRow(this.tableData);
        tableComponent.addListener(new TableComponent.Listener(){

            @Override
            public void onRowSelected() {

                
            }
            
        });

    }

    private String[] getTableTitles() {

        final String[] titles = { "PersonName", "ParticipantRole",
                "ConfStartDate" };
        return titles;
    }

    private void initTableData() {

        this.participants = this.participantService.getParticipants();
        for (int index = 0; index < this.participants.size(); index++) {
            final String[] data = { getPersonName(index),
                    getParticipantRole(index), getConferenceStartDate(index) };
            this.tableData.add(data);
        }
    }

    private String getPersonName(final int index) {

        this.participants = this.participantService.getParticipants();
        final String name = new String(this.participants.get(index).getPerson()
                .getSurname()
                + " "
                + this.participants.get(index).getPerson().getFirstName()
                + " "
                + this.participants.get(index).getPerson().getPatronymic());
        return name;
    }

    private String getParticipantRole(final int index) {

        this.participants = this.participantService.getParticipants();
        final String participantRole = this.participants.get(index).getRole()
                .getParticipationForm();
        return participantRole;
    }

    private String getConferenceStartDate(final int index) {

        this.participants = this.participantService.getParticipants();
        final Date startDate = this.participants.get(index).getConference()
                .getStartDate();
        return startDate.toString();
    }

}
