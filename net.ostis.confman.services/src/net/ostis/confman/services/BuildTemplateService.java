package net.ostis.confman.services;

public interface BuildTemplateService {

    public String processTemplate(String templateBody,
            TemplateContextService contextService);
}
