package net.ostis.confman.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.ostis.confman.model.common.report.ParticipantByCategoryComparator;
import net.ostis.confman.model.common.report.PersonLexicographicComparator;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Participant;

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

        String[] headers = { Messages.getString("surname"),
                Messages.getString("first_name"),
                Messages.getString("patronymic") };

        createHeader(this.table, this.model, headers);

        createSortedMembersBody(sortedPersonsList);

        try {
            this.excelBuilder.generate(outputStream, this.table);
            outputStream.close();
        } catch (final FileNotFoundException exception) {
            LOGGER.error(exception);
        } catch (final IOException exception) {
            LOGGER.error(exception);
        }
    }

    private void createSortedMembersBody(List<Person> sortedPersonsList) {

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

    private void createHeader(SpreadsheetTable table2, FullModel model2,
            String[] headers) {

        SpreadsheetRow titleRow = new SpreadsheetRow();
        titleRow.addCell(new SpreadsheetCell(""));
        titleRow.addCell(new SpreadsheetCell(Messages.getString("conference")
                + ": "
                + model2.getConferences()
                        .get(model2.getConferences().size() - 1).getTitle())); // implement
                                                                               // method
                                                                               // of
                                                                               // getting
                                                                               // current
                                                                               // conference
                                                                               // name
        titleRow.addCell(new SpreadsheetCell(""));

        SpreadsheetRow headerRow = new SpreadsheetRow();

        for (String title : headers) {
            headerRow.addCell(new SpreadsheetCell(title));
        }

        table2.addRow(titleRow);
        table2.addRow(headerRow);
    }

    @Override
    public void generateParticipantsCategoryList(final OutputStream outputStream) {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        List<Participant> allParticipants = new ArrayList<>();
        this.table = new SpreadsheetTable();
        this.excelBuilder = new ExcelBuilder();
        if (this.model.getParticipants() != null) {
            allParticipants = this.model.getParticipants();
        } else {
            LOGGER.error("Empty list of Participants");
        }

        String[] headers = { Messages.getString("category"),
                Messages.getString("surname"),
                Messages.getString("first_name"),
                Messages.getString("patronymic") };

        createHeader(this.table, this.model, headers);

        createParticipantsCategoryBody(allParticipants);

        try {
            this.excelBuilder.generate(outputStream, this.table);
            outputStream.close();
        } catch (final FileNotFoundException exception) {
            LOGGER.error(exception);
        } catch (final IOException exception) {
            LOGGER.error(exception);
        }
    }

    private void createParticipantsCategoryBody(
            List<Participant> allParticipants) {

        List<Participant> withCategoryParticipants = new ArrayList<Participant>();
        List<Participant> withoutCategoryParticipants = new ArrayList<Participant>();

        for (Participant participant : allParticipants) {
            if (participant.getRole() != null
                    && participant.getRole().getParticipationCategory() != null) {
                withCategoryParticipants.add(participant);
            } else {
                withoutCategoryParticipants.add(participant);
            }
        }

        Collections.sort(withCategoryParticipants,
                new ParticipantByCategoryComparator());

        for (final Participant participant : withCategoryParticipants) {
            final SpreadsheetRow row = new SpreadsheetRow();

            row.addCell(new SpreadsheetCell(participant.getRole()
                    .getParticipationCategory()));
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getSurname()));
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getFirstName()));
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getPatronymic()));

            this.table.addRow(row);
        }

        for (final Participant participant : withoutCategoryParticipants) {
            final SpreadsheetRow row = new SpreadsheetRow();

            row.addCell(new SpreadsheetCell(""));
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getSurname()));
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getFirstName()));
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getPatronymic()));

            this.table.addRow(row);
        }

    }
}
