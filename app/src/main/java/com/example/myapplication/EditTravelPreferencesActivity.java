package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditTravelPreferencesActivity extends AppCompatActivity {

    private Spinner transportModeSpinner, travelTimeSpinner;
    private Button savePreferencesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_preferences);

        transportModeSpinner = findViewById(R.id.transportModeSpinner);
        travelTimeSpinner = findViewById(R.id.travelTimeSpinner);
        savePreferencesButton = findViewById(R.id.savePreferencesButton);

        // Sample data for spinners
        ArrayAdapter<String> transportAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Car", "Bike", "Public Transport"});
        transportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transportModeSpinner.setAdapter(transportAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Morning", "Afternoon", "Evening"});
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelTimeSpinner.setAdapter(timeAdapter);

        savePreferencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save preferences (e.g., save to database or shared preferences)
                Toast.makeText(EditTravelPreferencesActivity.this, "Preferences saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
