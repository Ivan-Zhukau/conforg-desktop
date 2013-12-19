package net.ostis.confman.services.common.model;

public class SectionSettings {

    private Section section;

    private Integer chairmanTime;

    private Integer reportTime;

    private Integer plenaryReportTime;

    private Integer breakTime;

    private Integer coffeeBreakNumber;

    private Integer coffeeBreakTime;

    public SectionSettings() {

        super();
        setAllNull();
    }

    private void setAllNull() {
        this.breakTime = 0;
        this.chairmanTime = 0;
        this.coffeeBreakNumber = 0;
        this.coffeeBreakTime = 0;
        this.plenaryReportTime = 0;
        this.reportTime = 0;        
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

    public void setChairmanTime(final Integer chairmanTime) {

        this.chairmanTime = chairmanTime;
    }

    public int getReportTime() {

        return this.reportTime;
    }

    public void setReportTime(final Integer reportTime) {

        this.reportTime = reportTime;
    }

    public int getPlenaryReportTime() {

        return this.plenaryReportTime;
    }

    public void setPlenaryReportTime(final Integer plenaryReportTime) {

        this.plenaryReportTime = plenaryReportTime;
    }

    public int getBreakTime() {

        return this.breakTime;
    }

    public void setBreakTime(final Integer breakTime) {

        this.breakTime = breakTime;
    }

    public Integer getCoffeeBreakNumber() {

        return this.coffeeBreakNumber;
    }

    public void setCoffeeBreakNumber(final Integer coffeeBreakNumber) {

        this.coffeeBreakNumber = coffeeBreakNumber;
    }

    public Integer getCoffeeBreakTime() {

        return this.coffeeBreakTime;
    }

    public void setCoffeeBreakTime(final Integer coffeeBreakTime) {

        this.coffeeBreakTime = coffeeBreakTime;
    }

}
