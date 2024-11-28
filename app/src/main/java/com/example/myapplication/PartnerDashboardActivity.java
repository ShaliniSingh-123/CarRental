package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PartnerDashboardActivity extends AppCompatActivity {

    private Button tabEarning, tabMyCars, btnPayout, btnAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_dashboard);

        // Initialize views
        tabEarning = findViewById(R.id.tab_earning);
        tabMyCars = findViewById(R.id.tab_my_cars);
        btnPayout = findViewById(R.id.btn_payout);
        btnAddCar = findViewById(R.id.btn_add_car);

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
        btnPayout.setOnClickListener(v ->
                Toast.makeText(this, "Payout Clicked", Toast.LENGTH_SHORT).show());

        btnAddCar.setOnClickListener(v ->
                Toast.makeText(this, "Add Your Car Clicked", Toast.LENGTH_SHORT).show());
    }
}
