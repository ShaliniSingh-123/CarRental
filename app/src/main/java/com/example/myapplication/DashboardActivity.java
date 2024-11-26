package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private Button lowCostButton, normalCostButton, searchCarButton;
    private Spinner pickupSpinner, dropoffSpinner;
    private TextView tripStartTextView, tripEndTextView;
    private int year, month, day, hour, minute;
    private String tripStartDate, tripEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize UI components
        lowCostButton = findViewById(R.id.lowCostButton);
        normalCostButton = findViewById(R.id.normalCostButton);
        searchCarButton = findViewById(R.id.search_car_button);
        pickupSpinner = findViewById(R.id.pickupSpinner);
        dropoffSpinner = findViewById(R.id.dropoffSpinner);
        tripStartTextView = findViewById(R.id.trip_start_date);
        tripEndTextView = findViewById(R.id.trip_end_date);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        ViewPager2 viewPager2 = findViewById(R.id.ayaStoriesSlider);
        TabLayout tabLayout = findViewById(R.id.sliderDots);
        LinearLayout commentsContainer = findViewById(R.id.customerCommentsContainer);

        // Initialize Spinners with data
        String[] locations = {"CT – Cape Town Airport", "Cape Town - City", "Johannesburg - City"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        pickupSpinner.setAdapter(adapter2);
        dropoffSpinner.setAdapter(adapter2);

        // Setup Slider
        List<Integer> storyImages = Arrays.asList(R.drawable.sedan, R.drawable.suv, R.drawable.hatchback);
        AyaStoriesAdapter sliderAdapter = new AyaStoriesAdapter(this, storyImages);
        viewPager2.setAdapter(sliderAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {}).attach();

        // Add Customer Comments
        addCustomerComments(commentsContainer);

        // Set current date and time
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        // Set default trip start and end dates
        tripStartDate = getFormattedDateTime(day, month, year, hour, minute);
        tripEndDate = getFormattedDateTime(day + 1, month, year, hour + 1, minute);

        tripStartTextView.setText(tripStartDate);
        tripEndTextView.setText(tripEndDate);

        // Set Click listeners for date and time
        tripStartTextView.setOnClickListener(v -> showDatePickerDialog(tripStartTextView));
        tripEndTextView.setOnClickListener(v -> showDatePickerDialog(tripEndTextView));


        // Button listeners
        searchCarButton.setOnClickListener(v -> performSearch());
        lowCostButton.setOnClickListener(v -> openCarSelection("low_cost"));
        normalCostButton.setOnClickListener(v -> openCarSelection("normal_cost"));

        // BottomNavigationView listener
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::navigateTo);

        // Initialize ViewPager2 for Image Slider
        ViewPager2 imageSlider = findViewById(R.id.imageSlider);

        // List of image resource IDs
        Integer[] images = {R.drawable.explore_ban1, R.drawable.explore_ban2, R.drawable.explore_ban3};
        ImageSliderAdapter adapter = new ImageSliderAdapter(this, Arrays.asList(images));

        // Set the adapter for the slider
        imageSlider.setAdapter(adapter);
    }

    // Add customer comments dynamically
    private void addCustomerComments(LinearLayout container) {
        List<CustomerComment> comments = getCustomerComments();
        for (CustomerComment comment : comments) {
            TextView nameTextView = new TextView(this);
            nameTextView.setText(comment.getName());
            nameTextView.setTextSize(16);
            nameTextView.setTextColor(getResources().getColor(android.R.color.black));
            nameTextView.setPadding(0, 8, 0, 4);

            TextView commentTextView = new TextView(this);
            commentTextView.setText(comment.getComment());
            commentTextView.setTextSize(14);
            commentTextView.setTextColor(getResources().getColor(android.R.color.darker_gray));
            commentTextView.setPadding(0, 0, 0, 8);

            container.addView(nameTextView);
            container.addView(commentTextView);
        }
    }

    // Example method to get customer comments
    private List<CustomerComment> getCustomerComments() {
        List<CustomerComment> comments = new ArrayList<>();
        comments.add(new CustomerComment("John Doe", "Amazing experience! Highly recommend."));
        comments.add(new CustomerComment("Jane Smith", "Great service and friendly staff."));
        comments.add(new CustomerComment("Michael Brown", "Affordable and reliable car rental service."));
        return comments;
    }

    // Show DatePickerDialog and TimePickerDialog
    // Method to show Date Picker Dialog
    private void showDatePickerDialog(TextView dateTextView) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            // After selecting date, show time picker
            showTimePickerDialog((view1, hourOfDay, minute) -> {
                String selectedDate = getFormattedDateTime(dayOfMonth, month1, year1, hourOfDay, minute);
                dateTextView.setText(selectedDate);
            });
        }, year, month, day);
        datePickerDialog.show();
    }


    // Method to show Time Picker Dialog
    private void showTimePickerDialog(TimePickerDialog.OnTimeSetListener listener) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, listener, hour, minute, false);
        timePickerDialog.show();
    }

    // Format date and time
    private String getFormattedDateTime(int day, int month, int year, int hour, int minute) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String amPm = (hour < 12) ? "AM" : "PM";
        int hour12 = (hour % 12 == 0) ? 12 : (hour % 12);
        return String.format("%s %d, %d %d:%02d %s", months[month], day, year, hour12, minute, amPm);
    }

    // Perform search
    private void performSearch() {
        String pickupSpinnerValue = pickupSpinner.getSelectedItem().toString();
        String dropoffSpinnerValue = dropoffSpinner.getSelectedItem().toString();

        String searchDetails = "Searching for cars at " + pickupSpinnerValue +
                "\nTrip Start: " + tripStartDate +
                "\nTrip End: " + tripEndDate;
        Toast.makeText(this, searchDetails, Toast.LENGTH_LONG).show();
    }

    // Open Car Selection Activity
    private void openCarSelection(String costType) {
        Intent intent = new Intent(this, CategorySelectionActivity.class);
        intent.putExtra("COST_TYPE", costType);
        startActivity(intent);
    }

    // Handle bottom navigation
    private boolean navigateTo(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            return true;
        }  else if (id == R.id.nav_profile) {
            startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
            return true;
        }
        else if (item.getItemId() == R.id.nav_booking){
            startActivity(new Intent(DashboardActivity.this, MyBookingActivity.class));
            return true;
        }
        else if (item.getItemId() == R.id.nav_host_car){
            startActivity(new Intent(DashboardActivity.this, HostCarActivity.class));
            return true;
        }
        return false;
    }

    // CustomerComment class
    static class CustomerComment {
        private final String name;
        private final String comment;

        public CustomerComment(String name, String comment) {
            this.name = name;
            this.comment = comment;
        }

        public String getName() {
            return name;
        }

        public String getComment() {
            return comment;
        }
    }

    // Interface for date-time selection callback
    public interface OnDateTimeSelectedListener {
        void onDateTimeSelected(String dateTime);
    }
}
