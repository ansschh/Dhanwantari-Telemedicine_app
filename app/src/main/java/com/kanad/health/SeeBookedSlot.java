package com.kanad.health;

public class SeeBookedSlot {
    String starttime;
    String endtime;
    String state;
    String district;
    String slot;
    String uid;
    String doctorname_;

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
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

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDoctorname_() {
        return doctorname_;
    }

    public void setDoctorname_(String doctorname_) {
        this.doctorname_ = doctorname_;
    }

    public SeeBookedSlot(String starttime, String endtime, String state, String district, String slot, String uid, String doctorname_) {
        this.starttime = starttime;
        this.endtime = endtime;
        this.state = state;
        this.district = district;
        this.slot = slot;
        this.uid = uid;
        this.doctorname_ = doctorname_;
    }

    public SeeBookedSlot() {
    }
}
