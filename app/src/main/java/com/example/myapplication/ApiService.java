package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Define the POST request for login
    @POST("/login")  // The endpoint of the login API
    Call<LoginResponse> login(@Body LoginRequest loginRequest); // Send LoginRequest and receive LoginResponse

    // Define the POST request for registration
    @POST("/register")  // The endpoint of the register API
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest); // Send RegisterRequest and receive RegisterResponse
}
