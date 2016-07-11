package com.hebe.report.bean;

import java.io.Serializable;

/**
 * Created by Hebe on 7-11-011.
 */

public class MyReport implements Serializable {

    private int reportid;
    private String reporttype;
    private String reporttime;

    public MyReport(){};
    public MyReport(int id,String type,String time){
        this.reportid = id;
        this.reporttype = type;
        this.reporttime = time;
    }

    public int getReportid() {
        return reportid;
    }

    public void setReportid(int reportid) {
        this.reportid = reportid;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    @Override
    public String toString() {
        return "MyReport{" +
                "reportid=" + reportid +
                ", reporttype='" + reporttype + '\'' +
                ", reporttime='" + reporttime + '\'' +
                '}';
    }
}
