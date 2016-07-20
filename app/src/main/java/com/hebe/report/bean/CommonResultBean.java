package com.hebe.report.bean;

/**
 * Created by Hebe on 7-20-020.
 */

public class CommonResultBean {



    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String jw_id;

        public String getJw_id() {
            return jw_id;
        }

        public void setJw_id(String jw_id) {
            this.jw_id = jw_id;
        }
    }
}
