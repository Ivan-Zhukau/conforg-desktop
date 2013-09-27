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

        SpreadsheetTable tableModel = new SpreadsheetTable();
        
        SpreadsheetRow row1 = new SpreadsheetRow();
        SpreadsheetRow row2 = new SpreadsheetRow();
        SpreadsheetRow row3 = new SpreadsheetRow();
        SpreadsheetRow row4 = new SpreadsheetRow();
        
        tableModel.addRow(row1);
        tableModel.addRow(row2);
        tableModel.addRow(row3);
        tableModel.addRow(row3);
        tableModel.addRow(row3);
        tableModel.addRow(row2);
        tableModel.addRow(row4);
        
        SpreadsheetCell cell1 = new SpreadsheetCell("1");
        SpreadsheetCell cell2 = new SpreadsheetCell("abcdef");
        SpreadsheetCell cell3 = new SpreadsheetCell("qwerty");
        SpreadsheetCell cell4 = new SpreadsheetCell("");
        SpreadsheetCell cell5 = new SpreadsheetCell("not empty");
        
        row1.addCell(cell1);
        row1.addCell(cell1);
        row1.addCell(cell1);
        
        row2.addCell(cell4);
        
        row3.addCell(cell2);
        row3.addCell(cell3);
        row3.addCell(cell5);
        
        row4.addCell(cell4);
        row4.addCell(cell5);
        
        
        ExcelBuilder builder = new ExcelBuilder();
        OutputStream stream = new FileOutputStream("table.xlsx");
        builder.generate(stream, tableModel);
    }

}
