package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HostCarActivity extends AppCompatActivity {

    private EditText carModel, carMake, carYear, carPrice, carDescription;
    private Button uploadImageButton, hostCarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_car);

        carModel = findViewById(R.id.editCarModel);
        carMake = findViewById(R.id.editCarMake);
        carYear = findViewById(R.id.editCarYear);
        carPrice = findViewById(R.id.editCarPrice);
        carDescription = findViewById(R.id.editCarDescription);
        uploadImageButton = findViewById(R.id.buttonUploadImage);
        hostCarButton = findViewById(R.id.buttonHostCar);

        // Handle the "Host Car" button click
        hostCarButton.setOnClickListener(v -> {
            String model = carModel.getText().toString();
            String make = carMake.getText().toString();
            String year = carYear.getText().toString();
            String price = carPrice.getText().toString();
            String description = carDescription.getText().toString();

            // Add validation if needed

            // Here you would save this data to your backend or database
            saveCarDetails(model, make, year, price, description);

            Toast.makeText(HostCarActivity.this, "Car Successfully Listed!", Toast.LENGTH_SHORT).show();
            // Optionally, you can redirect to another activity, like a confirmation or success page
        });
    }

    private void saveCarDetails(String model, String make, String year, String price, String description) {
        // Logic for saving data to the database or backend (e.g., Firebase or MySQL)
    }

}
