package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class PartnerBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_booking);

        // Toolbar title and search icon setup
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        ImageView toolbarSearchIcon = findViewById(R.id.toolbarSearchIcon);

        toolbarTitle.setText("Booking");
        toolbarSearchIcon.setOnClickListener(v -> {
            // Implement search functionality here
        });

        // Setting dynamic booking data
        TextView startTripDate = findViewById(R.id.startTripDate1);
        TextView endTripDate = findViewById(R.id.endTripDate1);
        TextView carName = findViewById(R.id.carName1);
        TextView carDetails = findViewById(R.id.carDetails1);
        TextView carPrice = findViewById(R.id.carPrice1);

        startTripDate.setText("Start Trip - 16 May, 2024");
        endTripDate.setText("Trip End - 24 May, 2024");
        carName.setText("HONDA CITY");
        carDetails.setText("1.5 Honda White MT Titanium X");
        carPrice.setText("$24.59 / Week");
    }
}
