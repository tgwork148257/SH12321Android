package com.hebe.report.bean;

/**
 * Created by Hebe on 7-24-024.
 */

public class VerifyResultBean {

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
        private Object store_website;
        private String store_sale_website;
        private String buy_mobile;
        private String report_type;
        private String operator;
        private String buy_time;
        private String address;
        private String img;
        private String card_img;
        private String reason;
        private String type;
        private String add_time;
        private String mobile;
        private String con_flag;
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

        public Object getStore_website() {
            return store_website;
        }

        public void setStore_website(Object store_website) {
            this.store_website = store_website;
        }

        public String getStore_sale_website() {
            return store_sale_website;
        }

        public void setStore_sale_website(String store_sale_website) {
            this.store_sale_website = store_sale_website;
        }

        public String getBuy_mobile() {
            return buy_mobile;
        }

        public void setBuy_mobile(String buy_mobile) {
            this.buy_mobile = buy_mobile;
        }

        public String getReport_type() {
            return report_type;
        }

        public void setReport_type(String report_type) {
            this.report_type = report_type;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getBuy_time() {
            return buy_time;
        }

        public void setBuy_time(String buy_time) {
            this.buy_time = buy_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCard_img() {
            return card_img;
        }

        public void setCard_img(String card_img) {
            this.card_img = card_img;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
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

        public String getJw_id() {
            return jw_id;
        }

        public void setJw_id(String jw_id) {
            this.jw_id = jw_id;
        }
    }
}
