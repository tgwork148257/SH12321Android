package com.hebe.report.bean;

/**
 * Created by Hebe on 16/7/23.
 */

public class FileResultBean {
    private int code;
    private DataBean data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public DataBean getData() {
        return data;
    }

    public static class DataBean{
        private String file_path;

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getFile_path() {
            return file_path;
        }
    }
}
