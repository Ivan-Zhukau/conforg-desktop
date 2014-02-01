package net.ostis.confman.model.schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;

public class TimeChairmanWord implements TimeEntity {

    private Date   start;

    private Date   end;

    private String name;

    public TimeChairmanWord() {

        init();
        this.name = "Слово председателя";
    }

    private void init() {

        this.start = new Date();
        this.end = new Date();
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

        // TODO Auto-generated method stub

    }

    @Override
    public List<TimeEntity> getSons() {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void write(final SpreadsheetTable table) {

        final DateFormat format = new SimpleDateFormat("HH:mm");
        final SpreadsheetRow row = new SpreadsheetRow();
        final SpreadsheetCell start = new SpreadsheetCell(
                format.format(getStart()));
        final SpreadsheetCell end = new SpreadsheetCell(format.format(getEnd()));
        final SpreadsheetCell name = new SpreadsheetCell(getName());
        row.addCell(start).addCell(end).addCell(name);
        table.addRow(row);
    }

}
