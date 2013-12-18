package net.ostis.confman.model.entity;


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
    
    public void setReportTime(int reportTime) {

        this.reportTime = reportTime;
    }
    
    public void setPlenaryReportTime(int plenaryReportTime) {

        this.plenaryReportTime = plenaryReportTime;
    }
    
    public void setChairmanTime(int chairmanTime) {

        this.chairmanTime = chairmanTime;
    }
    
    public void setBreakTime(int breakTime) {

        this.breakTime = breakTime;
    }
    
    public int getReportTime() {

        return reportTime;
    }
    
    public int getPlenaryReportTime() {

        return plenaryReportTime;
    }
    
    public int getChairmanTime() {

        return chairmanTime;
    }
    
    public int getBreakTime() {

        return breakTime;
    }

}
