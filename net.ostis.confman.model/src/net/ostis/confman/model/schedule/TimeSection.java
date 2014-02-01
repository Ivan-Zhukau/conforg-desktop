package net.ostis.confman.model.schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;

public class TimeSection implements TimeEntity {

    private List<TimeEntity> sons;

    private Date             start;

    private Date             end;

    private String           name;

    public TimeSection() {

        init();
    }

    public TimeSection(final String name) {

        init();
        setName(name);
    }

    private void init() {

        this.start = new Date();
        this.end = new Date();
        this.sons = new LinkedList<>();
    }

    @Override
    public Date getStart() {

        return this.start;
    }

    @Override
    public void setSrart(final Date date) {

        this.start = date;

    }

    @Override
    public Date getEnd() {

        return this.end;
    }

    @Override
    public void setEnd(final Date date) {

        this.end = date;

    }

    @Override
    public String getName() {

        if (this.name == null) {
            return "";
        }
        return this.name;
    }

    @Override
    public void setName(final String name) {

        this.name = name;

    }

    @Override
    public void setSons(final List<TimeEntity> sons) {

        this.sons = sons;

    }

    @Override
    public List<TimeEntity> getSons() {

        return this.sons;
    }

    @Override
    public void write(final SpreadsheetTable table) {

        final DateFormat format = new SimpleDateFormat("HH:mm");

        final SpreadsheetRow emptyRow = new SpreadsheetRow();
        emptyRow.addCell(new SpreadsheetCell(""));

        final SpreadsheetRow row = new SpreadsheetRow();
        final SpreadsheetCell start = new SpreadsheetCell(
                format.format(getStart()));
        final SpreadsheetCell name = new SpreadsheetCell(getName());
        row.addCell(start).addCell(new SpreadsheetCell("")).addCell(name);
        table.addRow(emptyRow).addRow(row).addRow(emptyRow);

        for (final TimeEntity entity : getSons()) {
            entity.write(table);
        }
    }
}
