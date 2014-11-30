package net.ostis.confman.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import net.ostis.confman.model.mail.MailSettingsProvider;
import net.ostis.confman.model.mail.TemplateProvider;
import net.ostis.confman.model.mail.entity.MailDto;
import net.ostis.confman.model.mail.entity.Template;

class EmailServiceImpl implements EmailService {

    private static final String MAIL_TEMPLATES_FOLDER = "templates/";

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

}
