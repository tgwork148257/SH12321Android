package com.hebe.report.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hebe on 7-11-011.
 */

public class MyReport implements Serializable {


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
        private int totalPage;
        private List<ListBean> list;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String type_name;
            private String type;
            private String jw_id;
            private String report_time;
            private String con_flag;

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getJw_id() {
                return jw_id;
            }

            public void setJw_id(String jw_id) {
                this.jw_id = jw_id;
            }

            public String getReport_time() {
                return report_time;
            }

            public void setReport_time(String report_time) {
                this.report_time = report_time;
            }

            public String getCon_flag() {
                return con_flag;
            }

            public void setCon_flag(String con_flag) {
                this.con_flag = con_flag;
            }
        }
    }
}
