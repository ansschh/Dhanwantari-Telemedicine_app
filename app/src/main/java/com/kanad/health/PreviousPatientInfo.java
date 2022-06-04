package com.kanad.health;

public class PreviousPatientInfo {
    String uid;
    String NAME_;
    String GENDER_;
    String AGE_;
    String imageurl;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNAME_() {
        return NAME_;
    }

    public void setNAME_(String NAME_) {
        this.NAME_ = NAME_;
    }

    public String getGENDER_() {
        return GENDER_;
    }

    public void setGENDER_(String GENDER_) {
        this.GENDER_ = GENDER_;
    }

    public String getAGE_() {
        return AGE_;
    }

    public void setAGE_(String AGE_) {
        this.AGE_ = AGE_;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public PreviousPatientInfo(String uid, String NAME_, String GENDER_, String AGE_, String imageurl) {
        this.uid = uid;
        this.NAME_ = NAME_;
        this.GENDER_ = GENDER_;
        this.AGE_ = AGE_;
        this.imageurl = imageurl;
    }

    public PreviousPatientInfo() {
    }
}
