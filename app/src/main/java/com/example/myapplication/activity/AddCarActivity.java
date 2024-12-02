package com.example.myapplication.activity;

import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AddCarActivity extends AppCompatActivity {

    // Step 1 Fields
    private EditText etCarName, etCarModel, etRegistrationNumber, etSubcategory;
    private Spinner spinnerCategory, spinnerSeatingCapacity, spinnerFuelType, spinnerTransmissionType;
    private GridLayout gridLayout;
    private ImageView imageView1, imageView2, imageView3, imageView4;

    // Step 2 Fields (Pricing)
    private EditText dailyRentalPrice;

    // Step 3 Fields (Features)
    private CheckBox airConditioning, gps, bluetooth, childSeat;
    private EditText otherFeatures;

    // Step 4 Fields (Location)
    private EditText pickupLocation, dropoffLocation;

    // Step 5 Fields (Image Uploads)
    private Button uploadImagesButton, uploadDocumentButton;

    // Navigation Buttons
    private Button nextButton;
    private ImageView backArrow;

    // Step Views
    private View step1, step2, step3, step4, step5;
    private TextView stepIndicator;
    private int currentStep = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // Initialize Views for Steps
        initializeViews();

        // Setup Spinners
        setupSpinners();

        // Handle "Next" Button Click
        nextButton.setOnClickListener(v -> {
            switch (currentStep) {
                case 1:
                    if (validateStep1()) {
                        transitionToStep(step1, step2, "Features");
                        currentStep++;
                    }
                    break;
                case 2:
                    if (validateStep2()) {
                        transitionToStep(step2, step3, "Location");
                        currentStep++;
                    }
                    break;
                case 3:
                    if (validateStep3()) {
                        transitionToStep(step3, step4, "Upload Your Car Pictures");
                        currentStep++;
                    }
                    break;
                case 4:
                    if (validateStep4()) {
                        transitionToStep(step4, step5, "Upload Documents");
                        nextButton.setText("Submit");
                        currentStep++;
                    }
                    break;
                case 5:
                    submitForm();
                    // Get the ViewPager from PartnerDashboardActivity and switch to the "My Cars" tab
                    Intent intent = new Intent(AddCarActivity.this, PartnerDashboardActivity.class);
                    intent.putExtra("tab", "MyCars"); // Pass a flag to indicate to the dashboard activity to switch to the "My Cars" tab
                    startActivity(intent);
                    finish();  // Optionally, you can use finish() to close the current activity
                    break;
            }
        });

        // Handle "Back" Button Click
        backArrow.setOnClickListener(v -> {
            if (currentStep > 1) {
                switch (currentStep) {
                    case 2:
                        transitionToStep(step2, step1, "Add Basic Information");
                        currentStep--;
                        break;
                    case 3:
                        transitionToStep(step3, step2, "Features");
                        currentStep--;
                        break;
                    case 4:
                        transitionToStep(step4, step3, "Location");
                        currentStep--;
                        break;
                    case 5:
                        transitionToStep(step5, step4, "Upload Your Car Pictures");
                        currentStep--;
                        break;
                }
            } else {
                // When the current step is 1, navigate to PartnerDashboard activity
                Intent intent = new Intent(AddCarActivity.this, PartnerDashboardActivity.class);
                startActivity(intent);
                finish();  // Optional: Finish the current activity so that the user can't navigate back to it
            }
        });

    }

    // Initialize Views for all steps and buttons
    private void initializeViews() {
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);
        step5 = findViewById(R.id.step5);

        gridLayout = findViewById(R.id.gridLayout);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        imageView1.setOnClickListener(v -> openImagePicker(1));
        imageView2.setOnClickListener(v -> openImagePicker(2));
        imageView3.setOnClickListener(v -> openImagePicker(3));
        imageView4.setOnClickListener(v -> openImagePicker(4));

        stepIndicator = findViewById(R.id.stepIndicator);
        nextButton = findViewById(R.id.nextButton);
        backArrow = findViewById(R.id.backArrow); // Initialize the back arrow button

        // Step 1 Fields
        etCarName = findViewById(R.id.etCarName);
        etCarModel = findViewById(R.id.etCarModel);
        etRegistrationNumber = findViewById(R.id.etRegistrationNumber);
        etSubcategory = findViewById(R.id.etSubcategory);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerSeatingCapacity = findViewById(R.id.spinnerSeatingCapacity);
        spinnerFuelType = findViewById(R.id.spinnerFuelType);
        spinnerTransmissionType = findViewById(R.id.spinnerTransmissionType);

        // Step 2 Fields (Pricing)
//        dailyRentalPrice = findViewById(R.id.dailyRentalPrice);

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
    }

    // Transition between steps with indicator update
    private void transitionToStep(View hideStep, View showStep, String stepText) {
        hideStep.setVisibility(View.GONE);
        showStep.setVisibility(View.VISIBLE);
        stepIndicator.setText(stepText);
    }

    // Setup Spinners with predefined values
    private void setupSpinners() {
        setupSpinner(spinnerCategory, R.array.category_array);
        setupSpinner(spinnerSeatingCapacity, R.array.seating_capacity_array);
        setupSpinner(spinnerFuelType, R.array.fuel_type_array);
        setupSpinner(spinnerTransmissionType, R.array.transmission_type_array);
    }

    private void setupSpinner(Spinner spinner, int arrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    // Validation for Step 1
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

    // Additional validations for other steps
    private boolean validateStep2() {
        return true;
    }

    private boolean validateStep3() {
        return true;
    }

    private boolean validateStep4() {
        return true;
    }

    // Image picker logic (stub)
    private void openImagePicker(int imageIndex) {
        Toast.makeText(this, "Select Image for ImageView " + imageIndex, Toast.LENGTH_SHORT).show();
    }

    // Submit form logic (final step)
    private void submitForm() {
        Toast.makeText(this, "Form Submitted", Toast.LENGTH_SHORT).show();
    }
}
