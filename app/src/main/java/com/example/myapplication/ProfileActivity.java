package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
public class ProfileActivity extends AppCompatActivity {

    private TextView profileName, profilePhone;
    private ImageView profileImage;
    private TextView editProfile, myBooking, notification, languages, settings, termsConditions, customerSupport, rateUs;
    private BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Get the passed data from the Intent
        String email = getIntent().getStringExtra("user_email");
        String fullName = getIntent().getStringExtra("user_fullName");
        // Initialize views
        profileName = findViewById(R.id.profile_name);
        profilePhone = findViewById(R.id.profile_phone);
        profileImage = findViewById(R.id.profile_image);
        editProfile = findViewById(R.id.edit_profile);
        myBooking = findViewById(R.id.my_booking);
        notification = findViewById(R.id.notification);
        languages = findViewById(R.id.languages);
        settings = findViewById(R.id.settings);
        termsConditions = findViewById(R.id.terms_conditions);
        customerSupport = findViewById(R.id.customer_support);
        rateUs = findViewById(R.id.rate_us);
        bottomNavView = findViewById(R.id.bottomNavView);

        // Set up profile info
        if (fullName != null) {
            profileName.setText(fullName); // Set the user's full name
        }else{
            profileName.setText("Cameron Williamson");
        }

        profilePhone.setText("(219) 555-0114");
        profileImage.setImageResource(R.drawable.profile); // Replace with actual image resource if needed

        // Set up onClickListeners for options
        editProfile.setOnClickListener(v -> {
            // Handle Edit Profile click
            // Intent to edit profile activity
        });

        myBooking.setOnClickListener(v -> {
            // Handle My Booking click
            // Intent to My Booking activity
        });

        notification.setOnClickListener(v -> {
            // Handle Notifications click
            // Intent to Notifications activity
        });

        languages.setOnClickListener(v -> {
            // Handle Languages click
            // Intent to Languages activity
        });

        settings.setOnClickListener(v -> {
            // Handle Settings click
            // Intent to Settings activity
        });

        termsConditions.setOnClickListener(v -> {
            // Handle Terms and Conditions click
            // Intent to Terms and Conditions activity
        });

        customerSupport.setOnClickListener(v -> {
            // Handle Customer Support click
            // Intent to Customer Support activity
        });

        rateUs.setOnClickListener(v -> {
            // Handle Rate Us click
            // Intent to Rate Us activity
        });

        // Set up Bottom Navigation
        bottomNavView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_search) {
                startActivity(new Intent(ProfileActivity.this, DashboardActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_publish) {
                startActivity(new Intent(ProfileActivity.this, PublishActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_your_rides) {
                startActivity(new Intent(ProfileActivity.this, YourRidesActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_inbox) {
                startActivity(new Intent(ProfileActivity.this, InboxActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                return true;
            }
            return false;

    });

    }
}
