package net.ostis.confman.model.mail.entity;

import net.ostis.confman.model.entity.Participant;

public class EmailedParticipant {

    private Participant participant;

    private Template    template;

    public EmailedParticipant() {

        super();
    }

    public Participant getParticipant() {

        return this.participant;
    }

    public void setParticipant(final Participant participant) {

        this.participant = participant;
    }

    public Template getTemplate() {

        return this.template;
    }

    public void setTemplate(final Template template) {

        this.template = template;
    }
}
