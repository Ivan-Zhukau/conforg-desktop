package net.ostis.confman.model.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
public class Report {
    
    private String     title;
    
    private Long       sectionId;

    private List<Long> participants;
    
    private Long       reporter;
    
    
    public Report() {

        super();
    }


    @XmlElement
    public String getTitle() {
    
        return title;
    }


    
    public void setTitle(String title) {
    
        this.title = title;
    }


    @XmlElement(name = "section")
    public Long getSectionId() {
    
        return sectionId;
    }


    
    public void setSectionId(Long sectionId) {
    
        this.sectionId = sectionId;
    }


    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    public List<Long> getParticipants() {
    
        return participants;
    }


    
    public void setParticipants(List<Long> participants) {
    
        this.participants = participants;
    }


    
    public Long getReporter() {
    
        return reporter;
    }


    
    public void setReporter(Long reporter) {
    
        this.reporter = reporter;
    }
}
