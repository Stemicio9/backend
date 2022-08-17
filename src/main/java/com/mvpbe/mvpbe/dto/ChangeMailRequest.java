package com.mvpbe.mvpbe.dto;

public class ChangeMailRequest {

    private String newmail;
    private String code;

    public String getNewmail() {
        return newmail;
    }

    public String getCode() {
        return code;
    }

    public void setNewmail(String newmail) {
        this.newmail = newmail;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
