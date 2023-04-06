package com.example.letschat.models;

public class Users {
    String profile,last_msg,user_id,mail,password,user_name;

    public Users(String profile, String last_msg, String user_id, String mail, String password, String user_name) {
        this.profile = profile;
        this.last_msg = last_msg;
        this.user_id = user_id;
        this.mail = mail;
        this.password = password;
        this.user_name = user_name;
    }
    public Users(){}

    public Users(String mail, String password, String user_name) {
        this.mail = mail;
        this.password = password;
        this.user_name = user_name;
    }
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLast_msg() {
        return last_msg;
    }

    public void setLast_msg(String last_msg) {
        this.last_msg = last_msg;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
