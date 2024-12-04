package com.example.myapplication.models.response;

public class AddCarResponse {
    private boolean success;
    private String message;

    // Default Constructor
    public AddCarResponse() {
    }

    // Parameterized Constructor
    public AddCarResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter for success
    public boolean isSuccess() {
        return success;
    }

    // Setter for success
    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }
}
