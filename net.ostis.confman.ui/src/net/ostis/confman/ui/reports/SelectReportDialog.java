package net.ostis.confman.ui.reports;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class SelectReportDialog extends TitleAreaDialog {

    private enum LocaleStrings implements Localizable {
        DIALOG_TITLE("reportDialogTitle"),
        DIALOG_MESSAGE("reportDialogMessage"),
        OK_BUTTON_TEXT("reportDialogOKText"),
        CANCEL_BUTTON_TEXT("reportDialogCancelText"),
        TABLE_REPORT("reportTableReport"),
        TABLE_MAIN_AUTHOR("reportTableAuthor"),
        TABLE_SECTION("reportTableSection"),
        TABLE_CONFERENCE("reportTableConference");

        private String rk;

        private LocaleStrings(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private DynamicalTable table;

    private Report         selectedReport;

    public SelectReportDialog(final Shell parentShell) {

        super(parentShell);
    }

    @Override
    public void create() {

        super.create();
        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        setTitle(localizationUtil.translate(LocaleStrings.DIALOG_TITLE));
        setMessage(localizationUtil.translate(LocaleStrings.DIALOG_MESSAGE),
                IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(final Composite parent) {

        final Composite area = (Composite) super.createDialogArea(parent);
        final Composite container = new Composite(area, SWT.NONE);
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL,
                Boolean.TRUE, Boolean.TRUE);
        container.setLayoutData(gridData);
        container.setLayout(new GridLayout());
        createTableViewer(container);
        return area;
    }

    private void createTableViewer(final Composite container) {

        final ConferenceService conferenceService = (ConferenceService) ServiceLocator
                .getInstance().getService(ConferenceService.class);

        this.table = new DynamicalTable(container, Boolean.TRUE, SWT.SINGLE);
        createColumns();
        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(getAcceptedReports(conferenceService.getReports()));
        this.table.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                final IStructuredSelection selection = (IStructuredSelection) SelectReportDialog.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Report) {
                    SelectReportDialog.this.selectedReport = (Report) selectedElement;
                }
            }
        });
    }

    private List<Report> getAcceptedReports(final List<Report> reports) {

        final List<Report> acceptedReports = new ArrayList<Report>();
        for (final Report report : reports) {
            if (report.isReportAccepted()) {
                acceptedReports.add(report);
            }
        }
        return acceptedReports;
    }

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        this.table.createColumn(
                localizationUtil.translate(LocaleStrings.TABLE_REPORT),
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Report report = (Report) element;
                        return report.getTitle();
                    }
                });
        this.table.createColumn(
                localizationUtil.translate(LocaleStrings.TABLE_MAIN_AUTHOR),
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Report report = (Report) element;
                        if (report.getMainAuthor() != null) {
                            final Person person = report.getMainAuthor()
                                    .getPerson();
                            return checkPersonFields(person) ? person
                                    .getFirstName() + ' ' + person.getSurname()
                                    : "";
                        }
                        return "";
                    }

                    private boolean checkPersonFields(final Person person) {

                        return person != null && person.getFirstName() != null
                                && person.getSurname() != null;
                    }
                });
        this.table.createColumn(
                localizationUtil.translate(LocaleStrings.TABLE_SECTION),
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Report report = (Report) element;
                        final Section section = report.getSection();
                        if (section != null) {
                            return section.getTitle();
                        }
                        return "";
                    }
                });
        this.table.createColumn(
                localizationUtil.translate(LocaleStrings.TABLE_CONFERENCE),
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Report report = (Report) element;
                        final Section section = report.getSection();
                        if (section != null) {
                            final Conference conf = section.getConference();
                            return conf == null ? "" : conf.getTitle();
                        }
                        return "";
                    }
                });
    }

    @Override
    protected void createButtonsForButtonBar(final Composite parent) {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        createOkButton(parent, localizationUtil);
        createCancelButton(parent, localizationUtil);
    }

    private void createCancelButton(final Composite parent,
            final LocalizationUtil localizationUtil) {

        final Button cancel = createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
        cancel.setText(localizationUtil
                .translate(LocaleStrings.CANCEL_BUTTON_TEXT));
        setButtonLayoutData(cancel);
    }

    private void createOkButton(final Composite parent,
            final LocalizationUtil localizationUtil) {

        final Button ok = createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, false);
        ok.setText(localizationUtil.translate(LocaleStrings.OK_BUTTON_TEXT));
        setButtonLayoutData(ok);
    }

    @Override
    protected boolean isResizable() {

        return true;
    }

    public Report getSelectedReport() {

        return this.selectedReport;
    }
}
