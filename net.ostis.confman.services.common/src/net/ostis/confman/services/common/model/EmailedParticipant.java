package net.ostis.confman.services.common.model;

public class EmailedParticipant {
    
    private Participant participant;
    
    private Template template;
    
    public EmailedParticipant() {
        
        super();
    }

    public Participant getParticipant() {

        return participant;
    }

    public void setParticipant(Participant participant) {

        this.participant = participant;
    }

    public Template getTemplate() {

        return template;
    }

    public void setTemplate(Template template) {

        this.template = template;
    }
    
    

}
