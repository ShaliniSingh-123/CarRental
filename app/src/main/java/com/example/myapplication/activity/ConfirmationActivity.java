package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.BookingSuccessActivity;
import com.example.myapplication.R;

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
            Intent intent = new Intent(ConfirmationActivity.this, BookingSuccessActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
