package net.ostis.confman.model.entity.scmemory;

import java.util.List;

public class SectionSettings extends BaseEntity {

    private List<SectionBreaks> sectionBreaks;

    public SectionSettings() {

        super();
    }

    public SectionSettings(List<SectionBreaks> sectionBreaks) {

        super();
        this.sectionBreaks = sectionBreaks;
    }

    public List<SectionBreaks> getSectionBreaks() {

        return this.sectionBreaks;
    }

    public void setSectionBreaks(final List<SectionBreaks> sectionBreaks) {

        this.sectionBreaks = sectionBreaks;
    }

}
