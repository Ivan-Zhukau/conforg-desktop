package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "section-report-breaks")
public class SectionBreaks {

    private long sectionId;

    private int  chairmanTime;

    private int  reportTime;

    private int  plenaryReportTime;

    private int  breakTime;
    
    private int coffeeBreaksNum;
    
    private int coffeeBreaksTime;

    public SectionBreaks() {

        super();
    }

    @XmlElement(name = "section-id")
    public long getSectionId() {

        return this.sectionId;
    }

    public void setSectionId(final long sectionId) {

        this.sectionId = sectionId;
    }

    @XmlElement(name = "chairman-time")
    public int getChairmanTime() {

        return this.chairmanTime;
    }

    public void setChairmanTime(final int chairmanTime) {

        this.chairmanTime = chairmanTime;
    }

    @XmlElement(name = "report-time")
    public int getReportTime() {

        return this.reportTime;
    }

    public void setReportTime(final int reportTime) {

        this.reportTime = reportTime;
    }

    @XmlElement(name = "plenary-time")
    public int getPlenaryReportTime() {

        return this.plenaryReportTime;
    }

    public void setPlenaryReportTime(final int plenaryReportTime) {

        this.plenaryReportTime = plenaryReportTime;
    }

    @XmlElement(name = "break-time")
    public int getBreakTime() {

        return this.breakTime;
    }

    public void setBreakTime(final int breakTime) {

        this.breakTime = breakTime;
    }
    
    @XmlElement(name = "coffee-break-time")
    public int getCoffeeBreaksNum() {

        return coffeeBreaksNum;
    }
    
    @XmlElement(name = "coffee-break-num")
    public int getCoffeeBreaksTime() {

        return coffeeBreaksTime;
    }
        
    public void setCoffeeBreaksNum(int coffeeBreaksNum) {

        this.coffeeBreaksNum = coffeeBreaksNum;
    }
    
    public void setCoffeeBreaksTime(int coffeeBreaksTime) {

        this.coffeeBreaksTime = coffeeBreaksTime;
    }

}
