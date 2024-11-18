package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        // Profile section
        TextView tvEditProfilePicture = findViewById(R.id.tvEditProfilePicture);
        TextView tvEditPersonalDetails = findViewById(R.id.tvEditPersonalDetails);

        // Verification section
        TextView tvVerifyID = findViewById(R.id.tvVerifyID);

        // About you section
        TextView tvAddMiniBio = findViewById(R.id.tvAddMiniBio);
        TextView tvEditTravelPreferences = findViewById(R.id.tvEditTravelPreferences);

        // Vehicles section
        TextView tvAddVehicle = findViewById(R.id.tvAddVehicle);

        // Set click listeners
        tvEditProfilePicture.setOnClickListener(v -> showToast("Edit Profile Picture clicked"));
        tvEditPersonalDetails.setOnClickListener(v -> showToast("Edit Personal Details clicked"));
        tvVerifyID.setOnClickListener(v -> showToast("Verify My ID clicked"));
        tvAddMiniBio.setOnClickListener(v -> showToast("Add Mini Bio clicked"));
        tvEditTravelPreferences.setOnClickListener(v -> showToast("Edit Travel Preferences clicked"));
        tvAddVehicle.setOnClickListener(v -> showToast("Add Vehicle clicked"));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
