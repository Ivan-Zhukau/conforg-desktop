package net.ostis.confman.model.entity.scmemory;

import java.util.List;
import java.util.UUID;

public class Report extends BaseEntity {

    private String     title;

    private UUID       sectionId;

    private List<UUID> participants;

    private UUID       reporter;

    private Boolean    youngScientistReport;

    private Boolean    accepted;

    private Boolean    canceled;

    private Boolean    participationInContest;

    private Boolean    plenaryReport;

    private String     numberOfPages;

    public Report() {

        super();
    }

    public Report(String title, UUID sectionId, List<UUID> participants,
            UUID reporter, Boolean youngScientistReport, Boolean accepted,
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

    public UUID getSectionId() {

        return this.sectionId;
    }

    public void setSectionId(final UUID sectionId) {

        this.sectionId = sectionId;
    }

    public List<UUID> getParticipants() {

        return this.participants;
    }

    public void setParticipants(final List<UUID> participants) {

        this.participants = participants;
    }

    public UUID getReporter() {

        return this.reporter;
    }

    public void setReporter(final UUID reporter) {

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
