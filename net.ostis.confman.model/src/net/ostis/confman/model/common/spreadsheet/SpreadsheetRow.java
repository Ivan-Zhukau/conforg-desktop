package net.ostis.confman.model.common.spreadsheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpreadsheetRow {

    private List<SpreadsheetCell> cells;

    public SpreadsheetRow() {

        super();
        this.cells = new ArrayList<SpreadsheetCell>();
    }

    public void addCell(final SpreadsheetCell cell) {

        this.cells.add(cell);
    }

    public List<SpreadsheetCell> getCells() {

        return Collections.unmodifiableList(this.cells);
    }
}