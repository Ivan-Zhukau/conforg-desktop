package net.ostis.confman.model.entity.scmemory;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Conference extends BaseEntity {

    private String     title;

    private Date       startDate;

    private Date       endDate;

    private Address    residence;

    private List<UUID> sections;

    private List<UUID> reports;

    private List<UUID> participants;

    public Conference() {

        super();
    }
    
    

    public Conference(String title, Date startDate, Date endDate,
            Address residence, List<UUID> sections, List<UUID> reports,
            List<UUID> participants) {

        super();
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.residence = residence;
        this.sections = sections;
        this.reports = reports;
        this.participants = participants;
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public Date getStartDate() {

        return this.startDate;
    }

    public void setStartDate(final Date startDate) {

        this.startDate = startDate;
    }

    public Date getEndDate() {

        return this.endDate;
    }

    public void setEndDate(final Date endDate) {

        this.endDate = endDate;
    }

    public List<UUID> getSections() {

        return this.sections;
    }

    public void setSections(final List<UUID> sections) {

        this.sections = sections;
    }

    public List<UUID> getReports() {

        return this.reports;
    }

    public void setReports(final List<UUID> reports) {

        this.reports = reports;
    }

    public List<UUID> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<UUID> participants) {

        this.participants = participants;
    }

    public Address getResidence() {

        return this.residence;
    }

    public void setResidence(final Address residence) {

        this.residence = residence;
    }
}
