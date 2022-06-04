package com.kanad.health;

public class UserHelperClass {
    String Name, First_Name, Last_Name, Phone_number, password, email;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String first_Name, String last_Name, String phone_number, String password, String email) {
        Name = name;
        First_Name = first_Name;
        Last_Name = last_Name;
        Phone_number = phone_number;
        this.password = password;
        this.email = email;
    }

    public UserHelperClass(String personGivenName, String personFamilyName, String personEmail, String personId) {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
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
}
