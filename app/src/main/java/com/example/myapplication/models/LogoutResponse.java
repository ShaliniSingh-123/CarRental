package com.example.myapplication.models;

public class LogoutResponse {

    private int statusCode;
    private LogoutData data;
    private String message;
    private boolean success;

    // Constructor
    public LogoutResponse(int statusCode, LogoutData data, String message, boolean success) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.success = success;
    }

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LogoutData getData() {
        return data;
    }

    public void setData(LogoutData data) {
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

    // Nested class to represent the 'data' part of the response
    public static class LogoutData {
        // In your example, 'data' is an empty object, but you can add fields later if required
    }
}

