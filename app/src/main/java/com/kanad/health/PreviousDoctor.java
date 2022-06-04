package com.kanad.health;

public class PreviousDoctor {
    String uid;
    String state;
    String district;
    String date;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PreviousDoctor(String uid, String state, String district, String date) {
        this.uid = uid;
        this.state = state;
        this.district = district;
        this.date = date;
    }

    public PreviousDoctor() {
    }
}
