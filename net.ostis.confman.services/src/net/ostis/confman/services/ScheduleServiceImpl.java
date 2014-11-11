package net.ostis.confman.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.model.schedule.Schedule;
import net.ostis.confman.services.common.model.FullModel;

import org.apache.log4j.Logger;

class ScheduleServiceImpl implements ScheduleService {

    private FullModel             model;

    protected static final Logger LOGGER = Logger.getLogger(ScheduleService.class);

    public ScheduleServiceImpl() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
    }

    @Override
    public void save(final OutputStream outputStream) {

        createSchedule(outputStream);
    }

    void createSchedule(final OutputStream outputStream) {

        try {
            final SpreadsheetTable table = new SpreadsheetTable();
            final Schedule schedule = new Schedule();
            schedule.setSkeleton(this.model);
            schedule.setTimes(this.model);
            schedule.write(table);
            final ExcelBuilder builder = new ExcelBuilder();
            builder.generate(outputStream, table);
            outputStream.close();

        } catch (final FileNotFoundException exception) {
            LOGGER.error(exception);
        } catch (final IOException exception) {
            LOGGER.error(exception);
        }
    }
}
