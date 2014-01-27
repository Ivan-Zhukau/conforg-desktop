package net.ostis.confman.test.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;


public class ExelTest {
    
    @Test
    public void testExcelGeneration() throws IOException { 
    Workbook wb = new HSSFWorkbook();
    Sheet sheet = wb.createSheet("new sheet");

    Row row = sheet.createRow((short) 1);
    Cell cell = row.createCell((short) 1);
    cell.setCellValue("This is a test of merging");

    sheet.addMergedRegion(new CellRangeAddress(
            1, //first row (0-based)
            1, //last row  (0-based)
            1, //first column (0-based)
            2  //last column  (0-based)
    ));

    // Write the output to a file
    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
    wb.write(fileOut);    
    try {
        fileOut.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
}
