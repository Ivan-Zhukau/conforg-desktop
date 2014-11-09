package net.ostis.confman.ui.conference;

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

public class OpenConferenceDialog extends TitleAreaDialog {

    private enum LocaleStrings implements Localizable {
        DIALOG_TITLE("openConfDialogTitle"),
        DIALOG_MESSAGE("openConfDialogMessage"),
        OK_BUTTON_TEXT("openConfDialogOKText"),
        CANCEL_BUTTON_TEXT("openConfDialogCancelText"),
        CONFERENCE_NAME("conferenceNameColumn")
        //TABLE_MAIN_AUTHOR("reportTableAuthor"),
        //TABLE_SECTION("reportTableSection"),
        //TABLE_CONFERENCE("reportTableConference");
        ;
        
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

    private Conference         openedConference;

    public OpenConferenceDialog(final Shell parentShell) {

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
        this.table.setInput(conferenceService.getClosedConferences());
        this.table.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                final IStructuredSelection selection = (IStructuredSelection) OpenConferenceDialog.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Conference) {
                    OpenConferenceDialog.this.openedConference = (Conference) selectedElement;
                }
            }
        });
    }

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        this.table.createColumn(
                localizationUtil.translate(LocaleStrings.CONFERENCE_NAME),
                DynamicalTable.COLUMN_WIDTH * 2,
                new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Conference conference = (Conference) element;
                        return conference.getTitle();
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

    public Conference getOpenedConference() {

        return this.openedConference;
    }
}
