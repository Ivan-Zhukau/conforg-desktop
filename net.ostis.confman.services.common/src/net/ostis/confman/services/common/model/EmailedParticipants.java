package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class EmailedParticipants {

    private List<EmailedParticipant> emailedParticipants;

    private Template                 template;
    
    private Conference conference;

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

    public Conference getConference() {

        return conference;
    }

    public void setConference(Conference conference) {

        this.conference = conference;
    }

    public Template getTemplate() {

        return template;
    }

    public void setTemplate(Template template) {

        this.template = template;
    }

}
