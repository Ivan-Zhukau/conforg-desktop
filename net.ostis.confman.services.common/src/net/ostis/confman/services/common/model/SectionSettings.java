package net.ostis.confman.services.common.model;

public class SectionSettings {

    private Section section;

    private int     chairmanTime;

    private int     reportTime;

    private int     plenaryReportTime;

    private int     breakTime;

    public SectionSettings() {

        super();
    }

    public Section getSection() {

        return this.section;
    }

    public void setSection(final Section section) {

        this.section = section;
    }

    public int getChairmanTime() {

        return this.chairmanTime;
    }

    public void setChairmanTime(final int chairmanTime) {

        this.chairmanTime = chairmanTime;
    }

    public int getReportTime() {

        return this.reportTime;
    }

    public void setReportTime(final int reportTime) {

        this.reportTime = reportTime;
    }

    public int getPlenaryReportTime() {

        return this.plenaryReportTime;
    }

    public void setPlenaryReportTime(final int plenaryReportTime) {

        this.plenaryReportTime = plenaryReportTime;
    }

    public int getBreakTime() {

        return this.breakTime;
    }

    public void setBreakTime(final int breakTime) {

        this.breakTime = breakTime;
    }

}
