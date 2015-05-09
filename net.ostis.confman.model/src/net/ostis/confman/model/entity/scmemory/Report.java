package net.ostis.confman.model.entity.scmemory;

import java.util.List;

public class Report extends BaseEntity {

    private String     title;

    private SystemAddress       sectionId;

    private List<SystemAddress> participants;

    private SystemAddress       reporter;

    private Boolean    youngScientistReport;

    private Boolean    accepted;

    private Boolean    canceled;

    private Boolean    participationInContest;

    private Boolean    plenaryReport;

    private String     numberOfPages;

    public Report() {

        super();
    }

    public Report(String title, SystemAddress sectionId, List<SystemAddress> participants,
            SystemAddress reporter, Boolean youngScientistReport, Boolean accepted,
            Boolean canceled, Boolean participationInContest,
            Boolean plenaryReport, String numberOfPages) {

        super();
        this.title = title;
        this.sectionId = sectionId;
        this.participants = participants;
        this.reporter = reporter;
        this.youngScientistReport = youngScientistReport;
        this.accepted = accepted;
        this.canceled = canceled;
        this.participationInContest = participationInContest;
        this.plenaryReport = plenaryReport;
        this.numberOfPages = numberOfPages;
    }
    
    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public SystemAddress getSectionId() {

        return this.sectionId;
    }

    public void setSectionId(final SystemAddress sectionId) {

        this.sectionId = sectionId;
    }

    public List<SystemAddress> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<SystemAddress> participants) {

        this.participants = participants;
    }

    public SystemAddress getReporter() {

        return this.reporter;
    }

    public void setReporter(final SystemAddress reporter) {

        this.reporter = reporter;
    }

    public Boolean getYoungScientistReport() {

        return this.youngScientistReport;
    }

    public void setYoungScientistReport(final Boolean youngScientistReport) {

        this.youngScientistReport = youngScientistReport;
    }

    public Boolean getAccepted() {

        return this.accepted;
    }

    public void setAccepted(final Boolean accepted) {

        this.accepted = accepted;
    }

    public Boolean getCanceled() {

        return this.canceled;
    }

    public void setCanceled(final Boolean canceled) {

        this.canceled = canceled;
    }

    public Boolean getParticipationInContest() {
    
        return participationInContest;
    }

    public void setParticipationInContest(Boolean participationInContest) {
    
        this.participationInContest = participationInContest;
    }

    public Boolean getPlenaryReport() {
    
        return plenaryReport;
    }

    public void setPlenaryReport(Boolean plenaryReport) {
    
        this.plenaryReport = plenaryReport;
    }

    public String getNumberOfPages() {

        return this.numberOfPages;
    }

    public void setNumberOfPages(final String numberOfPages) {

        this.numberOfPages = numberOfPages;
    }
}
