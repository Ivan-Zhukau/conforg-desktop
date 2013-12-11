package net.ostis.confman.ui.conference.parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DateTimeWidget {

    public DateTimeWidget() {

        showWidget();
    }

    private void showWidget() {

        // setup the SWT window
        final Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setLayout(new RowLayout());

        // initialize a parent composite with a grid layout manager
        final Composite parent = new Composite(shell, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        parent.setLayout(gridLayout);
        final DateTime calendar = new DateTime(parent, SWT.CALENDAR);
        final DateTime date = new DateTime(parent, SWT.DATE);
        final DateTime time = new DateTime(parent, SWT.TIME);
        // Date Selection as a drop-down
        final DateTime dateD = new DateTime(parent, SWT.DATE | SWT.DROP_DOWN);

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        // tear down the SWT window
        display.dispose();
    }
}
