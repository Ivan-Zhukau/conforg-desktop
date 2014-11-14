package net.ostis.confman.services;

import java.io.OutputStream;
import java.util.List;

import net.ostis.confman.model.mail.entity.MailDto;
import net.ostis.confman.model.mail.entity.Template;
import net.ostis.confman.services.common.model.EmailedParticipant;

public interface EmailService {

    MailDto getMailInfo();

    List<Template> getTemplates();

    String getTemplateBody(String fileName);

    void generateExcelListOfEmailedParticipants(OutputStream fileOutputStream,
            List<EmailedParticipant> emailedParticipants);

}
