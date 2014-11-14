package net.ostis.confman.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.ostis.confman.model.common.spreadsheet.SpreadsheetCell;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetRow;
import net.ostis.confman.model.common.spreadsheet.SpreadsheetTable;
import net.ostis.confman.model.excel.ExcelBuilder;
import net.ostis.confman.model.mail.MailSettingsProvider;
import net.ostis.confman.model.mail.TemplateProvider;
import net.ostis.confman.model.mail.entity.MailDto;
import net.ostis.confman.model.mail.entity.Template;
import net.ostis.confman.services.common.model.EmailedParticipant;
import net.ostis.confman.services.common.model.Person;

import org.apache.log4j.Logger;

class EmailServiceImpl implements EmailService {

    private static final String   MAIL_TEMPLATES_FOLDER = "templates/";

    protected static final Logger LOGGER                = Logger.getLogger(EmailService.class);

    private ExcelBuilder          excelBuilder;

    private SpreadsheetTable      table;

    @Override
    public MailDto getMailInfo() {

        final MailSettingsProvider settingsProvider = new MailSettingsProvider();
        return settingsProvider.getMailSettings();
    }

    @Override
    public List<Template> getTemplates() {

        final TemplateProvider templateProvider = new TemplateProvider();
        return templateProvider.getMailTemplates();
    }

    @Override
    public String getTemplateBody(final String fileName) {

        final StringBuilder templatePath = new StringBuilder(
                MAIL_TEMPLATES_FOLDER);
        templatePath.append(fileName);
        final StringBuffer templateBody = new StringBuffer();
        try {
            final File file = new File(templatePath.toString());
            final FileReader fileReader = new FileReader(file);
            final BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                templateBody.append(line);
                templateBody.append("\n");
            }
            fileReader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return templateBody.toString();
    }

    @Override
    public void generateExcelListOfEmailedParticipants(
            final OutputStream fileOutputStream,
            final List<EmailedParticipant> emailedParticipants) {

        this.table = new SpreadsheetTable();
        this.excelBuilder = new ExcelBuilder();

        final String[] headers = { Messages.getString("participant"),
                Messages.getString("category"), Messages.getString("email"),
                Messages.getString("emailBody") };

        createHeader(this.table, headers);

        createSortedMembersBody(emailedParticipants);

        try {
            this.excelBuilder.generate(fileOutputStream, this.table);
            fileOutputStream.close();
        } catch (final FileNotFoundException exception) {
            LOGGER.error(exception);
        } catch (final IOException exception) {
            LOGGER.error(exception);
        }

    }

    private void createHeader(final SpreadsheetTable table,
            final String[] headers) {

        final SpreadsheetRow headerRow = new SpreadsheetRow();

        for (final String title : headers) {
            headerRow.addCell(new SpreadsheetCell(title));
        }

        table.addRow(headerRow);
        table.addRow(new SpreadsheetRow());
    }

    private void createSortedMembersBody(
            final List<EmailedParticipant> emailedParticipants) {

        final ServiceLocator serviceLocator = ServiceLocator.getInstance();
        final SafeConversionService conversionService = (SafeConversionService) serviceLocator
                .getService(SafeConversionService.class);
        for (final EmailedParticipant emailedParticipant : emailedParticipants) {
            final SpreadsheetRow row = new SpreadsheetRow();
            final Person person = emailedParticipant.getParticipant()
                    .getPerson();
            final String category = emailedParticipant.getParticipant()
                    .getRole().getParticipationCategory();
            row.addCell(new SpreadsheetCell(person.getFullName()));
            row.addCell(new SpreadsheetCell(conversionService
                    .safeConverter(category)));
            row.addCell(new SpreadsheetCell(conversionService
                    .safeConverter(person.getContacts().geteMail())));
            row.addCell(new SpreadsheetCell(conversionService
                    .safeConverter(emailedParticipant.getTemplate().getBody())));
            this.table.addRow(row);
        }
    }
}
