package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.response.Car;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorySelectionActivity extends AppCompatActivity {

    private LinearLayout categoryContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        // Set up the back button
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Initialize the container where categories will be dynamically added
        categoryContainer = findViewById(R.id.categoryContainer);

        // Fetch categories from the server
        fetchCategories();
    }

    private void fetchCategories() {
        ApiService apiService = RetrofitClient.getRetrofitInstance(this).create(ApiService.class);

        apiService.getCarsByCost("all").enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Car> carList = response.body();
                    Log.d("CarList", "Fetched cars: " + carList.toString());

                    populateCategories(carList);
                } else {
                    Toast.makeText(CategorySelectionActivity.this, "Failed to fetch categories.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Toast.makeText(CategorySelectionActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateCategories(List<Car> carList) {
        for (Car car : carList) {
            addCategoryItem(car);
        }
    }

    private void addCategoryItem(Car car) {
        // Inflate a new car item layout
        View categoryItemView = LayoutInflater.from(this).inflate(R.layout.car_item, categoryContainer, false);

        // Set the car brand and model as the name
        TextView carNameTextView = categoryItemView.findViewById(R.id.carName);
        carNameTextView.setText(car.getBrand() + " " + car.getModel());

        // Set the first image from the list (if available) using Glide
        ImageView carImageView = categoryItemView.findViewById(R.id.carImage);
        if (car.getImages() != null && !car.getImages().isEmpty()) {
            Glide.with(this)
                    .load(car.getImages().get(0)) // Load the first image
                    .placeholder(R.drawable.default_car_image) // Fallback image
                    .into(carImageView);
        } else {
            carImageView.setImageResource(R.drawable.default_car_image); // Default image
        }

        // Set a click listener for each category item
        categoryItemView.setOnClickListener(v -> openSubCategorySelection(car.getCategory()));

        // Add the inflated view to the container
        categoryContainer.addView(categoryItemView);
    }


    private int getImageResourceForCategory(String categoryName) {
        switch (categoryName) {
            case "SUV":
                return R.drawable.suv;
            case "Sedan":
                return R.drawable.sedan;
            case "Hatchback":
                return R.drawable.hatchback;
            case "Convertible":
                return R.drawable.convertible;
            default:
                return R.drawable.default_car_image;
        }
    }

    private void openSubCategorySelection(String category) {
        Intent intent = new Intent(CategorySelectionActivity.this, SubCategorySelectionActivity.class);
        intent.putExtra("CATEGORY", category); // Pass the selected category to the next activity
        startActivity(intent);
    }
}
