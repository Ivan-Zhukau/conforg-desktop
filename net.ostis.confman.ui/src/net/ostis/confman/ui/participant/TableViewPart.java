package net.ostis.confman.ui.participant;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class TableViewPart {

    @PostConstruct
    public void createComposite(final Composite parent) {

        final TableComponent table = new TableComponent(parent, SWT.BORDER
                | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);

        /*
         * final String[] titles = { "»Ãﬂ", "‘¿Ã»À»ﬂ", "Œ“◊≈—“¬Œ", "√–”œœ¿" };
         * 
         * table.addColumn(titles);
         * 
         * final List<String[]> test = new ArrayList<String[]>(); for (int i =
         * 1; i <= 40; i++) { final String[] data1 = { "x", "y", "z",
         * String.valueOf(i) }; test.add(data1); }
         * 
         * table.addRow(test);
         */
    }
}
