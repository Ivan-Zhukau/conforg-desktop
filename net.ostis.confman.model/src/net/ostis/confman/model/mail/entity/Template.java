package net.ostis.confman.model.mail.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "email-template")
public class Template {

    private String name;

    private String language;

    public Template() {

        super();
    }

    public String getName() {

        return this.name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getLanguage() {

        return this.language;
    }

    public void setLanguage(final String language) {

        this.language = language;
    }

}
