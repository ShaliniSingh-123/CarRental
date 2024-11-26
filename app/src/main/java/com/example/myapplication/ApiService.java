package com.example.myapplication;

import retrofit2.Call;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Part;
import retrofit2.http.Multipart;


public interface ApiService {

    // Define the POST request for login
    @POST("/api/v1/users/login")  // The endpoint of the login API
    Call<LoginResponse> login(@Body LoginRequest loginRequest); // Send LoginRequest and receive LoginResponse.java

    // Define the POST request for registration
    @POST("/api/v1/users/register")  // The endpoint of the register API
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest); // Send RegisterRequest and receive RegisterResponse


    @GET("/api/v1/users/profile/{userId}")
    Call<UserProfile> getUserProfile(@Path("userId") String userId);

    @Multipart
    @POST("/api/v1/users/uploadImage") // Replace with your server's endpoint URL
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part file);

    @Multipart
    @POST("/api/v1/users/updateProfile/")
    Call<UserProfile> updateUserProfileWithImage(
            @Part("fullName") RequestBody name,
            @Part("email") RequestBody email,
            @Part("mobile") RequestBody phone,
            @Part("address") RequestBody address
    );

        @POST("/logout")
        Call<LogoutResponse> logoutUser();




}
