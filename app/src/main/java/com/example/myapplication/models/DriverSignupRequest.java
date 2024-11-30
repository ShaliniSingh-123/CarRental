package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class DriverSignupRequest {

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("email")
    private String email;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("password")
    private String password;

    @SerializedName("license_number")
    private String licenseNumber;

    @SerializedName("license_expiry_date")
    private String licenseExpiryDate;

    @SerializedName("license_front_image_url")
    private String licenseFrontImageUrl; // Path or URL for the front license photo

    @SerializedName("license_back_image_url")
    private String licenseBackImageUrl;  // Path or URL for the back license photo

    @SerializedName("is_terms_accepted")
    private boolean isTermsAccepted;

    // Constructor
    public DriverSignupRequest(String fullName, String email, String mobile, String password,
                               String licenseNumber, String licenseExpiryDate,
                               String licenseFrontImageUrl, String licenseBackImageUrl, boolean isTermsAccepted) {
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.licenseNumber = licenseNumber;
        this.licenseExpiryDate = licenseExpiryDate;
        this.licenseFrontImageUrl = licenseFrontImageUrl;
        this.licenseBackImageUrl = licenseBackImageUrl;
        this.isTermsAccepted = isTermsAccepted;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getLicenseFrontImageUrl() {
        return licenseFrontImageUrl;
    }

    public void setLicenseFrontImageUrl(String licenseFrontImageUrl) {
        this.licenseFrontImageUrl = licenseFrontImageUrl;
    }

    public String getLicenseBackImageUrl() {
        return licenseBackImageUrl;
    }

    public void setLicenseBackImageUrl(String licenseBackImageUrl) {
        this.licenseBackImageUrl = licenseBackImageUrl;
    }

    public boolean isTermsAccepted() {
        return isTermsAccepted;
    }

    public void setTermsAccepted(boolean termsAccepted) {
        isTermsAccepted = termsAccepted;
    }

    @Override
    public String toString() {
        return "DriverSignupRequest{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", licenseExpiryDate='" + licenseExpiryDate + '\'' +
                ", licenseFrontImageUrl='" + licenseFrontImageUrl + '\'' +
                ", licenseBackImageUrl='" + licenseBackImageUrl + '\'' +
                ", isTermsAccepted=" + isTermsAccepted +
                '}';
    }
}
