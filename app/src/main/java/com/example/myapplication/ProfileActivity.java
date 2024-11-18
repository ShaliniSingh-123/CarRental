package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView tvProfileName, tvProfileEmail, tvProfilePhone, tvEditProfilePicture;
    private TextView tvVerifyID, tvAddMiniBio, tvEditTravelPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize database helper and UI components
        dbHelper = new DatabaseHelper(this);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        tvProfilePhone = findViewById(R.id.tvProfilePhone);
        tvEditProfilePicture = findViewById(R.id.tvEditProfilePicture);

        // Initialize new UI components
        tvVerifyID = findViewById(R.id.tvVerifyID);
        tvAddMiniBio = findViewById(R.id.tvAddMiniBio);
        tvEditTravelPreferences = findViewById(R.id.tvEditTravelPreferences);

        // Retrieve the email of the logged-in user from Intent
        String userEmail = getIntent().getStringExtra("user_email");

        // Fetch and display user details
        displayUserProfile(userEmail);

        // Set up bottom navigation
        setupBottomNavigation();

        // Set the click listener for editing profile picture
        tvEditProfilePicture.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, UploadProfilePictureActivity.class);
            startActivity(intent);
        });

        // Set click listener for "Verify my ID"
        tvVerifyID.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, VerifyMyIdActivity.class);
            startActivity(intent);
        });

        // Set click listener for "Add a mini bio"
        tvAddMiniBio.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AddMiniBioActivity.class);
            startActivity(intent);
        });

        // Set click listener for "Edit travel preferences"
        tvEditTravelPreferences.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, TravelPreferencesActivity.class);
            startActivity(intent);
        });

        // Set the click listener for editing personal details
        TextView tvEditPersonalDetails = findViewById(R.id.tvEditPersonalDetails);
        tvEditPersonalDetails.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, PersonalDetailsActivity.class);
            startActivity(intent);
        });
    }

    private void displayUserProfile(String email) {
        Cursor cursor = dbHelper.getUserDetails(email);
        if (cursor != null && cursor.moveToFirst()) {
            String firstName = cursor.getString(cursor.getColumnIndex("first_name"));
            String lastName = cursor.getString(cursor.getColumnIndex("last_name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));

            tvProfileName.setText(firstName + " " + lastName);
            tvProfileEmail.setText(email);
            tvProfilePhone.setText(phone);

            cursor.close();
        } else {
            showToast("User details not found.");
        }
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_search) {
                    startActivity(new Intent(ProfileActivity.this, DashboardActivity.class));
                    return true;
                } else if (id == R.id.nav_publish) {
                    startActivity(new Intent(ProfileActivity.this, PublishActivity.class));
                    return true;
                } else if (id == R.id.nav_your_rides) {
                    startActivity(new Intent(ProfileActivity.this, YourRidesActivity.class));
                    return true;
                } else if (id == R.id.nav_inbox) {
                    startActivity(new Intent(ProfileActivity.this, InboxActivity.class));
                    return true;
                } else if (id == R.id.nav_profile) {
                    return true;
                }
                return false;
            }
        });
        bottomNavView.setSelectedItemId(R.id.nav_profile);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
