package net.ostis.confman.services;

import java.io.OutputStream;
import java.util.Date;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.model.schedule.Schedule;
import net.ostis.confman.services.common.model.FullModel;

class ScheduleServiceImpl implements ScheduleService {

    private FullModel       model;
    
    public ScheduleServiceImpl() {
        
        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
    }

    @Override
    public void save(final OutputStream outputStream) {

        createSchedule(outputStream);
    }

    void createSchedule(final OutputStream outputStream) {
        
        final SpreadsheetTable table = new SpreadsheetTable();
        Schedule schedule = new Schedule();
        schedule.setSkeleton(model);
        schedule.setTimes(model);
        schedule.write(table);
        final ExcelBuilder builder = new ExcelBuilder();
        builder.generate(outputStream, table);

        /*final SpreadsheetTable table = new SpreadsheetTable();
        for (final net.ostis.confman.services.common.model.Conference conference : this.model
                .getConferences()) {
            whriteConference(conference, table);
        }

        final ExcelBuilder builder = new ExcelBuilder();
        builder.generate(outputStream, table);*/
    }

   /* private void whriteConference(
            final net.ostis.confman.services.common.model.Conference conference,
            final SpreadsheetTable table) {

        final SpreadsheetCell nameCell = new SpreadsheetCell(
                conference.getTitle());
        final SpreadsheetRow row = new SpreadsheetRow();
        row.addCell(nameCell);

        table.addRow(this.emptyRow).addRow(row).addRow(this.emptyRow);

        for (final Section section : this.model.getSections()) {
            if (section.getConference() != null
                    && section.getConference().equals(conference)) {
                whriteSection(section, table);
            }
        }
    }

    private void whriteSection(final Section section,
            final SpreadsheetTable table) {

        final SpreadsheetCell nameCell = new SpreadsheetCell(section.getTitle());
        final SpreadsheetCell dateCell = new SpreadsheetCell(section.getDate()
                .toString());
        final SpreadsheetRow row = new SpreadsheetRow();
        row.addCell(nameCell).addCell(dateCell);

        final SpreadsheetCell leaderCell = new SpreadsheetCell(
                "Слово председателя:");
        final SpreadsheetRow leaderRow = new SpreadsheetRow();
        leaderRow.addCell(leaderCell).addCell(this.emptyCell);

        table.addRow(this.emptyRow).addRow(row).addRow(this.emptyRow)
                .addRow(leaderRow).addRow(this.emptyRow);

        for (final Report report : this.model.getReports()) {
            if (report.getSection() != null
                    && report.getSection().equals(section)) {
                whriteReport(report, table, new Date());
            }
        }
    }

    private void whriteReport(final Report report,
            final SpreadsheetTable table, final Date date) {

        final SpreadsheetCell nameCell = new SpreadsheetCell(report.getTitle());
        SpreadsheetCell speakerCell;
        if (report.getMainAuthor() != null) {
            speakerCell = new SpreadsheetCell(report.getMainAuthor()
                    .getPerson().getFullName());
        } else {
            speakerCell = new SpreadsheetCell("");
        }
        final SpreadsheetCell timeCell = new SpreadsheetCell(date.toString());

        final SpreadsheetRow row = new SpreadsheetRow();
        row.addCell(nameCell).addCell(speakerCell).addCell(timeCell);

        table.addRow(row);
    }*/
}
