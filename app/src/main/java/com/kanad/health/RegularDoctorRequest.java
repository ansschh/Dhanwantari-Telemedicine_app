package com.kanad.health;

public class RegularDoctorRequest {
    String status;
    String useruid;

    public RegularDoctorRequest(String status, String useruid) {
        this.status = status;
        this.useruid = useruid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUseruid() {
        return useruid;
    }

    public void setUseruid(String useruid) {
        this.useruid = useruid;
    }

    public RegularDoctorRequest() {
    }
}
