package com.example.myapplication.network;


import com.example.myapplication.models.DriverImageResponse;
import com.example.myapplication.models.DriverResponse1;
import com.example.myapplication.models.ImageResponse;
import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.models.LogoutResponse;
import com.example.myapplication.models.PartnerRegisterRequest;
import com.example.myapplication.models.PartnerResponseRequest;
import com.example.myapplication.models.RegisterRequest;
import com.example.myapplication.models.RegisterResponse;
import com.example.myapplication.models.UserProfile;

import retrofit2.Call;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Part;
import retrofit2.http.Multipart;
import retrofit2.http.Field;


public interface ApiService {

    // Define the POST request for login
    @POST("/api/v1/users/login")  // The endpoint of the login API
    Call<LoginResponse> login(@Body LoginRequest loginRequest); // Send LoginRequest and receive LoginResponse.java

    // Define the POST request for registration
    @POST("/api/v1/customers/customerRegister")  // The endpoint of the register API
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
    Call<PartnerResponseRequest> register(@Body PartnerRegisterRequest request);

}
