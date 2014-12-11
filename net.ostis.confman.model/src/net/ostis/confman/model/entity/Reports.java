package net.ostis.confman.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reports")
public class Reports {

    private List<Report> reports;

    public Reports() {

        super();
        reports = new ArrayList<Report>();
    }

    @XmlElement(name = "report")
    public List<Report> getReports() {

        return this.reports;
    }

    public void setReports(final List<Report> reports) {

        this.reports = reports;
    }
}
