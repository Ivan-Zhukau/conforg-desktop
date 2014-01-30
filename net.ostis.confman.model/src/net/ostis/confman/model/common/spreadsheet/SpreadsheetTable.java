package net.ostis.confman.model.common.spreadsheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpreadsheetTable {

    private List<SpreadsheetRow> rows;

    public SpreadsheetTable() {

        super();
        this.rows = new ArrayList<SpreadsheetRow>();
    }

    public SpreadsheetTable addRow(final SpreadsheetRow row) {

        this.rows.add(row);
        return this;
    }

    public List<SpreadsheetRow> getRows() {

        return Collections.unmodifiableList(this.rows);
    }

}