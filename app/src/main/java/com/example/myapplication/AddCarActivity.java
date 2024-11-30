package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCarActivity extends AppCompatActivity {

    // Step 1 Fields
    private EditText etCarName, etCarModel, etRegistrationNumber, etSubcategory;
    private Spinner spinnerCategory, spinnerSeatingCapacity, spinnerFuelType, spinnerTransmissionType;

    // Step 2 Fields
    private EditText dailyRentalPrice, weeklyRentalPrice, monthlyRentalPrice;

    // Step 3 Fields (Features)
    private CheckBox airConditioning, gps, bluetooth, childSeat;
    private EditText otherFeatures;

    // Step 4 Fields (Location)
    private EditText pickupLocation, dropoffLocation;

    // Step 5 Fields (Image Uploads)
    private Button uploadImagesButton, uploadDocumentButton;

    // Navigation Buttons
    private Button nextButton;

    // Step Views
    private View step1, step2, step3, step4, step5;
    private TextView stepIndicator;
    private int currentStep = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // Initialize Views for Steps
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);
        step5 = findViewById(R.id.step5);

        stepIndicator = findViewById(R.id.stepIndicator);
        nextButton = findViewById(R.id.nextButton);

        // Initialize Views for Step 1
        etCarName = findViewById(R.id.etCarName);
        etCarModel = findViewById(R.id.etCarModel);
        etRegistrationNumber = findViewById(R.id.etRegistrationNumber);
        etSubcategory = findViewById(R.id.etSubcategory);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerSeatingCapacity = findViewById(R.id.spinnerSeatingCapacity);
        spinnerFuelType = findViewById(R.id.spinnerFuelType);
        spinnerTransmissionType = findViewById(R.id.spinnerTransmissionType);

        // Step 2 Fields
        dailyRentalPrice = findViewById(R.id.dailyRentalPrice);
        weeklyRentalPrice = findViewById(R.id.weeklyRentalPrice);
        monthlyRentalPrice = findViewById(R.id.monthlyRentalPrice);

        // Step 3 Fields (Features)
        airConditioning = findViewById(R.id.airConditioning);
        gps = findViewById(R.id.gps);
        bluetooth = findViewById(R.id.bluetooth);
        childSeat = findViewById(R.id.childSeat);
        otherFeatures = findViewById(R.id.otherFeatures);

        // Step 4 Fields (Location)
        pickupLocation = findViewById(R.id.pickupLocation);
        dropoffLocation = findViewById(R.id.dropoffLocation);

        // Step 5 Fields (Image Uploads)
        uploadImagesButton = findViewById(R.id.uploadImagesButton);
        uploadDocumentButton = findViewById(R.id.uploadDocumentButton);

        // Populate Spinners with data
        setupSpinners();

        // Handle "Next" Button Click
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (currentStep) {
                    case 1:
                        if (validateStep1()) {
                            step1.setVisibility(View.GONE);
                            step2.setVisibility(View.VISIBLE);
                            stepIndicator.setText("Step 2 of 5: Rental Details");
                            currentStep++;
                        }
                        break;
                    case 2:
                        step2.setVisibility(View.GONE);
                        step3.setVisibility(View.VISIBLE);
                        stepIndicator.setText("Step 3 of 5: Features");
                        currentStep++;
                        break;
                    case 3:
                        step3.setVisibility(View.GONE);
                        step4.setVisibility(View.VISIBLE);
                        stepIndicator.setText("Step 4 of 5: Location");
                        currentStep++;
                        break;
                    case 4:
                        step4.setVisibility(View.GONE);
                        step5.setVisibility(View.VISIBLE);
                        stepIndicator.setText("Step 5 of 5: Image and Documentation");
                        nextButton.setText("Submit");
                        currentStep++;
                        break;
                    case 5:
                        // Submit form logic here
                        submitForm();
                        break;
                }
            }
        });
    }

    // Setup Spinners with predefined values
    private void setupSpinners() {
        // Category Spinner
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // Seating Capacity Spinner
        ArrayAdapter<CharSequence> seatingCapacityAdapter = ArrayAdapter.createFromResource(this,
                R.array.seating_capacity_array, android.R.layout.simple_spinner_item);
        seatingCapacityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeatingCapacity.setAdapter(seatingCapacityAdapter);

        // Fuel Type Spinner
        ArrayAdapter<CharSequence> fuelTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.fuel_type_array, android.R.layout.simple_spinner_item);
        fuelTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(fuelTypeAdapter);

        // Transmission Type Spinner
        ArrayAdapter<CharSequence> transmissionTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.transmission_type_array, android.R.layout.simple_spinner_item);
        transmissionTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTransmissionType.setAdapter(transmissionTypeAdapter);
    }

    // Validate Step 1 Input
    private boolean validateStep1() {
        String carName = etCarName.getText().toString().trim();
        String carModel = etCarModel.getText().toString().trim();
        String registrationNumber = etRegistrationNumber.getText().toString().trim();
        String subcategory = etSubcategory.getText().toString().trim();

        if (carName.isEmpty() || carModel.isEmpty() || registrationNumber.isEmpty() || subcategory.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields in Step 1", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Submit form logic (final step)
    private void submitForm() {
        // You can collect data from all fields and submit it to the server or save it locally.
        Toast.makeText(this, "Form Submitted", Toast.LENGTH_SHORT).show();
    }
}