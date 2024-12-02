package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.models.request.LoginRequest;
import com.example.myapplication.models.response.LoginResponse;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.utils.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupDashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleLogin() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        // Check if both email and password fields are filled
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make the POST request to the login API using RetrofitClient to get Retrofit instance
        ApiService apiService = RetrofitClient.getRetrofitInstance(LoginActivity.this).create(ApiService.class);
        LoginRequest loginRequest = new LoginRequest(email, password);

        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        // Save tokens to SharedPreferences
                        SharedPreferencesManager.saveTokens(LoginActivity.this, loginResponse.getData().getAccessToken(), loginResponse.getData().getRefreshToken());

                        // Show success message
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        String role=loginResponse.getData().getUser().getRole();
                        // Pass user data to the next activity
                        Intent intent;

                        switch (role) {
                            case "customer":
                                intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                break;
                            case "partner":
                                intent = new Intent(LoginActivity.this, PartnerDashboardActivity.class);
                                break;
                            case "driver":
                                intent = new Intent(LoginActivity.this, DriverDashboardActivity.class);
                                break;
                            default:
                                Toast.makeText(LoginActivity.this, "Unknown role: " + role, Toast.LENGTH_SHORT).show();
                                return; // Exit if role is unknown
                        }
                        intent.putExtra("user_email", loginResponse.getData().getUser().getEmail());
//                        intent.putExtra("user_fullName", loginResponse.getData().getUser().);

                        startActivity(intent);
                        finish();  // Close the login activity
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String errorMessage = response.message() != null ? response.message() : "Unknown error";
                    Toast.makeText(LoginActivity.this, "Login Failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Unsuccessful response: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Login request failed", t);
            }
        });
    }


}
