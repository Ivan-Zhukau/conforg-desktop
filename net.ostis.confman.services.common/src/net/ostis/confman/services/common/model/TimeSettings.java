package net.ostis.confman.services.common.model;

public class TimeSettings {

    private int chairmanTime;

    private int reportTime;

    private int plenaryReportTime;

    private int breakTime;

    public TimeSettings() {

        super();
        setAllOnNull();
    }

    private void setAllOnNull() {

        this.breakTime = 0;
        this.chairmanTime = 0;
        this.reportTime = 0;
        this.plenaryReportTime = 0;
    }

    public void setReportTime(final int reportTime) {

        this.reportTime = reportTime;
    }

    public void setPlenaryReportTime(final int plenaryReportTime) {

        this.plenaryReportTime = plenaryReportTime;
    }

    public void setChairmanTime(final int chairmanTime) {

        this.chairmanTime = chairmanTime;
    }

    public void setBreakTime(final int breakTime) {

        this.breakTime = breakTime;
    }

    public int getReportTime() {

        return this.reportTime;
    }

    public int getPlenaryReportTime() {

        return this.plenaryReportTime;
    }

    public int getChairmanTime() {

        return this.chairmanTime;
    }

    public int getBreakTime() {

        return this.breakTime;
    }

}
