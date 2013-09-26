package net.ostis.confman.model.common.spreadsheet;

import java.util.ArrayList;
import java.util.List;

public class SpreadsheetTable {

    private List<SpreadsheetRow> rows;

    public SpreadsheetTable() {

        super();
        this.rows = new ArrayList<SpreadsheetRow>();
    }

    public void addRow(final SpreadsheetRow row) {

        this.rows.add(row);
    }

    public List<SpreadsheetRow> getRows() {

        return this.rows;
    }

}