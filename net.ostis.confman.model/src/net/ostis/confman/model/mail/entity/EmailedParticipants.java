package net.ostis.confman.model.mail.entity;

import java.util.List;

public class EmailedParticipants {

    private List<EmailedParticipant> emailedParticipants;

    public EmailedParticipants() {

        super();
    }

    public List<EmailedParticipant> getEmailedParticipants() {

        return this.emailedParticipants;
    }

    public void setEmailedParticipants(
            final List<EmailedParticipant> emailParticipants) {

        this.emailedParticipants = emailParticipants;
    }

}
