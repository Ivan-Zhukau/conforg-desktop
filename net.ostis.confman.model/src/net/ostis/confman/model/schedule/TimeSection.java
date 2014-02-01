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

    private Date start;
    
    private Date end;
    
    private String name;    
    
    
    public TimeSection() {
        
        init();
    }
    
    public TimeSection(String name) {
        
        init();
        setName(name);
    }
    
    private void init(){
        
        this.start = new Date();
        this.end = new Date();
        this.sons = new LinkedList<>();        
    }
    
    @Override
    public Date getStart() {
        
        return this.start;
    }

    @Override
    public void setSrart(Date date) {

        this.start = date;

    }

    @Override
    public Date getEnd() {

        return this.end;
    }

    @Override
    public void setEnd(Date date) {

        this.end = date;

    }

    @Override
    public String getName() {
        
        if(this.name == null){
            return "";
        }
        return this.name;
    }

    @Override
    public void setName(String name) {

        this.name = name;
        
    }

    @Override
    public void setSons(List<TimeEntity> sons) {

        this.sons = sons;

    }

    @Override
    public List<TimeEntity> getSons() {
        
        return this.sons;
    }

    @Override
    public void write(SpreadsheetTable table) {
        
        DateFormat format = new SimpleDateFormat("HH:mm");
        
        SpreadsheetRow emptyRow = new SpreadsheetRow();
        emptyRow.addCell(new SpreadsheetCell(""));
        
        SpreadsheetRow row = new SpreadsheetRow();
        SpreadsheetCell start = new SpreadsheetCell(format.format(getStart()));
        SpreadsheetCell end = new SpreadsheetCell(format.format(getEnd()));
        SpreadsheetCell name = new SpreadsheetCell(getName());
        row.addCell(start).addCell(end).addCell(name);         
        table.addRow(emptyRow).addRow(row).addRow(emptyRow);  
        
        for(TimeEntity entity: getSons()){
            entity.write(table);
        }
    }
}
