package net.ostis.confman.model.mail.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "email-template")
public class Template {

    private String name;

    private String path;

    private String language;

    private String body;

    private String title;

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

    public String getBody() {

        return this.body;
    }

    public void setBody(final String body) {

        this.body = body;
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public String getPath() {

        return this.path;
    }

    public void setPath(final String path) {

        this.path = path;
    }

}
