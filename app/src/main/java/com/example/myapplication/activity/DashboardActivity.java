package com.example.myapplication.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.myapplication.R;
import com.example.myapplication.adapter.AyaStoriesAdapter;
import com.example.myapplication.adapter.ImageSliderAdapter;
import com.example.myapplication.models.response.Car;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;

public class DashboardActivity extends AppCompatActivity {

    private Button lowCostButton, normalCostButton, searchCarButton;
    private Spinner pickupSpinner, dropoffSpinner;

    private TextView tripStartDateTextView, tripStartTimeTextView, dropOffDateTextView, dropOffTimeTextView;
    private int year, month, day, hour, minute;

    // Flags to identify which TextView to update
    private boolean isPickupDate = true;
    private boolean isPickupTime = true;

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
        tripStartDateTextView = findViewById(R.id.trip_start_date);
        tripStartTimeTextView = findViewById(R.id.trip_start_time);
        dropOffDateTextView = findViewById(R.id.trip_end_date);
        dropOffTimeTextView = findViewById(R.id.trip_end_time);
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

        // Set default date and time
        tripStartDateTextView.setText(getFormattedDate(day, month, year));
        tripStartTimeTextView.setText(getFormattedTime(hour, minute));
        dropOffDateTextView.setText(getFormattedDate(day, month, year));
        dropOffTimeTextView.setText(getFormattedTime(hour, minute));

        // Set Click listeners for Date and Time TextViews
        tripStartDateTextView.setOnClickListener(v -> {
            isPickupDate = true;
            showDatePickerDialog();
        });

        dropOffDateTextView.setOnClickListener(v -> {
            isPickupDate = false;
            showDatePickerDialog();
        });

        tripStartTimeTextView.setOnClickListener(v -> {
            isPickupTime = true;
            showTimePickerDialog();
        });

        dropOffTimeTextView.setOnClickListener(v -> {
            isPickupTime = false;
            showTimePickerDialog();
        });



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

    // Show DatePickerDialog
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            // Update the selected date
            year = year1;
            month = month1;
            day = dayOfMonth;

            // Update the correct TextView
            if (isPickupDate) {
                tripStartDateTextView.setText(getFormattedDate(day, month, year));
            } else {
                dropOffDateTextView.setText(getFormattedDate(day, month, year));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    // Show TimePickerDialog
    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
            // Update the selected time
            hour = hourOfDay;
            minute = minute1;

            // Update the correct TextView
            if (isPickupTime) {
                tripStartTimeTextView.setText(getFormattedTime(hour, minute));
            } else {
                dropOffTimeTextView.setText(getFormattedTime(hour, minute));
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
    // Format date and time

    // Perform search
    private void performSearch() {
        String pickupSpinnerValue = pickupSpinner.getSelectedItem().toString();
        String dropoffSpinnerValue = dropoffSpinner.getSelectedItem().toString();

        String searchDetails = "Searching for cars at " + pickupSpinnerValue +
                "\nTrip Start: " + tripStartDateTextView.getText() + " " + tripStartTimeTextView.getText() +
                "\nTrip End: " + dropOffDateTextView.getText() + " " + dropOffTimeTextView.getText();
        Toast.makeText(this, searchDetails, Toast.LENGTH_LONG).show();
    }

    // Format date in "Month Day, Year" format
    private String getFormattedDate(int day, int month, int year) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return String.format("%s %d, %d", months[month], day, year);
    }

    // Format time in "Hour:Minute AM/PM" format
    private String getFormattedTime(int hour, int minute) {
        String amPm = (hour < 12) ? "AM" : "PM";
        int hour12 = (hour % 12 == 0) ? 12 : (hour % 12);
        return String.format("%02d:%02d %s", hour12, minute, amPm);
    }

    // Open Car Selection Activity
    private void openCarSelection(String type) {
        ApiService apiService = RetrofitClient.getRetrofitInstance(this).create(ApiService.class);
        apiService.getCarsByCost(type).enqueue(new retrofit2.Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, retrofit2.Response<List<Car>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Car> carList = response.body();
                    Log.d("CarList", "Fetched cars: " + carList.toString());

                    // Pass the car list to the next activity
                    Intent intent = new Intent(DashboardActivity.this, CategorySelectionActivity.class);
                    intent.putParcelableArrayListExtra("carList", new ArrayList<>(carList));
                    startActivity(intent);
                } else {
                    Toast.makeText(DashboardActivity.this, "Failed to fetch cars.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "ErrorAs: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("CarSelectionError", "Error: " + t.getMessage());

            }
        });
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
