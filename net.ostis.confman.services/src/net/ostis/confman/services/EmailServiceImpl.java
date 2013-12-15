package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.model.mail.MailSettingsProvider;
import net.ostis.confman.model.mail.TemplateProvider;
import net.ostis.confman.model.mail.entity.MailDto;
import net.ostis.confman.model.mail.entity.Template;


public class EmailServiceImpl implements EmailService {

    @Override
    public MailDto getMailInfo() {

        MailSettingsProvider settingsProvider = new MailSettingsProvider();
        return settingsProvider.getMailSettings();
    }

    @Override
    public List<Template> getTemplates() {

        TemplateProvider templateProvider = new TemplateProvider();
        return templateProvider.getMailTemplates();
    }

}
