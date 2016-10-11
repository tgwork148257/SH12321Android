package com.hebe.report.bean;

/**
 * Created by Hebe on 7-20-020.
 */

public class UserToken {


    private String user_token;
    private String register;

    public void setRegister(String register) {
        this.register = register;
    }

    public String getRegister() {
        return register;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
}
