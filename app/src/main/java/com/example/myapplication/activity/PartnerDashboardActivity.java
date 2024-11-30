package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.activity.AddCarActivity;

public class PartnerDashboardActivity extends AppCompatActivity {

    private Button tabEarning, tabMyCars, btnPayout, btnBooking, btnHome, btnSetting;
    private LinearLayout btnAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_dashboard);

        // Initialize views
        tabEarning = findViewById(R.id.tab_earning);
        tabMyCars = findViewById(R.id.tab_my_cars);
        btnAddCar = findViewById(R.id.btn_add_car);
        btnBooking = findViewById(R.id.btn_booking);
        btnHome = findViewById(R.id.btn_home);
        btnSetting = findViewById(R.id.btn_setting);

        // Tab click listeners
        tabEarning.setOnClickListener(v -> {
            tabEarning.setBackgroundResource(R.drawable.tab_active);
            tabMyCars.setBackgroundResource(R.drawable.tab_inactive);
            Toast.makeText(this, "Earning Tab Selected", Toast.LENGTH_SHORT).show();
        });

        tabMyCars.setOnClickListener(v -> {
            tabMyCars.setBackgroundResource(R.drawable.tab_active);
            tabEarning.setBackgroundResource(R.drawable.tab_inactive);
            Toast.makeText(this, "My Cars Tab Selected", Toast.LENGTH_SHORT).show();
        });

        // Button click listeners

        btnAddCar.setOnClickListener(v -> {
            Intent intent = new Intent(PartnerDashboardActivity.this, AddCarActivity.class);
            startActivity(intent);
        });

        btnBooking.setOnClickListener(v ->
                Toast.makeText(this, "Booking Button Clicked", Toast.LENGTH_SHORT).show());

        btnHome.setOnClickListener(v ->
                Toast.makeText(this, "Home Button Clicked", Toast.LENGTH_SHORT).show());

        btnSetting.setOnClickListener(v ->
                Toast.makeText(this, "Settings Button Clicked", Toast.LENGTH_SHORT).show());
    }
}
