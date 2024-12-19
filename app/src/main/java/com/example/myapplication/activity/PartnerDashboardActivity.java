package com.example.myapplication.activity;
import retrofit2.Callback;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.dialog.*;
import com.example.myapplication.models.response.Car;
import com.example.myapplication.models.response.GetCarByUserResponse;
import com.example.myapplication.network.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.network.ApiService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class PartnerDashboardActivity extends AppCompatActivity {

    private Button tabEarning, tabMyCars;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_dashboard);

        tabEarning = findViewById(R.id.tab_earning);
        tabMyCars = findViewById(R.id.tab_my_cars);

        // Initialize BottomNavigationView
        bottomNav = findViewById(R.id.bottomNavView);

        // Set default fragment (EarningFragment)
        loadFragment(new EarningFragment());

        // Set click listener for "Earning" tab
        tabEarning.setOnClickListener(v -> loadFragment(new EarningFragment()));

        // Set click listener for "My Cars" tab
        tabMyCars.setOnClickListener(v -> {
            // Load the MyCarsFragment
            loadFragment(new MyCarsFragment());
            // Call the method to fetch cars when My Cars button is clicked
            fetchCarsForUser();
        });
        // BottomNavigationView listener
        bottomNav.setSelectedItemId(R.id.nav_home);
        bottomNav.setOnNavigationItemSelectedListener(this::navigateTo);

        // Handle intent that switches to My Cars tab
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("tab") && "MyCars".equals(intent.getStringExtra("tab"))) {
            loadFragment(new MyCarsFragment());
            fetchCarsForUser();
        }
    }

    // Method to navigate based on BottomNavigationView item selection
    private boolean navigateTo(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            loadFragment(new EarningFragment());  // Load the appropriate fragment
            return true;
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(PartnerDashboardActivity.this, PartnerProfileActivity.class));
            return true;
        } else if (id == R.id.nav_booking) {
            startActivity(new Intent(PartnerDashboardActivity.this, PartnerBookingActivity.class));
            return true;
        }
        return false;
    }

    // Method to load the given fragment into the container
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)  // Make sure the container's ID is correct
                .commit();
    }

    // Method to fetch cars by user ID
    private void fetchCarsForUser() {
        ApiService apiService = RetrofitClient.getRetrofitInstance(this).create(ApiService.class);

        apiService.getCarsByUserId().enqueue(new Callback<GetCarByUserResponse>() {
            @Override
            public void onResponse(Call<GetCarByUserResponse> call, Response<GetCarByUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Debug raw response
                    Log.d("CarsResponse", "Raw response: " + response.toString());

                    GetCarByUserResponse carResponse = response.body();

                    // Check the main response object
                    if (carResponse != null) {
                        Log.d("CarsResponse", "Full Response: " + carResponse.toString());
                    } else {
                        Log.e("CarsResponse", "Response body is null.");
                    }

                    Gson gson = new Gson();
                    String carsJson = gson.toJson(carResponse.getCars());
                    Log.d("CarsResponse", "Full Response (JSON): " + carsJson);
                    // Check cars list
                    List<Car> cars = carResponse.getCars();
                    if (cars != null && !cars.isEmpty()) {
                        Log.d("CarsResponse", "Cars List Size: " + cars.size());
                        for (Car car : cars) {
                            // Log individual car details
                            String carJson = gson.toJson(car);
                            Log.d("CarDetails", carJson);
                        }

                        // Update fragment with the list
                        MyCarsFragment myCarsFragment = (MyCarsFragment) getSupportFragmentManager()
                                .findFragmentByTag(MyCarsFragment.class.getSimpleName());
                        if (myCarsFragment != null) {
                            myCarsFragment.updateCarList(cars);
                        }
                    } else {
                        Log.d("CarsResponse", "Cars list is empty or null.");
                        Toast.makeText(PartnerDashboardActivity.this, "No cars found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String errorMessage = response.errorBody() != null ? response.errorBody().toString() : "Unknown error";
                    Log.e("CarsResponse", "Error: " + errorMessage);
                    Toast.makeText(PartnerDashboardActivity.this, "Failed to fetch cars", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetCarByUserResponse> call, Throwable t) {
                Log.e("CarsResponse", "API call failed: " + t.getMessage());
                Toast.makeText(PartnerDashboardActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
