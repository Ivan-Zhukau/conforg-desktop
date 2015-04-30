package by.ostis.common.conforg.model.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Section extends BaseEntity {

    private String title;
    private Date startDate;
    private UUID conference;
    private List<UUID> reports;

    public Section() {
        super();
    }

    public Section(String title, Date startDate, UUID conference, List<UUID> reports) {
        super();
        this.title = title;
        this.startDate = startDate;
        this.conference = conference;
        this.reports = reports;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public UUID getConference() {
        return conference;
    }

    public void setConference(UUID conference) {
        this.conference = conference;
    }

    public List<UUID> getReports() {
        return reports;
    }

    public void setReports(List<UUID> reports) {
        this.reports = reports;
    }
}
