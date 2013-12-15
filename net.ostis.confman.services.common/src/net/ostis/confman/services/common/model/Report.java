package net.ostis.confman.services.common.model;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private String            title;

    private Section           section;

    private List<Participant> allAuthors;

    private Participant       mainAuthor;

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
}
