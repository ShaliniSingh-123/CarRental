package com.example.myapplication;

public class LoginResponse {
    private String message;
    private String token;  // If your API returns a token after login

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
