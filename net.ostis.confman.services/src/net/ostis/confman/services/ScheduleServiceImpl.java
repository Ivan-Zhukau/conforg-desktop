package net.ostis.confman.services;

import java.io.OutputStream;
import java.util.Date;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.model.entity.Conference;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;


public class ScheduleServiceImpl implements ScheduleService {

    private FullModel model;
    private Date currentDate;
    private SpreadsheetCell emptyCell;
    private SpreadsheetRow emptyRow;
    
    public ScheduleServiceImpl(){
        
        emptyCell = new SpreadsheetCell("");
        emptyRow = new SpreadsheetRow();
        emptyRow.addCell(emptyCell);
        
        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
    }

    @Override
    public void save(OutputStream outputStream) {

        createSchedule(outputStream);        
    }
    
    void createSchedule(OutputStream outputStream){
        
        SpreadsheetTable table = new SpreadsheetTable();
        for(net.ostis.confman.services.common.model.Conference conference : model.getConferences()){
            whriteConference(conference, table);
        }
        
        ExcelBuilder builder = new ExcelBuilder();        
        builder.generate(outputStream, table);
    }

    private void whriteConference(
            net.ostis.confman.services.common.model.Conference conference,
            SpreadsheetTable table) {  

        SpreadsheetCell nameCell = new SpreadsheetCell(conference.getTitle());
        SpreadsheetRow row = new SpreadsheetRow();
        row.addCell(nameCell);        

        table.addRow(emptyRow).addRow(row).addRow(emptyRow);
        
        for(Section section : model.getSections()){
            if(section.getConference()!=null && section.getConference().equals(conference)){
                whriteSection(section, table);                             
            }
        }       
    }

    private void whriteSection(Section section, SpreadsheetTable table) {

        SpreadsheetCell nameCell = new SpreadsheetCell(section.getTitle());
        SpreadsheetCell dateCell = new SpreadsheetCell(section.getDate().toString());
        SpreadsheetRow row = new SpreadsheetRow();
        row.addCell(nameCell).addCell(dateCell);
        
        SpreadsheetCell leaderCell = new SpreadsheetCell("Слово председателя:");
        SpreadsheetRow leaderRow = new SpreadsheetRow();
        leaderRow.addCell(leaderCell).addCell(emptyCell);
        
        table.addRow(emptyRow).addRow(row).addRow(emptyRow).addRow(leaderRow).addRow(emptyRow);
        
        for(Report report : model.getReports()){
            if(report.getSection()!=null && report.getSection().equals(section)){
                whriteReport(report, table, new Date());   
            }            
        }
    }

    private void whriteReport(Report report, SpreadsheetTable table, Date date) {

        SpreadsheetCell nameCell = new SpreadsheetCell(report.getTitle());
        SpreadsheetCell speakerCell;
        if(report.getMainAuthor()!=null){            
            speakerCell = new SpreadsheetCell(report.getMainAuthor().getPerson().getFullName());            
        }
        else{
            speakerCell = new SpreadsheetCell("");
        }
        SpreadsheetCell timeCell = new SpreadsheetCell(date.toString());
        
        SpreadsheetRow row = new SpreadsheetRow();
        row.addCell(nameCell).addCell(speakerCell).addCell(timeCell);
        
        table.addRow(row);
    }
}