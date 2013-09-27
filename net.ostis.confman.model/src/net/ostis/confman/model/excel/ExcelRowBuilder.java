package net.ostis.confman.model.excel;

import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

class ExcelRowBuilder {

    private ExcelCellBuilder cellBuilder;

    public ExcelRowBuilder() {

        super();
        this.cellBuilder = new ExcelCellBuilder();
    }

    private void buildCells(final Row excelRow,
            final List<SpreadsheetCell> spreadsheetCells) {

        int cellPosition = 0;
        for (final SpreadsheetCell spreadsheetCell : spreadsheetCells) {
            this.cellBuilder.buildCell(excelRow, spreadsheetCell, cellPosition);
            cellPosition++;
        }
    }

    public void buildRow(final Sheet sheet,
            final SpreadsheetRow spreadsheetRow, final int rowPosition) {

        final Row row = sheet.createRow(rowPosition);
        buildCells(row, spreadsheetRow.getCells());
    }

}