package com.example.myapplication;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    private Button lowCostButton, normalCostButton, searchCarButton;
    private EditText searchLocationEditText;
    private TextView tripStartTextView, tripEndTextView;
    private int year, month, day, hour, minute;
    private String tripStartDate, tripEndDate;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize UI components
        lowCostButton = findViewById(R.id.lowCostButton);
        normalCostButton = findViewById(R.id.normalCostButton);
        searchCarButton = findViewById(R.id.search_car_button);
        searchLocationEditText = findViewById(R.id.search_location);
        tripStartTextView = findViewById(R.id.trip_start_date);
        tripEndTextView = findViewById(R.id.trip_end_date);

        // Get current date and time
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        // Set default date and time for trip start and end
        tripStartDate = getFormattedDateTime(day, month, year, hour, minute);
        tripEndDate = getFormattedDateTime(day + 1, month, year, hour + 1, minute); // Default end date is next day

        tripStartTextView.setText(tripStartDate);
        tripEndTextView.setText(tripEndDate);

        // Set click listeners for trip start and end date pickers
        tripStartTextView.setOnClickListener(v -> showDateTimePickerDialog((dateTime) -> {
            tripStartDate = dateTime;
            tripStartTextView.setText(dateTime);
        }));

        tripEndTextView.setOnClickListener(v -> showDateTimePickerDialog((dateTime) -> {
            tripEndDate = dateTime;
            tripEndTextView.setText(dateTime);
        }));

        // Set search button click listener
        searchCarButton.setOnClickListener(v -> performSearch());

        // Set OnClickListeners for the buttons (Low and Normal cost)
        lowCostButton.setOnClickListener(v -> openCarSelection("low_cost"));
        normalCostButton.setOnClickListener(v -> openCarSelection("normal_cost"));

        // Initialize BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> navigateTo(item));

        // Initialize ViewPager2 for Image Slider
        ViewPager2 imageSlider = findViewById(R.id.imageSlider);

        // List of image resource IDs
        Integer[] images = {R.drawable.explore_ban1, R.drawable.explore_ban2, R.drawable.explore_ban3};
        ImageSliderAdapter adapter = new ImageSliderAdapter(this, Arrays.asList(images));

        // Set the adapter for the slider
        imageSlider.setAdapter(adapter);
    }

    // Show DatePickerDialog and TimePickerDialog
    private void showDateTimePickerDialog(OnDateTimeSelectedListener listener) {
        // Show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(DashboardActivity.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    // Set the selected date
                    String selectedDate = getFormattedDateTime(dayOfMonth, monthOfYear, year, hour, minute);

                    // Show TimePickerDialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(DashboardActivity.this,
                            (view1, hourOfDay, minute1) -> {
                                // Set the selected time
                                String dateTime = getFormattedDateTime(dayOfMonth, monthOfYear, year, hourOfDay, minute1);
                                listener.onDateTimeSelected(dateTime);
                            }, hour, minute, false);
                    timePickerDialog.show();

                }, year, month, day);
        datePickerDialog.show();
    }

    // Format date and time as "Nov 8, 4 PM"
    private String getFormattedDateTime(int day, int month, int year, int hour, int minute) {
        // Convert the date and time to the required format
        String monthName = getMonthName(month);
        String amPm = (hour < 12) ? "AM" : "PM";
        int hour12 = (hour > 12) ? hour - 12 : hour;
        if (hour12 == 0) hour12 = 12; // Handle midnight as 12 AM
        return String.format("%s %d, %d %d:%02d %s", monthName, day, year, hour12, minute, amPm);
    }

    // Get month name from month number
    private String getMonthName(int month) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return months[month];
    }

    // Perform search validation and logic
    private void performSearch() {
        String searchLocation = searchLocationEditText.getText().toString();
        if (searchLocation.isEmpty()) {
            Toast.makeText(this, "Please enter a location to search for cars", Toast.LENGTH_SHORT).show();
            return;
        }

        // Display search details
        String searchDetails = "Searching for cars at " + searchLocation +
                "\nTrip Start: " + tripStartDate +
                "\nTrip End: " + tripEndDate;
        Toast.makeText(this, searchDetails, Toast.LENGTH_LONG).show();
    }

    // Open Car Selection Activity
    private void openCarSelection(String costType) {
        Intent intent = new Intent(DashboardActivity.this, CategorySelectionActivity.class);
        intent.putExtra("COST_TYPE", costType); // Passing the cost type to the next activity
        startActivity(intent);
    }

    // Handle bottom navigation item selection
    private boolean navigateTo(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_search) {
            return true;
        } else if (id == R.id.nav_publish) {
            startActivity(new Intent(DashboardActivity.this, PublishActivity.class));
            return true;
        } else if (id == R.id.nav_your_rides) {
            startActivity(new Intent(DashboardActivity.this, YourRidesActivity.class));
            return true;
        } else if (id == R.id.nav_inbox) {
            startActivity(new Intent(DashboardActivity.this, InboxActivity.class));
            return true;
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
            return true;
        }
        return false;
    }

    // Functional interface for handling date and time selection
    private interface OnDateTimeSelectedListener {
        void onDateTimeSelected(String dateTime);
    }
}
