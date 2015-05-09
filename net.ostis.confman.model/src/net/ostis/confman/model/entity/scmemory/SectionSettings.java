package net.ostis.confman.model.entity.scmemory;

import java.util.UUID;

public class SectionSettings {

    private UUID sectionId;

    private int  chairmanTime;

    private int  reportTime;

    private int  plenaryReportTime;

    private int  breakTime;

    private int  coffeeBreaksNum;

    private int  coffeeBreaksTime;

    public SectionSettings() {

        super();
    }

    public SectionSettings(UUID sectionId, int chairmanTime, int reportTime,
            int plenaryReportTime, int breakTime, int coffeeBreaksNum,
            int coffeeBreaksTime) {

        super();
        this.sectionId = sectionId;
        this.chairmanTime = chairmanTime;
        this.reportTime = reportTime;
        this.plenaryReportTime = plenaryReportTime;
        this.breakTime = breakTime;
        this.coffeeBreaksNum = coffeeBreaksNum;
        this.coffeeBreaksTime = coffeeBreaksTime;
    }

    public UUID getSectionId() {

        return this.sectionId;
    }

    public void setSectionId(final UUID sectionId) {

        this.sectionId = sectionId;
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

    public int getCoffeeBreakNumber() {

        return this.coffeeBreaksNum;
    }

    public void setCoffeeBreaksNum(final int coffeeBreaksNum) {

        this.coffeeBreaksNum = coffeeBreaksNum;
    }

    public int getCoffeeBreakTime() {

        return this.coffeeBreaksTime;
    }

    public void setCoffeeBreaksTime(final int coffeeBreaksTime) {

        this.coffeeBreaksTime = coffeeBreaksTime;
    }

}
