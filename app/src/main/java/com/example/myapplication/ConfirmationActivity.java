package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Back button
        findViewById(R.id.backIcon).setOnClickListener(v -> finish());

        // Confirm button
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> {
            Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
            // Add booking confirmation logic here
        });
    }
}
