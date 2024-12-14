package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DriverDashboardActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dashboard);


        // Initialize BottomNavigationView
        bottomNav = findViewById(R.id.bottomNavView);

        // BottomNavigationView listener
        bottomNav.setSelectedItemId(R.id.nav_home);
        bottomNav.setOnNavigationItemSelectedListener(this::navigateTo);
    }

    // Method to navigate based on BottomNavigationView item selection
    private boolean navigateTo(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(DriverDashboardActivity.this, DriverDashboardActivity.class));
            return true;
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(DriverDashboardActivity.this, DriverProfileActivity.class));
            return true;
        }
//        else if (id == R.id.nav_booking) {
//            startActivity(new Intent(DriverDashboardActivity.this, DriverBookingActivity.class));
//            return true;
//        }
        return false;
    }

}
