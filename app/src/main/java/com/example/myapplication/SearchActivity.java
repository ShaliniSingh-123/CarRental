package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private Spinner pickupSpinner, dropoffSpinner;
    private DatePicker pickupDatePicker, dropoffDatePicker;
    private TimePicker pickupTimePicker, dropoffTimePicker;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize UI elements
        pickupSpinner = findViewById(R.id.pickupSpinner);
        dropoffSpinner = findViewById(R.id.dropoffSpinner);
        pickupDatePicker = findViewById(R.id.pickupDatePicker);
        dropoffDatePicker = findViewById(R.id.dropoffDatePicker);
        pickupTimePicker = findViewById(R.id.pickupTimePicker);
        dropoffTimePicker = findViewById(R.id.dropoffTimePicker);
        searchButton = findViewById(R.id.searchButton);

        // Populate Spinners with location data
        String[] locations = {"CT – Cape Town Airport", "Cape Town - City", "Johannesburg - City"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        pickupSpinner.setAdapter(adapter);
        dropoffSpinner.setAdapter(adapter);

        // Set up the search button
        searchButton.setOnClickListener(v -> {
            String pickupLocation = pickupSpinner.getSelectedItem().toString();
            String dropoffLocation = dropoffSpinner.getSelectedItem().toString();

            int pickupDay = pickupDatePicker.getDayOfMonth();
            int pickupMonth = pickupDatePicker.getMonth() + 1;
            int pickupYear = pickupDatePicker.getYear();
            int pickupHour = pickupTimePicker.getHour();
            int pickupMinute = pickupTimePicker.getMinute();

            int dropoffDay = dropoffDatePicker.getDayOfMonth();
            int dropoffMonth = dropoffDatePicker.getMonth() + 1;
            int dropoffYear = dropoffDatePicker.getYear();
            int dropoffHour = dropoffTimePicker.getHour();
            int dropoffMinute = dropoffTimePicker.getMinute();

            Toast.makeText(SearchActivity.this,
                    "Pickup: " + pickupLocation + ", Dropoff: " + dropoffLocation +
                            "\nPickup Date/Time: " + pickupDay + "/" + pickupMonth + "/" + pickupYear +
                            " " + pickupHour + ":" + pickupMinute +
                            "\nDropoff Date/Time: " + dropoffDay + "/" + dropoffMonth + "/" + dropoffYear +
                            " " + dropoffHour + ":" + dropoffMinute,
                    Toast.LENGTH_LONG).show();
        });
    }
}
