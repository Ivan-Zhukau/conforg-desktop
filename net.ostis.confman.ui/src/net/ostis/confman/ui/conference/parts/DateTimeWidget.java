package net.ostis.confman.ui.conference.parts;

import java.util.Date;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.table.DynamicalTable;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;

public class DateTimeWidget extends Dialog {

    private enum LocaleStrings implements Localizable {
        OK_BUTTON_TEXT("reportDialogOKText"),
        CANCEL_BUTTON_TEXT("reportDialogCancelText");

        private String rk;

        private LocaleStrings(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private DateTime calendar;
    
    private DateTime time;
    
    private Date date;

    public DateTimeWidget(final Shell parentShell) {

        super(parentShell);
        
    }

    @Override
    public void create() {

        super.create();
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        
        date  = new Date();
        final Composite area = (Composite) super.createDialogArea(parent);        
        createCalendarViewer(area);
        return area;
    }

    private void createTableViewer(final Composite container) {

       /* final ConferenceService conferenceService = (ConferenceService) ServiceLocator
                .getInstance().getService(ConferenceService.class);

        this.table = new DynamicalTable(container);
        this.table.getViewer().setContentProvider(
                ArrayContentProvider.getInstance());
        this.table.getViewer().setInput(conferenceService.getReports());*/
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

    private void createCalendarViewer(final Composite parent) {
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        parent.setLayout(layout);
        
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.CENTER;
        gridData.grabExcessHorizontalSpace = true;
        
        GridData gridData1 = new GridData();
        gridData1.horizontalAlignment = GridData.CENTER;
        gridData1.grabExcessHorizontalSpace = true;
        
        calendar = new DateTime(parent, SWT.CALENDAR);
        calendar.setLayoutData(gridData);
        calendar.addSelectionListener(new SelectionListener() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
            
                changeDate();
                
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            
                changeDate();
                
            }
        });
        
        time = new DateTime(parent, SWT.TIME);
        time.setLayoutData(gridData1);
        time.addSelectionListener(new SelectionListener() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
            
                changeTime();
                
            }
            
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            
                changeTime();
                
            }
        });

    }

    public void setDate(Object value) {

        date = (Date) value;
        calendar.setDate(date.getYear(), date.getMonth(), date.getDay());
        time.setTime(date.getHours(), date.getMinutes(), date.getSeconds());
        
    }
    
    public Object getDate(){
        return date;
    }
    
    private void changeDate() {
        date.setYear(calendar.getYear());
        date.setMonth(calendar.getMonth());
        date.setDate(calendar.getDay());
    }
    
    private void changeTime() {
        date.setHours(time.getHours());
        date.setMinutes(time.getMinutes());
        date.setSeconds(time.getSeconds());
        
    }
    
}
