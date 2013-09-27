package net.ostis.confman.model.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.ListIterator;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBuilder {

    public static final Logger    GENERATOR_LOGGER = Logger.getLogger(ExcelBuilder.class);

    private final Workbook        workbook;

    private final ExcelRowBuilder rowBuilder;

    public ExcelBuilder() {

        super();
        this.rowBuilder = new ExcelRowBuilder();
        this.workbook = new XSSFWorkbook();
    }

    public void generate(final OutputStream outputStream,
            final SpreadsheetTable tableModel) {

        buildSheet(tableModel);
        writeDocumentToStream(outputStream);
    }

    private void buildSheet(final SpreadsheetTable tableModel) {

        final Sheet sheet = this.workbook.createSheet();
        buildRows(sheet, tableModel.getRows());
    }

    private void buildRows(final Sheet sheet,
            final List<SpreadsheetRow> spreadsheetRows) {

        final ListIterator<SpreadsheetRow> rowIterator = spreadsheetRows
                .listIterator();
        while (rowIterator.hasNext()) {
            this.rowBuilder.buildRow(sheet, rowIterator.next(),
                    rowIterator.previousIndex());
        }
    }

    private void writeDocumentToStream(final OutputStream outputStream) {

        try {
            this.workbook.write(outputStream);
        } catch (final IOException exception) {
            GENERATOR_LOGGER.error(exception);
        }
    }
}