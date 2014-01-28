package net.ostis.confman.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.services.common.model.AcademicInformation;
import net.ostis.confman.services.common.model.Address;
import net.ostis.confman.services.common.model.ContactInformation;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.ParticipantRole;
import net.ostis.confman.services.common.model.Person;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.services.common.model.WorkplaceInformation;

import org.apache.log4j.Logger;

public class SectionServiceImpl implements SectionService {

    protected static final Logger LOGGER = Logger.getLogger(SectionService.class);

    private ExcelBuilder          excelBuilder;

    private List<SpreadsheetTable>      tables;

    private FullModel             model;

    private List<Section>         sectionsList;

    public SectionServiceImpl() {

        super();
    }

    @Override
    public void generateSectionReporList() {

        initTools();
        addSectionsInformation();
        saveTheTable();
    }

    private void initTools() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        this.tables = new ArrayList<>();
        this.excelBuilder = new ExcelBuilder();
        if (this.model.getSections() != null) {
            this.sectionsList = this.model.getSections();
        } else {
            LOGGER.error("Empty list of Sections");
        }
    }

    private void addSectionsInformation() {
        
        for(Section temp : sectionsList){
            int numberOfPerformances = 1;
            SpreadsheetTable tempTable = new SpreadsheetTable();
            SpreadsheetRow title = new SpreadsheetRow();
            title.addCell(new SpreadsheetCell(temp.getTitle()));
            tempTable.addRow(title);
            createHeaders(tempTable);
            this.tables.add(tempTable);
            tempTable.addRow(new SpreadsheetRow());
            List<Report> reports = temp.getReports();
            for(Report tempReport : reports){
                List<Participant> participants = tempReport.getAllAuthors();
                for(Participant tempParticipant : participants){
                    tempTable.addRow(addNewRow(tempParticipant,numberOfPerformances,tempReport));
                }
                numberOfPerformances++;
            }
            addRowAllPages(tempTable, reports);
        }
    }

    private void addRowAllPages(SpreadsheetTable tempTable, List<Report> reports) {

        SpreadsheetRow allPages = new SpreadsheetRow();
        allPages.addCell(new SpreadsheetCell("Общее число страниц"));
        allPages.addCell(new SpreadsheetCell(Integer.toString(calculateAllPages(reports))));
        tempTable.addRow(allPages);
    }

    private void createHeaders(SpreadsheetTable table) {

        final SpreadsheetRow headers = new SpreadsheetRow();
        headers.addCell(new SpreadsheetCell("Количество страниц"));
        headers.addCell(new SpreadsheetCell("Порядок выступления в программе"));
        headers.addCell(new SpreadsheetCell("Фамилия"));
        headers.addCell(new SpreadsheetCell("Имя"));
        headers.addCell(new SpreadsheetCell("Отчество"));
        headers.addCell(new SpreadsheetCell("Email"));
        headers.addCell(new SpreadsheetCell("Название доклада"));
        headers.addCell(new SpreadsheetCell("Учёная степень,звание"));
        headers.addCell(new SpreadsheetCell("Организация"));
        headers.addCell(new SpreadsheetCell("Должность"));
        headers.addCell(new SpreadsheetCell("Форма участия"));
        headers.addCell(new SpreadsheetCell("Адрес"));
        table.addRow(headers);
    }
    
    private SpreadsheetRow addNewRow(Participant tempParticipant, int numberOfPerformances, Report tempReport){
        
        final SpreadsheetRow report = new SpreadsheetRow();
        Person person = tempParticipant.getPerson();
        ContactInformation contactInformation = person.getContacts();
        WorkplaceInformation workplaceInformation = person.getWorkplace();
        AcademicInformation academicInformation = person.getDegree();
        Address address = person.getResidence();
        ParticipantRole participantRole = tempParticipant.getRole();
        report.addCell(new SpreadsheetCell(tempReport.getNumberOfPages()));
        report.addCell(new SpreadsheetCell(Integer.toString(numberOfPerformances)));
        report.addCell(new SpreadsheetCell(person.getSurname()));
        report.addCell(new SpreadsheetCell(person.getFirstName()));
        report.addCell(new SpreadsheetCell(person.getPatronymic()));
        report.addCell(new SpreadsheetCell(contactInformation.geteMail()));
        report.addCell(new SpreadsheetCell(tempReport.getTitle()));
        report.addCell(new SpreadsheetCell(academicInformation.getDegree() + "," + academicInformation.getTitle()));
        report.addCell(new SpreadsheetCell(workplaceInformation.getAffliation()));
        report.addCell(new SpreadsheetCell(workplaceInformation.getPosition()));
        report.addCell(new SpreadsheetCell(participantRole.getParticipationForm()));
        report.addCell(new SpreadsheetCell(address.getCountry() + "," + address.getCity()));
        return report;
    }
    
    private int calculateAllPages(List<Report> reports){
        
        int numberOfPages = 0;
        for (Report report : reports) {
            if(report.getNumberOfPages() != null){
                numberOfPages += Integer.parseInt(report.getNumberOfPages());
            }
        }
        return numberOfPages;
    }

    private void saveTheTable() {

        FileOutputStream excelFile;
        try {
            excelFile = new FileOutputStream("SectionReports.xlsx");
            this.excelBuilder.generate(excelFile, this.tables);
            excelFile.close();
        } catch (final FileNotFoundException exception) {
            LOGGER.error(exception);
        } catch (final IOException exception) {
            LOGGER.error(exception);
        }
    }

}
