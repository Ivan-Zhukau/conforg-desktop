package net.ostis.confman.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Section;

import org.apache.log4j.Logger;

public class SectionServiceImpl implements SectionService {

    protected static final Logger LOGGER = Logger.getLogger(ReportService.class);

    private ExcelBuilder          excelBuilder;

    private SpreadsheetTable      table;

    private FullModel             model;

    private List<Section>         sectionsList;

    public SectionServiceImpl() {

        super();
    }

    @Override
    public void generateSectionReporList() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        this.table = new SpreadsheetTable();
        if (this.model.getSections() != null) {
            this.sectionsList = this.model.getSections();
        } else {
            LOGGER.error("Empty list of Sections");
        }
        this.table.addRow(createHeaders());
        saveTheTable();
    }

    private SpreadsheetRow createHeaders() {

        final SpreadsheetRow headers = new SpreadsheetRow();
        headers.addCell(new SpreadsheetCell("Количество страниц"));
        headers.addCell(new SpreadsheetCell("Порядок выступления в программе"));
        headers.addCell(new SpreadsheetCell("Фамилия"));
        headers.addCell(new SpreadsheetCell("Имя"));
        headers.addCell(new SpreadsheetCell("Отчество"));
        headers.addCell(new SpreadsheetCell("Email"));
        headers.addCell(new SpreadsheetCell("Название доклада"));
        headers.addCell(new SpreadsheetCell("Учёная степень,звание"));
        headers.addCell(new SpreadsheetCell("Организация"));
        headers.addCell(new SpreadsheetCell("Должность"));
        headers.addCell(new SpreadsheetCell("Форма участия"));
        headers.addCell(new SpreadsheetCell("Адрес"));
        return headers;
    }

    private void saveTheTable() {

        FileOutputStream excelFile;
        try {
            excelFile = new FileOutputStream("SectionReports.xlsx");
            this.excelBuilder.generate(excelFile, this.table);
            excelFile.close();
        } catch (final FileNotFoundException exception) {
            LOGGER.error(exception);
        } catch (final IOException exception) {
            LOGGER.error(exception);
        }
    }

}
