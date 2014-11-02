package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class Templates {

    private List<Template> templates;

    public Templates() {

        super();
        this.templates = new ArrayList<>();
    }

    public List<Template> getTemplates() {

        return this.templates;
    }

    public void setTemplates(final List<Template> templates) {

        this.templates = templates;
    }
    
    public void addTemplate(Template template){
        this.templates.add(template);
    }

}
