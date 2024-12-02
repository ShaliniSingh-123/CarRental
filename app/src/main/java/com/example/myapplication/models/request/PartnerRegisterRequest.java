package com.example.myapplication.models.request;



public class PartnerRegisterRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;

    public PartnerRegisterRequest(String fullName, String email, String mobile, String address, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber= mobile;
        this.address = address;
        this.password = password;
    }

    // Getters and setters if needed
}

