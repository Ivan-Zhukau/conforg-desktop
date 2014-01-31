package net.ostis.confman.model.schedule;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.services.common.model.FullModel;


public class Schedule {

    private List<TimeEntity> conferences;
    
    public Schedule() {
        
        conferences = new ArrayList<>();
    }

    public List<TimeEntity> getConferences(){
        
        return this.conferences;
    }

    public void setConferences(List<TimeEntity> conferences){
        
        this.conferences = conferences;
    }
    
    public void write(SpreadsheetTable table){
        
        for(TimeEntity entity: this.conferences){
            entity.write(table);
        }
    }
    
    public void setSkeleton(FullModel model){
        
        new SkeletonCreator().create(model, this);
    }
    
    public void setTimes(FullModel model){
        
        new TimesCreator().create(model, this);
    }
}
