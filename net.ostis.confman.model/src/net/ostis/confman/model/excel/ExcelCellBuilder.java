package net.ostis.confman.model.excel;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

class ExcelCellBuilder {

    public ExcelCellBuilder() {

        super();
    }

    public void buildCell(final Row excelRow,
            final SpreadsheetCell spreadsheetCell, final int cellPosition) {

        final Cell cell = excelRow.createCell(cellPosition);
        cell.setCellValue((String) spreadsheetCell.getValue());
    }

}