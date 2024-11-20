package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText fullNameEditText, emailEditText, mobileEditText, passwordEditText, confirmPasswordEditText;
    private CheckBox termsCheckBox;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        termsCheckBox = findViewById(R.id.termsCheckBox);
        registerButton = findViewById(R.id.registerButton);

        // Initialize the back arrow
        ImageView backArrow = findViewById(R.id.backArrow);

        // Set click listener on back arrow
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the previous one
                onBackPressed();
            }
        });

        // Handle Register button click
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!termsCheckBox.isChecked()) {
                    Toast.makeText(SignupActivity.this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Retrieve input values
                String fullName = fullNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (fullName.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Create RegisterRequest object
                    RegisterRequest registerRequest = new RegisterRequest(fullName, email, mobile, password);

                    // Use RetrofitClient to get the Retrofit instance
                    Retrofit retrofit = RetrofitClient.getRetrofitInstance();
                    ApiService apiService = retrofit.create(ApiService.class);

                    // Make the API call using the register endpoint
                    Call<RegisterResponse> call = apiService.register(registerRequest);
                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            if (response.isSuccessful()) {
                                // Handle successful registration
                                RegisterResponse registerResponse = response.body();

                                if (registerResponse != null) {
                                    // Check if the message or any other success criteria are met
                                    Toast.makeText(SignupActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                                    // Start the login activity after successful registration
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Handle case where response body is null (although unlikely if API is working correctly)
                                    Toast.makeText(SignupActivity.this, "Registration failed: No response body", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Handle response error (e.g., 400 or 500 HTTP error)
                                Toast.makeText(SignupActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            // Handle network or other errors
                            Toast.makeText(SignupActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
