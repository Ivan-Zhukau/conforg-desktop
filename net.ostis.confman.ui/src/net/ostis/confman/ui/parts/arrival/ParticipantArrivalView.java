package net.ostis.confman.ui.parts.arrival;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.ParticipantRole;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.GenericComboBox;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
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
        PARTICIPATION_FORM("participantTableParticipationRole"),
        NOT_AVAILABLE("notAvailable");
        
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

    private ConferenceService conferenceService;

    private DynamicalTable table;

    public ParticipantArrivalView() {

        super();
        conferenceService= (ConferenceService) ServiceLocator.getInstance()
                .getService(ConferenceService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        GridLayout layout = new GridLayout();
        parent.setLayout(layout);
        LocalizationUtil localizationUtil = LocalizationUtil.getInstance();
        List<Conference> conferences = conferenceService.getConferences();
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.BEGINNING;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        new GenericComboBox<Conference>(
                parent, localizationUtil.translate(TableColumns.CONFERENCE),
                conferences, gridData) {

                    @Override
                    protected String getCaption(Conference item) {

                        return item.getTitle();
                    }

                    @Override
                    protected void selectionChanged(Conference item) {

                        table.setInput(item.getParticipants());
                    }
        };
        this.table = new DynamicalTable(parent, Boolean.FALSE, SWT.SINGLE);
        createColumns();
        addTableEventSupport();
    }

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final int COLUMN_WIDTH = 200;
        this.table.createColumn(localizationUtil.translate(TableColumns.NAME),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Participant participant = (Participant) element;
                        return participant.getPerson().getFullName();
                    }
                });
        this.table.createColumn(localizationUtil.translate(TableColumns.CONFERENCE),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        LocalizationUtil localizationUtil = LocalizationUtil.getInstance();
                        final Participant participant = (Participant) element;
                        Conference conference = participant.getConference();
                        return conference == null
                                ? localizationUtil.translate(TableColumns.NOT_AVAILABLE)
                                : conference.getTitle();
                    }
                });
        this.table.createColumn(localizationUtil.translate(TableColumns.PARTICIPATION_FORM),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        LocalizationUtil localizationUtil = LocalizationUtil.getInstance();
                        final Participant participant = (Participant) element;
                        ParticipantRole role = participant.getRole();
                        return role == null
                                ? localizationUtil.translate(TableColumns.NOT_AVAILABLE)
                                : role.getParticipationForm();
                    }
                });
    }

    private void addTableEventSupport() {

        this.table.setContentProvider(ArrayContentProvider.getInstance());
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
