package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class InterfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        Button signupButton = findViewById(R.id.registerRedirectText);
        Button loginButton = findViewById(R.id.loginRedirectText);

        // Set OnClickListener for the "Sign Up" button
        signupButton.setOnClickListener(v -> {
            // Redirect to RegisterActivity
            Intent intent = new Intent(InterfaceActivity.this, SignupDashboardActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener for the "Login" button (optional, as per your requirement)
        loginButton.setOnClickListener(v -> {
            // Redirect to LoginActivity (add this if you want)
            Intent intent = new Intent(InterfaceActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        // Set OnClickListener for the "Cross" button

    }
}
