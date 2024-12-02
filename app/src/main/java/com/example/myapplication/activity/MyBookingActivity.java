package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BookingAdapter;
import com.example.myapplication.models.request.BookingRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MyBookingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBookings;
    private BookingAdapter bookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        ImageView backButton = findViewById(R.id.backButton);
        recyclerViewBookings = findViewById(R.id.recyclerViewBookings);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        // Set up RecyclerView
        recyclerViewBookings.setLayoutManager(new LinearLayoutManager(this));
        bookingAdapter = new BookingAdapter(getSampleBookings());
        recyclerViewBookings.setAdapter(bookingAdapter);

        // Back button click listener
        backButton.setOnClickListener(view -> finish());

        // BottomNavigationView listener
        bottomNavigationView.setSelectedItemId(R.id.nav_booking);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::navigateTo);
    }

    private List<BookingRequest> getSampleBookings() {
        List<BookingRequest> bookings = new ArrayList<>();
        bookings.add(new BookingRequest("Mercedes-Benz", "CZ2215", "Trip Start", "Trip End", "$660", "2464 Royal Ln. Mesa, New Jersey 45463", R.drawable.sedan));
        bookings.add(new BookingRequest("Mercedes-Benz", "CZ2215", "Trip Start", "Trip End", "$660", "2464 Royal Ln. Mesa, New Jersey 45463", R.drawable.hatchback));
        return bookings;
    }
    private boolean navigateTo(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(MyBookingActivity.this, DashboardActivity.class));
            return true;
        }  else if (id == R.id.nav_profile) {
            startActivity(new Intent(MyBookingActivity.this, ProfileActivity.class));
            return true;
        }
        else if (item.getItemId() == R.id.nav_booking){
            startActivity(new Intent(MyBookingActivity.this, MyBookingActivity.class));
            return true;
        }

        return false;
    }
}
