package net.ostis.confman.ui.conference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Conference;
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


public class SelectConferenceDialog extends TitleAreaDialog {

    private static final DateFormat DATE_FORMATTER;
    
    static {
        String pattern = LocalizationUtil.getInstance().translate(LocaleStrings.DATE_FORMAT);
        DATE_FORMATTER = new SimpleDateFormat(pattern);
    }

    private enum LocaleStrings implements Localizable {
        DIALOG_TITLE("confDialogTitle"),
        DIALOG_MESSAGE("confDialogMessage"),
        OK_BUTTON_TEXT("confDialogOKText"),
        CANCEL_BUTTON_TEXT("confDialogCancelText"),
        CONF_TITLE("confTitle"),
        CONF_START_DATE("confStartDate"),
        DATE_FORMAT("dateFormat");

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

    private Conference         selectedConference;

    public SelectConferenceDialog(final Shell parentShell) {

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

        this.table = new DynamicalTable(container, Boolean.FALSE, SWT.SINGLE);
        createColumns();
        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(conferenceService.getConferences());
        this.table.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                final IStructuredSelection selection = (IStructuredSelection) SelectConferenceDialog.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Conference) {
                    SelectConferenceDialog.this.selectedConference = (Conference) selectedElement;
                }
            }
        });
    }

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        this.table.createColumn(
                localizationUtil.translate(LocaleStrings.CONF_TITLE),
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Conference conf = (Conference) element;
                        return conf.getTitle();
                    }
                });
        this.table.createColumn(
                localizationUtil.translate(LocaleStrings.CONF_START_DATE),
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Conference conf = (Conference) element;
                        Date startDate = conf.getStartDate();
                        return startDate == null ? "" : DATE_FORMATTER.format(startDate);
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

    public Conference getSelectedConf() {

        return this.selectedConference;
    }
}
