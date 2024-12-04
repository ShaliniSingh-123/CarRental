package com.example.myapplication.models.response;

// This is the main UserProfile class that will contain the User data
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserProfileResponse {

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("data")
    private Data data;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private boolean success;

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Nested class for data
    public static class Data {

        @SerializedName("_id")
        private String id;

        @SerializedName("fullName")
        private String fullName;

        @SerializedName("email")
        private String email;

        @SerializedName("phoneNumber")
        private String phoneNumber;

        @SerializedName("address")
        private String address;

        @SerializedName("imgUrl")
        private String imgUrl;

        @SerializedName("bookingHistory")
        private List<Object> bookingHistory;

        @SerializedName("createdAt")
        private String createdAt;

        @SerializedName("updatedAt")
        private String updatedAt;

        @SerializedName("__v")
        private int version;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public List<Object> getBookingHistory() {
            return bookingHistory;
        }

        public void setBookingHistory(List<Object> bookingHistory) {
            this.bookingHistory = bookingHistory;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}


