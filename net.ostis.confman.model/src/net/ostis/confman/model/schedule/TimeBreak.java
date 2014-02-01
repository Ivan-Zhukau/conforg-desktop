package net.ostis.confman.model.schedule;

import java.util.Date;
import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;

public class TimeBreak implements TimeEntity {

    private Date   start;

    private Date   end;

    private String name;

    public TimeBreak() {

        init();
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

        // TODO Auto-generated method stub

    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public void setName(final String name) {

        this.name = name;

    }

}
