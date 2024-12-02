package com.example.myapplication.models.response;


public class DriverImageResponse {
    private boolean status;
    private String message;
    private Data data;

    // Getters and setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // Nested class for 'data' field if applicable
    public static class Data {
        private String frontPhotoUrl;
        private String backPhotoUrl;

        // Getters and setters
        public String getFrontPhotoUrl() {
            return frontPhotoUrl;
        }

        public void setFrontPhotoUrl(String frontPhotoUrl) {
            this.frontPhotoUrl = frontPhotoUrl;
        }

        public String getBackPhotoUrl() {
            return backPhotoUrl;
        }

        public void setBackPhotoUrl(String backPhotoUrl) {
            this.backPhotoUrl = backPhotoUrl;
        }
    }
}
