package com.hebe.report.bean;

/**
 * Created by Hebe on 16/7/23.
 */

public class WifiResultBean {

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
        private String call_time;
        private String report_address;
        private String name;
        private Object content;
        private String type;
        private String add_time;
        private String mobile;
        private String jw_id;
        private String feedback;
        private String feedback_result;
        private String feedback_score;
        private String operator_rs;

        public void setOperator_rs(String operator_rs) {
            this.operator_rs = operator_rs;
        }

        public String getOperator_rs() {
            return operator_rs;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback_result(String feedback_result) {
            this.feedback_result = feedback_result;
        }

        public String getFeedback_result() {
            return feedback_result;
        }

        public void setFeedback_score(String feedback_score) {
            this.feedback_score = feedback_score;
        }

        public String getFeedback_score() {
            return feedback_score;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getCall_time() {
            return call_time;
        }

        public void setCall_time(String call_time) {
            this.call_time = call_time;
        }

        public String getReport_address() {
            return report_address;
        }

        public void setReport_address(String report_address) {
            this.report_address = report_address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
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

        public String getJw_id() {
            return jw_id;
        }

        public void setJw_id(String jw_id) {
            this.jw_id = jw_id;
        }
    }
}
