package net.ostis.confman.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.ostis.confman.model.common.report.PersonLexicographicComparator;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Person;

import org.apache.log4j.Logger;

public class ReportServiceImpl implements ReportService {

    protected static final Logger LOGGER = Logger.getLogger(ReportService.class);

    private ExcelBuilder          excelBuilder;

    private SpreadsheetTable      table;

    private FullModel             model;

    public ReportServiceImpl() {

        super();
    }

    @Override
    public void registrationReport(final File file) {

    }

    @Override
    public void generateSortedMemberList() {
        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        List<Person> sortedPersonsList = new ArrayList<>();
        ConferenceServiceImpl cs = new ConferenceServiceImpl();
        this.table = new SpreadsheetTable();
        this.excelBuilder = new ExcelBuilder();
        System.out.println(model.getPersons().get(0).getSurname());
        if (this.model.getPersons() != null) {
            sortedPersonsList = this.model.getPersons();
        } else {
            LOGGER.error("Empty list of Persons");
        }
        Collections
                .sort(sortedPersonsList, new PersonLexicographicComparator());
        for (final Person personList : sortedPersonsList) {
            final SpreadsheetRow row = new SpreadsheetRow();
            row.addCell(new SpreadsheetCell(personList.getSurname()));
            row.addCell(new SpreadsheetCell(personList.getFirstName()));
            row.addCell(new SpreadsheetCell(personList.getPatronymic()));
            this.table.addRow(row);
        }
        FileOutputStream excelFile;
        try {
            excelFile = new FileOutputStream("SortedMemberList.xlsx");
            this.excelBuilder.generate(excelFile, this.table);
            excelFile.close();
        } catch (final FileNotFoundException exception) {
            LOGGER.error(exception);
        } catch (final IOException exception) {
            LOGGER.error(exception);
        }
    }
}
