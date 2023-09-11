package com.example.japanesequizappversion2.Model;

public class User {
    String fullName, userName, email, phoneNo, passWord, date, gender, otp, roles, status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User() {
    }
    public User(String fullName, String userName, String email, String phoneNo, String passWord, String date, String gender, String otp, String roles, String status) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.passWord = passWord;
        this.date = date;
        this.gender = gender;
        this.otp = otp;
        this.roles = roles;
        this.status = status;
    }
    public User(String fullName, String userName, String email, String phoneNo, String passWord, String date, String gender, String otp, String roles) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.passWord = passWord;
        this.date = date;
        this.gender = gender;
        this.otp = otp;
        this.roles = roles;
    }

    public User(String fullName, String userName, String email, String phoneNo, String date, String gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.date = date;
        this.gender = gender;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}