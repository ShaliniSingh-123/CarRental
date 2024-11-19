package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class CarDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details); // Ensure this matches your layout file

        // Back button functionality
        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Navigate back to the previous screen
            }
        });

        // "Book Now" button functionality
        Button bookNowButton = findViewById(R.id.book_now_button); // Ensure this ID matches your layout file
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to BookingInformationActivity
                Intent intent = new Intent(CarDetailsActivity.this, BookingInformationActivity.class);
                startActivity(intent);
            }
        });
    }
}
