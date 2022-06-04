package com.kanad.health;

public class ProfileData {
    String first_name;
    String last_name;
    String mobilenumber;
    String password;
    String email;
    String dateofbirth;
    String address;
    String sex;
    String language;
    String usertype;
    String states;
    String districts;
    String yearofbirth;
    String Profile_Complete;
    String imageurl;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
    }

    public String getYearofbirth() {
        return yearofbirth;
    }

    public void setYearofbirth(String yearofbirth) {
        this.yearofbirth = yearofbirth;
    }

    public String getProfile_Complete() {
        return Profile_Complete;
    }

    public void setProfile_Complete(String profile_Complete) {
        Profile_Complete = profile_Complete;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public ProfileData(String first_name, String last_name, String mobilenumber, String password, String email, String dateofbirth, String address, String sex, String language, String usertype, String states, String districts, String yearofbirth, String profile_Complete, String imageurl) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobilenumber = mobilenumber;
        this.password = password;
        this.email = email;
        this.dateofbirth = dateofbirth;
        this.address = address;
        this.sex = sex;
        this.language = language;
        this.usertype = usertype;
        this.states = states;
        this.districts = districts;
        this.yearofbirth = yearofbirth;
        Profile_Complete = profile_Complete;
        this.imageurl = imageurl;
    }

    public ProfileData() {
    }
}
