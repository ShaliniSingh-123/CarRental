package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity {

    private EditText searchLocationEditText;
    private TextView tripStartTextView, tripEndTextView;
    private CheckBox deliveryPickupCheckBox;
    private Button searchCarButton;
    private int year, month, day;
    private String tripStartDate, tripEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize UI components
        searchLocationEditText = findViewById(R.id.search_location);
        tripStartTextView = findViewById(R.id.trip_start_date);
        tripEndTextView = findViewById(R.id.trip_end_date);
//        deliveryPickupCheckBox = findViewById(R.id.delivery_pickup_checkbox);
        searchCarButton = findViewById(R.id.search_car_button);

        // Get current date
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // Set default dates for trip start and end
        tripStartDate = day + "/" + (month + 1) + "/" + year;
        tripEndDate = (day + 1) + "/" + (month + 1) + "/" + year; // Default end date is next day

        tripStartTextView.setText(tripStartDate);
        tripEndTextView.setText(tripEndDate);

        // Set click listeners for trip start and end date pickers
        tripStartTextView.setOnClickListener(v -> showDatePickerDialog((date) -> {
            tripStartDate = date;
            tripStartTextView.setText(date);
        }));

        tripEndTextView.setOnClickListener(v -> showDatePickerDialog((date) -> {
            tripEndDate = date;
            tripEndTextView.setText(date);
        }));

        // Set search button click listener
        searchCarButton.setOnClickListener(v -> performSearch());

        // Initialize BottomNavigationView and set up the navigation
        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setSelectedItemId(R.id.nav_search);

        bottomNavView.setOnNavigationItemSelectedListener(item -> navigateTo(item));
    }

    // Show DatePickerDialog and pass the selected date to the callback
    private void showDatePickerDialog(OnDateSelectedListener listener) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    listener.onDateSelected(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    // Perform search validation and logic
    private void performSearch() {
        String searchLocation = searchLocationEditText.getText().toString();
        boolean isDeliveryPickupChecked = deliveryPickupCheckBox.isChecked();

        if (searchLocation.isEmpty()) {
            Toast.makeText(this, "Please enter a location to search for cars", Toast.LENGTH_SHORT).show();
            return;
        }

        // Display search details (You can replace this with an API call or other search logic)
        String searchDetails = "Searching for cars at " + searchLocation +
                "\nTrip Start: " + tripStartDate +
                "\nTrip End: " + tripEndDate +
                "\nDelivery Pickup: " + (isDeliveryPickupChecked ? "Yes" : "No");
        Toast.makeText(this, searchDetails, Toast.LENGTH_LONG).show();
    }

    // Handle bottom navigation item selection
    // Handle bottom navigation item selection with if-else logic
    private boolean navigateTo(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_search) {
            startActivity(new Intent(this, DashboardActivity.class));
            return true;
        } else if (id == R.id.nav_publish) {
            startActivity(new Intent(this, PublishActivity.class));
            return true;
        } else if (id == R.id.nav_your_rides) {
            startActivity(new Intent(this, YourRidesActivity.class));
            return true;
        } else if (id == R.id.nav_inbox) {
            startActivity(new Intent(this, InboxActivity.class));
            return true;
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }
        return false;
    }


    // Functional interface for handling date selection
    private interface OnDateSelectedListener {
        void onDateSelected(String date);
    }
}
