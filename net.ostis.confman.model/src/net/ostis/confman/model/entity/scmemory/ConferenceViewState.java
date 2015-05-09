package net.ostis.confman.model.entity.scmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConferenceViewState extends BaseEntity {

    private List<UUID> openedConferences = new ArrayList<UUID>(0);

    public ConferenceViewState() {
        super();
    }

    public ConferenceViewState(List<UUID> openedConferences) {

        super();
        this.openedConferences = openedConferences;
    }



    public List<UUID> getOpenedConferences() {
    
        return openedConferences;
    }

    public void setOpenedConferences(List<UUID> openedConferences) {
    
        this.openedConferences = openedConferences;
    }

}
