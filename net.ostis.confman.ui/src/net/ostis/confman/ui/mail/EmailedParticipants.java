package net.ostis.confman.ui.mail;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.mail.entity.Template;
import net.ostis.confman.services.common.model.Participant;

public class EmailedParticipants {

    private List<Participant> participants;

    private Template          template;

    public EmailedParticipants() {

        super();
        this.template = new Template();
        this.participants = new ArrayList<>();
    }

    public EmailedParticipants(final List<Participant> participants,
            final Template template) {

        super();
        this.participants = participants;
        this.template = template;
    }

    public List<Participant> getParticipants() {

        return this.participants;
    }

    public Template getTemplate() {

        return this.template;
    }

}
