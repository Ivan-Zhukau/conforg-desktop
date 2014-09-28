package net.ostis.confman.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.ostis.confman.model.common.report.PersonLexicographicComparator;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Person;

import org.apache.log4j.Logger;

class ReportServiceImpl implements ReportService {

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
    public void generateSortedMemberList(final OutputStream outputStream) {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        List<Person> sortedPersonsList = new ArrayList<>();
        this.table = new SpreadsheetTable();
        this.excelBuilder = new ExcelBuilder();
        if (this.model.getPersons() != null) {
            sortedPersonsList = this.model.getPersons();
        } else {
            LOGGER.error("Empty list of Persons");
        }

        createHeader(this.table, this.model);

        createBody(sortedPersonsList);

        try {
            this.excelBuilder.generate(outputStream, this.table);
            outputStream.close();
        } catch (final FileNotFoundException exception) {
            LOGGER.error(exception);
        } catch (final IOException exception) {
            LOGGER.error(exception);
        }
    }

    private void createBody(List<Person> sortedPersonsList) {

        Collections
                .sort(sortedPersonsList, new PersonLexicographicComparator());
        for (final Person personList : sortedPersonsList) {
            final SpreadsheetRow row = new SpreadsheetRow();
            row.addCell(new SpreadsheetCell(personList.getSurname()));
            row.addCell(new SpreadsheetCell(personList.getFirstName()));
            row.addCell(new SpreadsheetCell(personList.getPatronymic()));
            this.table.addRow(row);
        }
    }

    private void createHeader(SpreadsheetTable table2, FullModel model2) {

        SpreadsheetRow titleRow = new SpreadsheetRow();
        titleRow.addCell(new SpreadsheetCell(""));
        titleRow.addCell(new SpreadsheetCell(Messages.getString("conference")
                + ": "
                + model2.getConferences()
                        .get(model2.getConferences().size() - 1).getTitle()));
        titleRow.addCell(new SpreadsheetCell(""));

        SpreadsheetRow headerRow = new SpreadsheetRow();
        headerRow.addCell(new SpreadsheetCell(Messages.getString("surname")));
        headerRow
                .addCell(new SpreadsheetCell(Messages.getString("first_name")));
        headerRow
                .addCell(new SpreadsheetCell(Messages.getString("patronymic")));

        table2.addRow(titleRow);
        table2.addRow(headerRow);
    }
}
