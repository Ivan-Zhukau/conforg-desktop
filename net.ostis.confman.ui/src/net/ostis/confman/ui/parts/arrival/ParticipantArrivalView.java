package net.ostis.confman.ui.parts.arrival;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ParticipantService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.ComboBoxField;
import net.ostis.confman.ui.common.component.EditableComponent;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class ParticipantArrivalView {
    
    private enum TableColumns implements Localizable {
            
        NAME("participantTableAuthorName"),
        CONFERENCE("participantTableConference"),
        PARTICIPATION_FORM("participantTableParticipationRole");
        
        private String rk;
        
        private TableColumns(final String rk) {
        
            this.rk = rk;
        }
        
        @Override
        public String getResourceKey() {
        
            return this.rk;
        }
    }

    @Inject
    private ESelectionService selectionService;

    @Inject
    private EPartService      partService;

    @Inject
    private IEventBroker      eventBroker;
    
    private ParticipantService participantService;
    private ConferenceService conferenceService;

    private EditableComponent<ComboBoxField> confSelector;
    private DynamicalTable table;

    public ParticipantArrivalView() {
        super();
        participantService = (ParticipantService) ServiceLocator.getInstance()
                .getService(ParticipantService.class);
        conferenceService= (ConferenceService) ServiceLocator.getInstance()
                .getService(ConferenceService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        GridLayout layout = new GridLayout();
        parent.setLayout(layout);
        LocalizationUtil localizationUtil = LocalizationUtil.getInstance();
        List<Conference> conferences = conferenceService.getConferences();
        String[] confNames = new String[conferences.size()];
        int currentConfNo = 0;
        for (Conference conf : conferences) {
            confNames[currentConfNo++] = conf.getTitle();
        }
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.BEGINNING;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        confSelector = new ComboBoxField(parent, 
                localizationUtil.translate(TableColumns.CONFERENCE), confNames, gridData);
        this.table = new DynamicalTable(parent, Boolean.TRUE, SWT.SINGLE);
        createColumns();
        addTableEventSupport();
    }

    private void createColumns() {

        
    }

    private void addTableEventSupport() {

        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(this.participantService.getParticipants());
        this.table.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                final IStructuredSelection selection = 
                        (IStructuredSelection) ParticipantArrivalView.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Participant) {
                    ParticipantArrivalView.this.selectionService
                            .setSelection(selectedElement);
                }
            }
        });
    }

}
