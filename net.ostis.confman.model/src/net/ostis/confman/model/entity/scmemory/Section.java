package net.ostis.confman.model.entity.scmemory;

import java.util.Date;
import java.util.List;

public class Section extends BaseEntity {

    private String     title;

    private Date       date;

    private SystemAddress       conferenceId;

    private List<SystemAddress> reports;

    public Section() {

        super();
    }

    public Section(String title, Date date, SystemAddress conferenceId,
            List<SystemAddress> reports) {

        super();
        this.title = title;
        this.date = date;
        this.conferenceId = conferenceId;
        this.reports = reports;
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public Date getDate() {

        return this.date;
    }

    public void setDate(final Date date) {

        this.date = date;
    }

    public SystemAddress getConferenceId() {

        return this.conferenceId;
    }

    public void setConferenceId(final SystemAddress conferenceId) {

        this.conferenceId = conferenceId;
    }

    public List<SystemAddress> getReports() {

        return this.reports;
    }

    public void setReports(final List<SystemAddress> reports) {

        this.reports = reports;
    }
}
