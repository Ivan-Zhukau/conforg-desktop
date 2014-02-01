package net.ostis.confman.services;

import java.io.OutputStream;

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
    }
}
