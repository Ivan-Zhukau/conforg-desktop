package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private String            title;

    private Section           section;

    private List<Participant> allAuthors;

    private Participant       mainAuthor;

    private boolean           youngScientistReport;

    private boolean           reportCanceled;

    private boolean           reportAccepted;

    private boolean           plenaryReport;

    private boolean           participationInContest;

    private String            numberOfPages;
    
    private String contestParticipation;

    public Report() {

        super();
        this.section = new Section();
        this.allAuthors = new ArrayList<>();
        this.mainAuthor = new Participant();
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public Section getSection() {

        return this.section;
    }

    public void setSection(final Section section) {

        this.section = section;
    }

    public List<Participant> getAllAuthors() {

        return this.allAuthors;
    }

    public void setAllAuthors(final List<Participant> allAuthors) {

        this.allAuthors = allAuthors;
    }

    public Participant getMainAuthor() {

        return this.mainAuthor;
    }

    public void setMainAuthor(final Participant mainAuthor) {

        this.mainAuthor = mainAuthor;
    }

    public boolean isYoungScientistReport() {

        return this.youngScientistReport;
    }

    public void setYoungScientistReport(final boolean youngScientistReport) {

        this.youngScientistReport = youngScientistReport;
    }

    public String getNumberOfPages() {

        return this.numberOfPages;
    }

    public void setNumberOfPages(final String numberOfPages) {

        this.numberOfPages = numberOfPages;
    }

    public boolean isReportCanceled() {

        return this.reportCanceled;
    }

    public void setReportCanceled(final boolean cancelReport) {

        this.reportCanceled = cancelReport;
    }

    public boolean isReportAccepted() {

        return this.reportAccepted;
    }

    public void setReportAccepted(final boolean acceptReport) {

        this.reportAccepted = acceptReport;
    }

    public boolean isPlenaryReport() {

        return plenaryReport;
    }

    public void setPlenaryReport(boolean plenaryReport) {

        this.plenaryReport = plenaryReport;
    }

    public boolean getParticipationInContest() {

        return participationInContest;
    }

    public void setParticipationInContest(boolean participationInContest) {

        this.participationInContest = participationInContest;
    }

    public String getContestParticipation() {

        return contestParticipation;
    }

    public void setContestParticipation(String contestParticipation) {

        this.contestParticipation = contestParticipation;
    }
}
