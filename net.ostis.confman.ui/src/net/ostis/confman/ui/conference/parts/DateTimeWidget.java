package net.ostis.confman.ui.conference.parts;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.table.DynamicalTable;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;

public class DateTimeWidget extends Dialog {

    private enum LocaleStrings implements Localizable {
        DIALOG_TITLE("reportDialogTitle"),
        DIALOG_MESSAGE("reportDialogMessage"),
        OK_BUTTON_TEXT("reportDialogOKText"),
        CANCEL_BUTTON_TEXT("reportDialogCancelText"),
        TABLE_REPORT("reportTableReport"),
        TABLE_MAIN_AUTHOR("reportTableAuthor"),
        TABLE_SECTION("reportTableSection");

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

    private Composite      parent;

    public DateTimeWidget(final Shell parentShell) {

        super(parentShell);
    }

    @Override
    public void create() {

        super.create();
        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        /*
         * setTitle(localizationUtil.translate(LocaleStrings.DIALOG_TITLE));
         * setMessage(localizationUtil.translate(LocaleStrings.DIALOG_MESSAGE),
         * IMessageProvider.INFORMATION);
         */
    }

    @Override
    protected Control createDialogArea(final Composite parent) {

        final GridLayout layout = new GridLayout(1, false);

        final Composite area = (Composite) super.createDialogArea(parent);
        area.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        area.setLayout(layout);
        
        createCalendarViewer(area);
        return area;
    }

    private void createTableViewer(final Composite container) {

        final ConferenceService conferenceService = (ConferenceService) ServiceLocator
                .getInstance().getService(ConferenceService.class);

        this.table = new DynamicalTable(container);
        createColumns();
        this.table.getViewer().setContentProvider(
                ArrayContentProvider.getInstance());
        this.table.getViewer().setInput(conferenceService.getReports());
        /*
         * this.table.getViewer().addSelectionChangedListener( new
         * ISelectionChangedListener() {
         * 
         * @Override public void selectionChanged( final SelectionChangedEvent
         * event) {
         * 
         * final IStructuredSelection selection = (IStructuredSelection)
         * SelectReportDialog.this.table .getViewer().getSelection(); final
         * Object selectedElement = selection .getFirstElement(); if
         * (selectedElement instanceof Report) {
         * SelectReportDialog.this.selectedReport = (Report) selectedElement; }
         * } });
         */
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
                        final Person person = report.getMainAuthor()
                                .getPerson();
                        return person.getFirstName() + ' '
                                + person.getSurname();
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
    }

    @Override
    protected void createButtonsForButtonBar(final Composite parent) {

        super.createButtonsForButtonBar(parent);
        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        localizeOkButton(localizationUtil);
        localizeCancelButton(localizationUtil);
    }

    private void localizeCancelButton(final LocalizationUtil localizationUtil) {

        final Button cancel = getButton(IDialogConstants.CANCEL_ID);
        cancel.setText(localizationUtil
                .translate(LocaleStrings.CANCEL_BUTTON_TEXT));
        setButtonLayoutData(cancel);
    }

    private void localizeOkButton(final LocalizationUtil localizationUtil) {

        final Button ok = getButton(IDialogConstants.OK_ID);
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

    private void createCalendarViewer(final Composite parent) {

        final GridData dataGridInput = new GridData(SWT.FILL, SWT.FILL, true,
                false);

        final DateTime calendar = new DateTime(parent, SWT.CALENDAR);
        calendar.setLayoutData(dataGridInput);

        final DateTime time = new DateTime(parent, SWT.TIME);
        time.setLayoutData(dataGridInput);
    }
}
