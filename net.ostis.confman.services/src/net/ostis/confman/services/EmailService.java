package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.model.mail.entity.MailDto;
import net.ostis.confman.model.mail.entity.Template;


public interface EmailService {
    
    MailDto getMailInfo();
    
    List<Template> getTemplates();
    
}
