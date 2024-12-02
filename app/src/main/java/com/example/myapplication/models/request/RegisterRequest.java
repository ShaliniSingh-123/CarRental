package com.example.myapplication.models.request;

public class RegisterRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;

    // Constructor
    public RegisterRequest(String fullName, String email, String mobile, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = mobile;
        this.password = password;
    }

    // Getter and Setter for fullName
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for mobile
    public String getMobile() {
        return phoneNumber;
    }

    public void setMobile(String mobile) {
        this.phoneNumber = mobile;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
