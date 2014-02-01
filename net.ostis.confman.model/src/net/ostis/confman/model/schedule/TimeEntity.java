package net.ostis.confman.model.schedule;

import java.util.Date;
import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;


public interface TimeEntity {

    public Date getStart();
    
    public void setSrart(Date date);
    
    public Date getEnd();
    
    public void setEnd(Date date);

    public String getName();
    
    public void setName(String name);
    
    public void setSons(List<TimeEntity> sons);
    
    public List<TimeEntity> getSons();
    
    public void write(SpreadsheetTable table);
}
