package com.example.myapplication.network;


import com.example.myapplication.models.response.DriverImageResponse;
import com.example.myapplication.models.response.DriverResponse1;
import com.example.myapplication.models.response.ImageResponse;
import com.example.myapplication.models.request.LoginRequest;
import com.example.myapplication.models.response.LoginResponse;
import com.example.myapplication.models.response.LogoutResponse;
import com.example.myapplication.models.request.PartnerRegisterRequest;
import com.example.myapplication.models.response.PartnerResponse;
import com.example.myapplication.models.request.RegisterRequest;
import com.example.myapplication.models.response.RegisterResponse;
import com.example.myapplication.models.response.UserProfileResponse;
import com.example.myapplication.models.response.UserProfileResponse;

import retrofit2.Call;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Part;
import retrofit2.http.Multipart;


public interface ApiService {

    // Define the POST request for login
    @POST("/api/v1/users/login")  // The endpoint of the login API
    Call<LoginResponse> login(@Body LoginRequest loginRequest); // Send LoginRequest and receive LoginResponse.java

    // Define the POST request for registration
    @POST("/api/v1/customers/customerRegister")  // The endpoint of the register API
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest); // Send RegisterRequest and receive RegisterResponse


    @GET("/api/v1/users/profile/{userId}")
    Call<UserProfileResponse> getUserProfile();

    @Multipart
    @POST("/api/v1/users/uploadImage") // Replace with your server's endpoint URL
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part file);

    @Multipart
    @POST("/api/v1/users/updateProfile/")
    Call<UserProfileResponse> updateUserProfileWithImage(
            @Part("fullName") RequestBody name,
            @Part("email") RequestBody email,
            @Part("address") RequestBody address
    );

        @POST("/api/v1/users/logout")
        Call<LogoutResponse> logoutUser();



    @Multipart
    @POST("uploadDriverLicensePhoto") // Endpoint for uploading driver license photos
    Call<DriverImageResponse> uploadDriverLicensePhoto(
            @Part MultipartBody.Part licenseFront,
            @Part MultipartBody.Part licenseBack
    );





    @Multipart
    @POST("/api/v1/drivers/registerDriver")
    Call<DriverResponse1> registerDriverWithPhotos(
            @Part MultipartBody.Part licenseFront,
            @Part MultipartBody.Part licenseBack,
            @Part("fullName") RequestBody fullName,
            @Part("email") RequestBody email,
            @Part("phoneNumber") RequestBody phoneNumber,
            @Part("password") RequestBody password,
            @Part("licenseNumber") RequestBody licenseNumber,
            @Part("licenseExpiryDate") RequestBody licenseExpiryDate
    );


    @POST("/api/v1/partners/registerPartner")  // Specify the endpoint
    Call<PartnerResponse> register(@Body PartnerRegisterRequest request);

}
