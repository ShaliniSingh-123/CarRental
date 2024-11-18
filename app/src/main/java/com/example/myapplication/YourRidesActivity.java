package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class YourRidesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_rides);

        // Initialize BottomNavigationView and set a listener
        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavView);

        // Set default selected item to the current activity
        bottomNavView.setSelectedItemId(R.id.nav_your_rides);

        // Set up navigation item selected listener
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_search) {
                    startActivity(new Intent(YourRidesActivity.this, DashboardActivity.class));
                    return true;
                } else if (id == R.id.nav_publish) {
                    // Do nothing as we are already in PublishActivity, keeping it highlighted
                    return true;
                } else if (id == R.id.nav_your_rides) {
                    startActivity(new Intent(YourRidesActivity.this, YourRidesActivity.class));
                    return true;
                } else if (id == R.id.nav_inbox) {
                    startActivity(new Intent(YourRidesActivity.this, InboxActivity.class));
                    return true;
                } else if (id == R.id.nav_profile) {
                    startActivity(new Intent(YourRidesActivity.this, ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
