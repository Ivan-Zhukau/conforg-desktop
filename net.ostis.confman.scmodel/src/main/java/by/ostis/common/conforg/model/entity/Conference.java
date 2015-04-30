package by.ostis.common.conforg.model.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/* (non-Javadoc)
 * Conference 
 */
public class Conference extends BaseEntity {

    public static class ConferenceBuilder {

        private String title;
        private Date startDate;
        private Date endDate;
        private UUID residence;
        private List<UUID> sections;
        private List<UUID> reports;
        private List<UUID> participants;

        public ConferenceBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ConferenceBuilder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public ConferenceBuilder setEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public ConferenceBuilder setResidence(UUID residence) {
            this.residence = residence;
            return this;
        }

        public ConferenceBuilder setSections(List<UUID> sections) {
            this.sections = sections;
            return this;
        }

        public ConferenceBuilder setReports(List<UUID> reports) {
            this.reports = reports;
            return this;
        }

        public ConferenceBuilder setParticipants(List<UUID> participants) {
            this.participants = participants;
            return this;
        }

        public Conference build() {
            return new Conference(title, startDate, endDate, residence, sections, reports, participants);
        }
    }

    private String title;
    private Date startDate;
    private Date endDate;
    private UUID residence;
    private List<UUID> sections;
    private List<UUID> reports;
    private List<UUID> participants;

    private Conference(String title, Date startDate, Date endDate, UUID residence, List<UUID> sections,
                      List<UUID> reports, List<UUID> participants) {
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public UUID getResidence() {
        return residence;
    }

    public void setResidence(UUID residence) {
        this.residence = residence;
    }

    public List<UUID> getSections() {
        return sections;
    }

    public void setSections(List<UUID> sections) {
        this.sections = sections;
    }

    public List<UUID> getReports() {
        return reports;
    }

    public void setReports(List<UUID> reports) {
        this.reports = reports;
    }

    public List<UUID> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UUID> participants) {
        this.participants = participants;
    }
}
