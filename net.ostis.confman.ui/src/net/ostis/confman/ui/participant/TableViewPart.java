package net.ostis.confman.ui.participant;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ParticipantService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.ParticipantRole;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
import net.ostis.confman.ui.common.component.table.SortComparator;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class TableViewPart {

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
    private ESelectionService  selectionService;

    private ParticipantService participantService;

    private DynamicalTable     table;

    private SortComparator     comparator;

    public TableViewPart() {

        super();
        this.participantService = (ParticipantService) ServiceLocator
                .getInstance().getService(ParticipantService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        parent.setLayout(new FillLayout());
        this.table = new DynamicalTable(parent, Boolean.FALSE, SWT.SINGLE);
        this.comparator = createComparator();
        this.table.setComparator(this.comparator);
        createColumns();
        addTableEventSupport();
    }

    private SortComparator createComparator() {

        return new SortComparator() {

            @Override
            public int compareAll(final int columnNumber, final Object e1,
                    final Object e2) {

                final Participant p1 = (Participant) e1;
                final Participant p2 = (Participant) e2;
                int rc = 0;
                switch (columnNumber) {
                    case 0: {
                        if (p1.getPerson() != null && p2.getPerson() != null
                                && p1.getPerson().getFullName() != null
                                && p2.getPerson().getFullName() != null) {
                            rc = p1.getPerson().getFullName()
                                    .compareTo(p2.getPerson().getFullName());
                        } else {
                            rc = judge((p1.getPerson() == null || p1
                                    .getPerson().getFullName() == null),
                                    (p2.getPerson() == null || p2.getPerson()
                                            .getFullName() == null));
                        }
                        break;
                    }
                    case 1: {
                        if (p1.getConference() != null
                                && p2.getConference() != null
                                && p1.getConference().getTitle() != null
                                && p2.getConference().getTitle() != null) {
                            rc = p1.getConference().getTitle()
                                    .compareTo(p2.getConference().getTitle());
                        } else {
                            rc = judge(
                                    (p1.getConference() == null || p1
                                            .getConference().getTitle() == null),
                                    (p2.getConference() == null || p2
                                            .getConference().getTitle() == null));
                        }
                        break;
                    }
                    case 2: {
                        if (p1.getRole() != null && p2.getRole() != null
                                && p1.getRole().getParticipationForm() != null
                                && p2.getRole().getParticipationForm() != null) {
                            rc = p1.getRole()
                                    .getParticipationForm()
                                    .compareTo(
                                            p2.getRole().getParticipationForm());
                        } else {
                            rc = judge((p1.getRole() == null || p1.getRole()
                                    .getParticipationForm() == null),
                                    (p2.getRole() == null || p2.getRole()
                                            .getParticipationForm() == null));
                        }
                        break;
                    }
                    default:
                        rc = 0;
                }
                return rc;
            }
        };
    }

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();

        final int COLUMN_WIDTH = 150;

        this.table.createSortColumn(
                localizationUtil.translate(TableColumns.NAME), COLUMN_WIDTH,
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Participant participant = (Participant) element;
                        final Person person = participant.getPerson();
                        return person.getFirstName() + ' '
                                + person.getSurname();
                    }
                }, 0);
        this.table.createSortColumn(
                localizationUtil.translate(TableColumns.CONFERENCE),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Participant participant = (Participant) element;
                        final Conference conference = participant
                                .getConference();
                        return conference == null ? "" : conference.getTitle();
                    }
                }, 1);
        this.table.createSortColumn(
                localizationUtil.translate(TableColumns.PARTICIPATION_FORM),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Participant participant = (Participant) element;
                        final ParticipantRole role = participant.getRole();
                        return role == null ? "" : role.getParticipationForm();
                    }
                }, 2);

    }

    private void addTableEventSupport() {

        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(this.participantService.getParticipants());
        this.table.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                final IStructuredSelection selection = (IStructuredSelection) TableViewPart.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Participant) {
                    TableViewPart.this.selectionService
                            .setSelection(selectedElement);
                }
            }
        });
    }

    @Inject
    @Optional
    private void onConfDataUpdate(
            @UIEventTopic(ConferenceTopics.TABLE_UPDATE) final String s) {

        this.table.refresh();
    }

    private int judge(final boolean i, final boolean j) {

        if (i == true && j == false) {
            return -1;
        } else if (i == false && j == true) {
            return 1;
        } else {
            return 0;
        }
    }
}
