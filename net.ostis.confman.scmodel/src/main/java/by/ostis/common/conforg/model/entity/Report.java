package by.ostis.common.conforg.model.entity;

import java.util.List;
import java.util.UUID;

public class Report extends BaseEntity {

    public static class ReportBuilder {

        private String title;
        private Integer noOfPages;
        private Boolean accepted;
        private Boolean youngScientistReport;
        private UUID reporter;
        private List<UUID> authors;
        private UUID section;

        public ReportBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ReportBuilder setNoOfPages(Integer noOfPages) {
            this.noOfPages = noOfPages;
            return this;
        }

        public ReportBuilder setAccepted(Boolean accepted) {
            this.accepted = accepted;
            return this;
        }

        public ReportBuilder setYoungScientistReport(Boolean youngScientistReport) {
            this.youngScientistReport = youngScientistReport;
            return this;
        }

        public ReportBuilder setReporter(UUID reporter) {
            this.reporter = reporter;
            return this;
        }

        public ReportBuilder setAuthors(List<UUID> authors) {
            this.authors = authors;
            return this;
        }

        public ReportBuilder setSection(UUID section) {
            this.section = section;
            return this;
        }

        public Report build() {
            return new Report(title, noOfPages, accepted, youngScientistReport, reporter, authors, section);
        }
    }

    private String title;
    private Integer noOfPages;
    private Boolean accepted;
    private Boolean youngScientistReport;
    private UUID reporter;
    private List<UUID> authors;
    private UUID section;

    private Report(String title, Integer noOfPages, Boolean accepted, Boolean youngScientistReport, UUID reporter,
                  List<UUID> authors, UUID section) {
        super();
        this.title = title;
        this.noOfPages = noOfPages;
        this.accepted = accepted;
        this.youngScientistReport = youngScientistReport;
        this.reporter = reporter;
        this.authors = authors;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(Integer noOfPages) {
        this.noOfPages = noOfPages;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean isYoungScientistReport() {
        return youngScientistReport;
    }

    public void setYoungScientistReport(Boolean youngScientistReport) {
        this.youngScientistReport = youngScientistReport;
    }

    public UUID getReporter() {
        return reporter;
    }

    public void setReporter(UUID reporter) {
        this.reporter = reporter;
    }

    public List<UUID> getAuthors() {
        return authors;
    }

    public void setAuthors(List<UUID> authors) {
        this.authors = authors;
    }

    public UUID getSection() {
        return section;
    }

    public void setSection(UUID section) {
        this.section = section;
    }
}
