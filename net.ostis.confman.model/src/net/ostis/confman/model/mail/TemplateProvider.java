package net.ostis.confman.model.mail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.ostis.confman.model.mail.entity.Template;
import net.ostis.confman.model.mail.entity.Templates;

public class TemplateProvider {

    private static final String MAIL_TEMPLATE_TYPES = "resourses/mail-templates.xml";

    public TemplateProvider() {

        super();
    }

    public List<Template> getMailTemplates() {

        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Templates.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final Templates templates = (Templates) unmarshaller
                    .unmarshal(new FileReader(MAIL_TEMPLATE_TYPES));
            return templates.getTemplates();
        } catch (JAXBException | FileNotFoundException e) {
            throw new IllegalArgumentException(
                    "Mail templates config file isn't exists.");
        }
    }
}
