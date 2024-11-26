package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageView backButton = findViewById(R.id.backButton);
        Switch switchPushNotification = findViewById(R.id.switchPushNotification);
        Switch switchAppUpdate = findViewById(R.id.switchAppUpdate);
        Switch switchDarkMode = findViewById(R.id.switchDarkMode);

        // Back button click listener
        backButton.setOnClickListener(view -> finish());

        // Push notification toggle
        switchPushNotification.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "Push Notifications Enabled" : "Push Notifications Disabled", Toast.LENGTH_SHORT).show());

        // Application update toggle
        switchAppUpdate.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "App Updates Enabled" : "App Updates Disabled", Toast.LENGTH_SHORT).show());

        // Dark mode toggle
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "Dark Mode Enabled" : "Dark Mode Disabled", Toast.LENGTH_SHORT).show());
    }
}
