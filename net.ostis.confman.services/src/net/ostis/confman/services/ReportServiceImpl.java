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
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Report;

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

        final String[] headers = { Messages.getString("surname"),
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

    private void createSortedMembersBody(final List<Person> sortedPersonsList) {

        Collections
                .sort(sortedPersonsList, new PersonLexicographicComparator());
        for (final Person person : sortedPersonsList) {
            final SpreadsheetRow row = new SpreadsheetRow();
            row.addCell(new SpreadsheetCell(person.getSurname()));
            row.addCell(new SpreadsheetCell(person.getFirstName()));
            row.addCell(new SpreadsheetCell(person.getPatronymic()));
            this.table.addRow(row);
        }
    }

    private void createHeader(final SpreadsheetTable table2,
            final FullModel model2, final String[] headers) {

        final SpreadsheetRow titleRow = new SpreadsheetRow();
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

        final SpreadsheetRow headerRow = new SpreadsheetRow();

        for (final String title : headers) {
            headerRow.addCell(new SpreadsheetCell(title));
        }

        table2.addRow(titleRow);
        table2.addRow(headerRow);
    }

    @Override
    public void generateParticipantsCategoryList(final OutputStream outputStream, final Conference conference) {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        List<Participant> allParticipants = new ArrayList<>();
        this.table = new SpreadsheetTable();
        this.excelBuilder = new ExcelBuilder();
        allParticipants = conference.getParticipants();

        final String[] headers = { Messages.getString("category"),
                Messages.getString("surname"),
                Messages.getString("first_name"),
                Messages.getString("patronymic"),
                Messages.getString("article_title"),
                Messages.getString("email"), 
                Messages.getString("phone"), 
                Messages.getString("acadimic_degree"),
                Messages.getString("organization"),
                Messages.getString("position"),
                Messages.getString("participation_form"),
                Messages.getString("address"),
                Messages.getString("competition_participate") };

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
            final List<Participant> allParticipants) {

        final List<Participant> withCategoryParticipants = new ArrayList<Participant>();
        final List<Participant> withoutCategoryParticipants = new ArrayList<Participant>();

        for (final Participant participant : allParticipants) {
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
            addPartisipantsToTable(participant);
        }

        for (final Participant participant : withoutCategoryParticipants) {
            addPartisipantsToTable(participant);
        }

    }

    private void addPartisipantsToTable(final Participant participant) {

        final SpreadsheetRow row = new SpreadsheetRow();

        if (participant.getReports() != null
                && !participant.getReports().isEmpty()) {
            addReports(row, participant);
        } else {
            addRow(row, participant, null);
        }
        this.table.addRow(row);
    }

    private void addReports(final SpreadsheetRow row,
            final Participant participant) {

        for (final Report report : participant.getReports()) {
            addRow(row, participant, report);
        }
    }

    private void addRow(final SpreadsheetRow row,
            final Participant participant, final Report report) {

        if (participant.getRole() != null) {
            row.addCell(new SpreadsheetCell(participant.getRole()
                    .getParticipationCategory()));
        } else {
            row.addCell(new SpreadsheetCell(""));
        }

        if (participant.getPerson() != null) {
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getSurname()));
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getFirstName()));
            row.addCell(new SpreadsheetCell(participant.getPerson()
                    .getPatronymic()));
        } else {
            row.addCell(new SpreadsheetCell(""));
            row.addCell(new SpreadsheetCell(""));
            row.addCell(new SpreadsheetCell(""));
        }
        row.addCell(new SpreadsheetCell(report != null ? report.getTitle() : ""));
        row.addCell(new SpreadsheetCell(
                (participant.getPerson() != null && participant.getPerson()
                        .getContacts() != null) ? participant.getPerson()
                        .getContacts().geteMail() : ""));
        row.addCell(new SpreadsheetCell(
                (participant.getPerson() != null && participant.getPerson()
                        .getContacts() != null) ? participant.getPerson()
                        .getContacts().getContactPhoneNumber() : ""));
        row.addCell(new SpreadsheetCell((participant.getPerson() != null && participant.getPerson().getDegree() != null) ? participant.getPerson().getDegree().getDegree() : ""));
        row.addCell(new SpreadsheetCell(
                (participant.getPerson() != null && participant.getPerson()
                        .getWorkplace() != null) ? participant.getPerson()
                                .getWorkplace().getAffliation() : ""));
        row.addCell(new SpreadsheetCell((participant.getPerson() != null && participant.getPerson().getWorkplace() != null) ? participant.getPerson().getWorkplace().getPosition() : ""));
        row.addCell(new SpreadsheetCell(participant.getRole() != null ? participant.getRole().getParticipationForm() : ""));
        row.addCell(new SpreadsheetCell(participant.getPerson().getAddress().toString()));
        row.addCell(new SpreadsheetCell(getCompetitionParticipate(participant)));
        
    }

    private Object getCompetitionParticipate(Participant participant) {

        for(Report report : participant.getReports()) {
            if (report.getContestParticipation() != null) {
                return report.getContestParticipation();
            }
        }
        return "";
    }
}
