package net.ostis.confman.model.entity.scmemory;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Section extends BaseEntity {

    private String     title;

    private Date       date;

    private UUID       conferenceId;

    private List<UUID> reports;

    public Section() {

        super();
    }

    public Section(String title, Date date, UUID conferenceId,
            List<UUID> reports) {

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

    public UUID getConferenceId() {

        return this.conferenceId;
    }

    public void setConferenceId(final UUID conferenceId) {

        this.conferenceId = conferenceId;
    }

    public List<UUID> getReports() {

        return this.reports;
    }

    public void setReports(final List<UUID> reports) {

        this.reports = reports;
    }
}
