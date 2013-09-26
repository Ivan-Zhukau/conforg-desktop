package net.ostis.confman.model.common.spreadsheet;

public class SpreadsheetCell {

    private final Object value;

    public SpreadsheetCell(final Object value) {

        super();
        this.value = value;
    }

    public Object getValue() {

        return this.value;
    }
}