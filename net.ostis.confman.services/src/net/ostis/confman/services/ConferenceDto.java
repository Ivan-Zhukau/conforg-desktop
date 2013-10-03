package net.ostis.confman.services;

import java.util.Date;

public class ConferenceDto {

    private String title;

    private Date   startDate;

    private Date   endDate;

    public ConferenceDto() {

        super();
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

}
