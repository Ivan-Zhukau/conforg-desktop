package net.ostis.confman.test.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.excel.ExcelBuilder;

import org.junit.Test;

public class ExcelGenerationTest {

    @Test
    public void testExcelGeneration() throws FileNotFoundException {

        final SpreadsheetTable tableModel = new SpreadsheetTable();

        final SpreadsheetRow row1 = new SpreadsheetRow();
        final SpreadsheetRow row2 = new SpreadsheetRow();
        final SpreadsheetRow row3 = new SpreadsheetRow();
        final SpreadsheetRow row4 = new SpreadsheetRow();

        tableModel.addRow(row1);
        tableModel.addRow(row2);
        tableModel.addRow(row3);
        tableModel.addRow(row3);
        tableModel.addRow(row3);
        tableModel.addRow(row2);
        tableModel.addRow(row4);

        final SpreadsheetCell cell1 = new SpreadsheetCell("1");
        final SpreadsheetCell cell2 = new SpreadsheetCell("abcdef");
        final SpreadsheetCell cell3 = new SpreadsheetCell("qwerty");
        final SpreadsheetCell cell4 = new SpreadsheetCell("");
        final SpreadsheetCell cell5 = new SpreadsheetCell("not empty");

        row1.addCell(cell1);
        row1.addCell(cell1);
        row1.addCell(cell1);

        row2.addCell(cell4);

        row3.addCell(cell2);
        row3.addCell(cell3);
        row3.addCell(cell5);

        row4.addCell(cell4);
        row4.addCell(cell5);

        final ExcelBuilder builder = new ExcelBuilder();
        final OutputStream stream = new FileOutputStream("table.xlsx");
        builder.generate(stream, tableModel);
    }

}
