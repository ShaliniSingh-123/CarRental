package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import  com.example.myapplication.R;
public class DriverProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView profileName, profilePhone;
    private TextView editProfile, notifications,request,wallet,history, uploadLicense, paymentInfo, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile); // Ensure your XML layout file is named activity_profile.xml

        // Initialize views
        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_name);
        profilePhone = findViewById(R.id.profile_phone);

        editProfile = findViewById(R.id.editProfile);
        notifications = findViewById(R.id.notifications);
        uploadLicense = findViewById(R.id.uploadLicense);
        paymentInfo = findViewById(R.id.paymentInfo);
        request = findViewById(R.id.request);
        wallet = findViewById(R.id.wallet);
        history = findViewById(R.id.history);

        logout = findViewById(R.id.logout);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);

        // Set initial profile details
        setProfileDetails();

        // Handle clicks on menu items
        editProfile.setOnClickListener(v -> navigateTo(EditProfileActivity.class));
//        vehicleDetails.setOnClickListener(v -> navigateTo(VehicleDetailsActivity.class));
//        uploadLicense.setOnClickListener(v -> navigateTo(UploadLicenseActivity.class));
//        paymentInfo.setOnClickListener(v -> navigateTo(PaymentInfoActivity.class));
        logout.setOnClickListener(v -> logoutUser());

        // Handle Bottom Navigation clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(DriverProfileActivity.this, DriverDashboardActivity.class));
                return true;
            } else if (itemId == R.id.nav_settings) {
                startActivity(new Intent(DriverProfileActivity.this, DriverProfileActivity.class));
                return true;
            }
            return false;
        });

    }

    private void setProfileDetails() {
        // Example data: replace with actual user data
        profileName.setText("Cameron Williamson");
        profilePhone.setText("(219) 555-0114");
        profileImage.setImageResource(R.drawable.profile); // Replace with actual profile image loading logic
    }

    private void navigateTo(Class<?> activityClass) {
        Intent intent = new Intent(DriverProfileActivity.this, activityClass);
        startActivity(intent);
    }

    private void logoutUser() {
        // Handle logout logic, e.g., clearing user session, redirecting to login
        startActivity(new Intent(DriverProfileActivity.this, LoginActivity.class));
        finish();
    }
}
