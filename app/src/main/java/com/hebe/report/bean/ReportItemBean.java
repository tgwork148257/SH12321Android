package com.hebe.report.bean;

import java.io.Serializable;

/**
 * Created by Hebe on 7-5-005.
 */

public class ReportItemBean implements Serializable {

    public ReportItemBean(){};
    public ReportItemBean(int drawableId,String name){
        this.drawableId = drawableId;
        this.name = name;
    }
    private int drawableId;
    private String name;

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
