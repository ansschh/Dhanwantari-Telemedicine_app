package com.kanad.health;

public class retrievingData {
    String first_name;
    String last_name;

    public retrievingData() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public retrievingData(String first_name, String last_name, String mobilenumber, String password, String email, String dateofbirth, String address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobilenumber = mobilenumber;
        this.password = password;
        this.email = email;
        this.dateofbirth = dateofbirth;
        this.address = address;
    }

    String mobilenumber;
    String password;
    String email;
    String dateofbirth;
    String address;
}
