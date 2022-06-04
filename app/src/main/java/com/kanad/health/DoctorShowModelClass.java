package com.kanad.health;

public class DoctorShowModelClass {
    String Specilization_ ;
    String Degree_ ;
    String Institute_ ;
    String YearOfCompletetion ;
    String YearsOfExpirience ;
    String approved;
    String Image_Url;
    String Doctor_Name;
    String doctor_uid_dummy;

    public String getSpecilization_() {
        return Specilization_;
    }

    public void setSpecilization_(String specilization_) {
        Specilization_ = specilization_;
    }

    public String getDegree_() {
        return Degree_;
    }

    public void setDegree_(String degree_) {
        Degree_ = degree_;
    }

    public String getInstitute_() {
        return Institute_;
    }

    public void setInstitute_(String institute_) {
        Institute_ = institute_;
    }

    public String getYearOfCompletetion() {
        return YearOfCompletetion;
    }

    public void setYearOfCompletetion(String yearOfCompletetion) {
        YearOfCompletetion = yearOfCompletetion;
    }

    public String getYearsOfExpirience() {
        return YearsOfExpirience;
    }

    public void setYearsOfExpirience(String yearsOfExpirience) {
        YearsOfExpirience = yearsOfExpirience;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getImage_Url() {
        return Image_Url;
    }

    public void setImage_Url(String image_Url) {
        Image_Url = image_Url;
    }

    public String getDoctor_Name() {
        return Doctor_Name;
    }

    public void setDoctor_Name(String doctor_Name) {
        Doctor_Name = doctor_Name;
    }

    public String getDoctor_uid_dummy() {
        return doctor_uid_dummy;
    }

    public void setDoctor_uid_dummy(String doctor_uid_dummy) {
        this.doctor_uid_dummy = doctor_uid_dummy;
    }

    public DoctorShowModelClass(String specilization_, String degree_, String institute_, String yearOfCompletetion, String yearsOfExpirience, String approved, String image_Url, String doctor_Name, String doctor_uid_dummy) {
        Specilization_ = specilization_;
        Degree_ = degree_;
        Institute_ = institute_;
        YearOfCompletetion = yearOfCompletetion;
        YearsOfExpirience = yearsOfExpirience;
        this.approved = approved;
        Image_Url = image_Url;
        Doctor_Name = doctor_Name;
        this.doctor_uid_dummy = doctor_uid_dummy;
    }

    public DoctorShowModelClass() {
    }
}
