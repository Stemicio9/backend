package com.mvpbe.mvpbe.dto;

public class ResultUpload {

    private boolean uploadok;


    public ResultUpload(){}

    public ResultUpload(boolean uploadok){
        this.uploadok = uploadok;
    }

    public boolean isUploadok() {
        return uploadok;
    }

    public void setUploadok(boolean uploadok) {
        this.uploadok = uploadok;
    }
}
