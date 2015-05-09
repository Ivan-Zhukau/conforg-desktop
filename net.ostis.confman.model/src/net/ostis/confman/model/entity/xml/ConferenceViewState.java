package net.ostis.confman.model.entity.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "conference-view-state")
public class ConferenceViewState {

    private List<Long> openedConferences = new ArrayList<Long>(0);

    public ConferenceViewState() {
        super();
    }

    @XmlElementWrapper(name = "opened-conferences")
    @XmlElement(name = "conference")
    public List<Long> getOpenedConferences() {
    
        return openedConferences;
    }

    public void setOpenedConferences(List<Long> openedConferences) {
    
        this.openedConferences = openedConferences;
    }

}
