package net.ostis.confman.model.entity.scmemory;

import java.util.Date;
import java.util.List;

public class Conference extends BaseEntity {

    private String              title;

    private Date                startDate;

    private Date                endDate;

    private SystemAddress             residence;

    private List<SystemAddress> sections;

    private List<SystemAddress> reports;

    private List<SystemAddress> participants;

    public Conference() {

        super();
    }

    public Conference(String title, Date startDate, Date endDate,
            SystemAddress residence, List<SystemAddress> sections,
            List<SystemAddress> reports, List<SystemAddress> participants) {

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

    public List<SystemAddress> getSections() {

        return this.sections;
    }

    public void setSections(final List<SystemAddress> sections) {

        this.sections = sections;
    }

    public List<SystemAddress> getReports() {

        return this.reports;
    }

    public void setReports(final List<SystemAddress> reports) {

        this.reports = reports;
    }

    public List<SystemAddress> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<SystemAddress> participants) {

        this.participants = participants;
    }

    public SystemAddress getResidence() {

        return this.residence;
    }

    public void setResidence(final SystemAddress residence) {

        this.residence = residence;
    }
}
