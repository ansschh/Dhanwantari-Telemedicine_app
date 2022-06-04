package com.kanad.health;

public class DoctorRequestStatus {
    String status;
    String useruid;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUseruid(String useruid) {
        this.useruid = useruid;
    }

    public String getStatus() {
        return status;
    }

    public String getUseruid() {
        return useruid;
    }
}
