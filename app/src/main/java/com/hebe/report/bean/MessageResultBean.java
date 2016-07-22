package com.hebe.report.bean;

/**
 * Created by Hebe on 7-22-022.
 */

public class MessageResultBean {

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
        private String type_name;
        private String report_mobile;
        private String report_type;
        private String accept_mobile;
        private String content;
        private String type;
        private String add_time;
        private String mobile;
        private String id;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getReport_mobile() {
            return report_mobile;
        }

        public void setReport_mobile(String report_mobile) {
            this.report_mobile = report_mobile;
        }

        public String getReport_type() {
            return report_type;
        }

        public void setReport_type(String report_type) {
            this.report_type = report_type;
        }

        public String getAccept_mobile() {
            return accept_mobile;
        }

        public void setAccept_mobile(String accept_mobile) {
            this.accept_mobile = accept_mobile;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
