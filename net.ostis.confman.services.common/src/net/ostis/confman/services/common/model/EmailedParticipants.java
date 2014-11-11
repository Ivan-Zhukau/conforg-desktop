package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class EmailedParticipants {

    private List<EmailedParticipant> emailedParticipants;

    private String                   templateName;

    public EmailedParticipants() {

        super();
        this.emailedParticipants = new ArrayList<>();
    }

    public List<EmailedParticipant> getEmailedParticipants() {

        return this.emailedParticipants;
    }

    public void setEmailedParticipants(
            final List<EmailedParticipant> emailedParticipants) {

        this.emailedParticipants = emailedParticipants;
    }

    public String getTemplateName() {

        return this.templateName;
    }

    public void setTemplateName(final String templateName) {

        this.templateName = templateName;
    }

}
