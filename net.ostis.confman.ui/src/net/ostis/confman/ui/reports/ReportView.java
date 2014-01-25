package net.ostis.confman.ui.reports;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
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

public class ReportView {

    private enum TableCaptions implements Localizable {
        TITLE("reportTableReportTitle"),
        MAIN_AUTHOR("reportTableMainAuthor"),
        AUTHORS("reportTableAuthors"),
        EMPTY("reportTableEmptyElement");

        private String rk;

        private TableCaptions(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    @Inject
    private ESelectionService selectionService;

    private ConferenceService conferenceService;

    private DynamicalTable    table;

    public ReportView() {

        super();
        this.conferenceService = (ConferenceService) ServiceLocator
                .getInstance().getService(ConferenceService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        parent.setLayout(new FillLayout());
        this.table = new DynamicalTable(parent, Boolean.TRUE, SWT.SINGLE);
        createColumns();
        addTableEventSupport();
    }

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final int COLUMN_WIDTH = 150;
        this.table.createColumn(
                localizationUtil.translate(TableCaptions.TITLE), COLUMN_WIDTH,
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Report report = (Report) element;
                        return report.getTitle() == null ? localizationUtil
                                .translate(TableCaptions.EMPTY) : report
                                .getTitle();
                    }
                });
        this.table.createColumn(
                localizationUtil.translate(TableCaptions.MAIN_AUTHOR),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Report report = (Report) element;
                        final Participant mainAuthor = report.getMainAuthor();
                        if (mainAuthor != null
                                && mainAuthor.getPerson() != null) {
                            final Person person = mainAuthor.getPerson();
                            return person.getSurname() == null ? localizationUtil
                                    .translate(TableCaptions.EMPTY) : person
                                    .getFullName();
                        } else {
                            return localizationUtil
                                    .translate(TableCaptions.EMPTY);
                        }
                    }
                });
        this.table.createColumn(
                localizationUtil.translate(TableCaptions.AUTHORS),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Report report = (Report) element;
                        return buildAuthors(report.getAllAuthors());
                    }

                    private String buildAuthors(final List<Participant> authors) {

                        final StringBuilder temp = new StringBuilder();
                        final Iterator<Participant> authorIterator = authors
                                .iterator();
                        while (authorIterator.hasNext()) {
                            final Participant participant = authorIterator
                                    .next();
                            temp.append(participant.getPerson().getFullName());
                            if (authorIterator.hasNext()) {
                                temp.append(", ");
                            }
                        }
                        return "".equals(temp.toString()) ? localizationUtil
                                .translate(TableCaptions.EMPTY) : temp
                                .toString();
                    }
                });
    }

    private void addTableEventSupport() {

        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(this.conferenceService.getReports());
        this.table.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                final IStructuredSelection selection = (IStructuredSelection) ReportView.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Report) {
                    ReportView.this.selectionService
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
}
