package net.ostis.confman.ui.conference.parts;

import java.util.Date;

import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
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

    private Date     date;

    public DateTimeWidget(final Shell parentShell) {

        super(parentShell);

    }

    @Override
    public void create() {

        super.create();
    }

    @Override
    protected Control createDialogArea(final Composite parent) {

        this.date = new Date();
        final Composite area = (Composite) super.createDialogArea(parent);
        createCalendarViewer(area);
        return area;
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

        final GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        parent.setLayout(layout);

        final GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.CENTER;
        gridData.grabExcessHorizontalSpace = true;

        final GridData gridData1 = new GridData();
        gridData1.horizontalAlignment = GridData.CENTER;
        gridData1.grabExcessHorizontalSpace = true;

        this.calendar = new DateTime(parent, SWT.CALENDAR);
        this.calendar.setLayoutData(gridData);
        this.calendar.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                changeDate();

            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                changeDate();

            }
        });

        this.time = new DateTime(parent, SWT.TIME);
        this.time.setLayoutData(gridData1);
        this.time.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                changeTime();

            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                changeTime();

            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(final Object value) {

        if (value != null) {
            this.date = (Date) value;
            this.calendar.setDate(this.date.getYear(), this.date.getMonth(),
                    this.date.getDay());
            this.time.setTime(this.date.getHours(), this.date.getMinutes(),
                    this.date.getSeconds());
        }

    }

    public Object getDate() {

        return this.date;
    }

    @SuppressWarnings("deprecation")
    private void changeDate() {

        this.date.setYear(this.calendar.getYear() - 1900);
        this.date.setMonth(this.calendar.getMonth());
        this.date.setDate(this.calendar.getDay());
    }

    @SuppressWarnings("deprecation")
    private void changeTime() {

        this.date.setHours(this.time.getHours());
        this.date.setMinutes(this.time.getMinutes());
        this.date.setSeconds(this.time.getSeconds());

    }

}
