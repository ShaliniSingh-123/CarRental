package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);

        // Initialize views
        TextView bookingStatus = findViewById(R.id.bookingStatus);
        TextView carName = findViewById(R.id.carName);
        TextView pickupLocation = findViewById(R.id.pickupLocation);
        TextView tripDates = findViewById(R.id.tripDates);
        TextView rentalFees = findViewById(R.id.rentalFees);
        TextView totalFees = findViewById(R.id.totalFees);
        Button backToHomeBtn = findViewById(R.id.backToHomeBtn);

        // Set values for each field dynamically
        bookingStatus.setText("Booked Successfully");
        carName.setText("Mercedes Benz w176");
        pickupLocation.setText("Pick-up and Return: \nWashington Manchester - Same location");
        tripDates.setText("Trip Dates:\n Thu 15 April, 11:00 am - Sat 17 April, 6:00 pm");
        rentalFees.setText("One day rent: $200\nTotal of 3 days: $600");
        totalFees.setText("Total Fees: $600.00");

        // Button to go back to home
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the main screen (Home)
                Intent intent = new Intent(BookingSuccessActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
