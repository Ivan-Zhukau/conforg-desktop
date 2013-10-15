package net.ostis.confman.services.common.model;


public class ArticleInformation {
    private String titleEntry;

    private String participationForm;

    private String speaker;

    private String showLaunching;

    public ArticleInformation() {

        super();
    }

    public String getTitleEntry() {

        return this.titleEntry;
    }

    public String getParticipationForm() {

        return this.participationForm;
    }

    public String getShowLaunching() {

        return this.showLaunching;
    }

    public String getSpeaker() {

        return this.speaker;
    }

    public void setParticipationForm(final String participationForm) {

        this.participationForm = participationForm;
    }

    public void setShowLaunching(final String showLaunching) {

        this.showLaunching = showLaunching;
    }

    public void setSpeaker(final String speaker) {

        this.speaker = speaker;
    }

    public void setTitleEntry(final String titleEntry) {

        this.titleEntry = titleEntry;
    }
}
