package net.ostis.confman.model.mail.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "email-templates")
public class Templates {

    private List<Template> templates;

    public Templates() {

        super();
    }

    @XmlElement(name = "email-template")
    public List<Template> getTemplates() {

        return this.templates;
    }

    public void setTemplates(final List<Template> templates) {

        this.templates = templates;
    }

}
