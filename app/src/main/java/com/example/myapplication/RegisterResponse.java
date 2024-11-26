package com.example.myapplication;
public class RegisterResponse{

    private int statusCode;
    private Data data;
    private String message;
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

    // Inner class to represent the 'data' object
    public static class Data {
        private String email;
        private String fullName;
        private String photo;

        private String mobile;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile(){
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

    // Constructor (optional)
    public RegisterResponse(int statusCode, Data data, String message, boolean success) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.success = success;
    }

    @Override
    public String toString() {
        return "LoginResponse.java{" +
                "statusCode=" + statusCode +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}

