package com.hebe.report.bean;

/**
 * Created by Hebe on 7-23-023.
 */

public class AppResultBean {

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
        private String name;
        private String source;
        private String content;
        private String type;
        private String add_time;
        private String mobile;
        private String con_flag;
        private String jw_id;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public String getCon_flag() {
            return con_flag;
        }

        public void setCon_flag(String con_flag) {
            this.con_flag = con_flag;
        }

        public String getId() {
            return jw_id;
        }

        public void setId(String id) {
            this.jw_id = id;
        }
    }
}
