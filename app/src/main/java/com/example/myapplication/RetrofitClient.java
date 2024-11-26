package com.example.myapplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // Static method to return Retrofit instance
    public static Retrofit getRetrofitInstance() {
        // Create the logging interceptor for logging HTTP request and response bodies
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // Log the full body of requests and responses

        // Create OkHttpClient and add the interceptor
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)  // Add the logging interceptor to OkHttpClient
                .build();

        // Create and return the Retrofit instance
        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:3000") // Base URL for the API
                .client(okHttpClient)               // Use the OkHttpClient with logging
                .addConverterFactory(GsonConverterFactory.create()) // Convert JSON to Java objects using Gson
                .build();
    }
}
