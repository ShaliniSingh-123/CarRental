package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    private Button lowCostButton, normalCostButton, searchCarButton;
    private EditText searchLocationEditText;
    private TextView tripStartTextView, tripEndTextView;
    private CheckBox deliveryPickupCheckBox;
    private int year, month, day;
    private String tripStartDate, tripEndDate;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView ayaRentalStoriesTextView = findViewById(R.id.ayaRentalStoriesTextView);

        // Create the SpannableString with the full text
        String text = "AYA Rental Stories";
        SpannableString spannableString = new SpannableString(text);

        // Find the start and end index of the word "Stories"
        int startIndex = text.indexOf("Stories");
        int endIndex = startIndex + "Stories".length();

        // Set the color of "Stories" to green
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#1E824C")), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the spannable text to the TextView
        ayaRentalStoriesTextView.setText(spannableString);
        // Initialize Buttons and UI components
        lowCostButton = findViewById(R.id.lowCostButton);
        normalCostButton = findViewById(R.id.normalCostButton);
        searchCarButton = findViewById(R.id.search_car_button);

        searchLocationEditText = findViewById(R.id.search_location);
        tripStartTextView = findViewById(R.id.trip_start_date);
        tripEndTextView = findViewById(R.id.trip_end_date);
//        deliveryPickupCheckBox = findViewById(R.id.delivery_pickup_checkbox);

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

    // Show DatePickerDialog and pass the selected date to the callback
    private void showDatePickerDialog(OnDateSelectedListener listener) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(DashboardActivity.this,
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
            // Stay in current DashboardActivity
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

    // Functional interface for handling date selection
    private interface OnDateSelectedListener {
        void onDateSelected(String date);
    }
}
