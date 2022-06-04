package com.kanad.health;

public class RPMRequest {
    String uid;
    String status;

    public RPMRequest(String uid, String status) {
        this.uid = uid;
        this.status = status;
    }

    public RPMRequest() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
