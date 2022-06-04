package com.kanad.health;

public class User {
    public String fullname, ph_number, email, password;
    public User(){

    }
    public User(String fullname, String ph_number, String email, String password){
        this.fullname = fullname;
        this.email = email;
        this.ph_number = ph_number;
        this.password = password;

    }
}
