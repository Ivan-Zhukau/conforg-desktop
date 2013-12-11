package net.ostis.confman.model.registrationform;

import java.util.ArrayList;
import java.util.List;

public class ArticleInformation {

    private String       titleEntry;

    private List<String> coAuthors;

    private String       participationForm;

    private String       speaker;

    private String       showLaunching;

    public ArticleInformation() {

        super();
        this.coAuthors = new ArrayList<String>();
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

    public List<String> getCoAuthors() {

        return this.coAuthors;
    }

    public void setCoAuthors(final List<String> coAuthors) {

        this.coAuthors = coAuthors;
    }
}
