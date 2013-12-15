package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Section {

    private String       title;

    private Date         date;

    private Conference   conference;

    private List<Report> reports;

    public Section() {

        super();
        this.reports = new ArrayList<>();
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

    public List<Report> getReports() {

        return this.reports;
    }

    public void setReports(final List<Report> reports) {

        this.reports = reports;
    }

    public Conference getConference() {

        return this.conference;
    }

    public void setConference(final Conference conference) {

        this.conference = conference;
    }
}
