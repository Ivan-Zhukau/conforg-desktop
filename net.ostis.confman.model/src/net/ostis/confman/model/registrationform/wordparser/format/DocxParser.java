package net.ostis.confman.model.registrationform.wordparser.format;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.registrationform.wordparser.RegistrationFormConstant;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class DocxParser {

    private List<String> infoList;

    public DocxParser() {

        this.infoList = new ArrayList<String>();
    }

    public List<String> parse(final FileInputStream inputStream) {

        try {
            final XWPFDocument document = new XWPFDocument(inputStream);
            final List<XWPFTable> tables = document.getTables();
            readTables(tables);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return this.infoList;
    }

    private void readTables(final List<XWPFTable> tables) {

        for (final XWPFTable table : tables) {
            for (final XWPFTableRow row : table.getRows()) {
                for (final XWPFTableCell cell : row.getTableCells()) {
                    if (row.getTableCells().indexOf(cell) == RegistrationFormConstant.INFORMATION_COLUNM
                            && table.getRows().indexOf(row) != 0) {
                        this.infoList.add(cell.getText());
                    }
                }
            }
        }
    }
}
