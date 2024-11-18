package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CarDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        // Set click listener for the back button
        findViewById(R.id.backButton).setOnClickListener(v -> {
            // Close the current activity and return to the previous one
            finish();
        });

        // Set click listener for the book button
        findViewById(R.id.bookButton).setOnClickListener(v ->
                Toast.makeText(this, "Car booked successfully!", Toast.LENGTH_SHORT).show()
        );
    }
}
