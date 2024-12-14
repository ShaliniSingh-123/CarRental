package com.example.myapplication.activity;
import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.myapplication.R;
import com.example.myapplication.models.request.AddCarRequest;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.models.response.AddCarResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCarActivity extends Activity {
    private static final int PICK_IMAGE = 1;
    private static final int MAX_IMAGES = 6;
    private ArrayList<Bitmap> imageList = new ArrayList<>();
    private AddCarActivity.ImageAdapter imageAdapter;

    // Step 1 Fields
    private EditText etCarName, etCarModel, etRegistrationNumber, etSubcategory;
    private Spinner spinnerCategory, spinnerSeatingCapacity, spinnerFuelType, spinnerTransmissionType;

    // Step 2 Fields (Pricing)
    private EditText dailyRentalPrice;

    // Step 3 Fields (Features)
    private CheckBox airConditioning, gps, bluetooth, childSeat;
    private EditText otherFeatures;

    // Step 4 Fields (Location)
    private EditText pickupLocation, dropoffLocation;

    // Navigation Buttons
    private Button nextButton;
    private ImageView backArrow, frontPhoto, backPhoto,odocumentfrontPhoto,odocumentbackPhoto,licensefrontPhoto,licensebackPhoto,bankPhoto;

    // Step Views
    private View step1, step2, step3, step4, step5;
    private TextView stepIndicator;
    private int currentStep = 1;

    // Retrofit API Service
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        GridView gridView = findViewById(R.id.gridView);
        imageAdapter = new AddCarActivity.ImageAdapter();
        gridView.setAdapter(imageAdapter);

        // Add click listener for adding and deleting images
        gridView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            if (position == imageList.size()) {
                // "+" icon clicked
                if (imageList.size() < MAX_IMAGES) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE);
                } else {
                    Toast.makeText(AddCarActivity.this, "Maximum 6 images allowed!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Image clicked for deletion
                showDeleteConfirmationDialog(position);
            }
        });

        // Initialize Retrofit service
        apiService = RetrofitClient.getRetrofitInstance(AddCarActivity.this).create(ApiService.class);

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
                        transitionToStep(step3, step4, "Add Your Car Images");
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
                        transitionToStep(step5, step4, "Add Your Car Images");
                        nextButton.setText("Next");
                        currentStep--;
                        break;
                }
            } else {
                finish(); // Navigate back
            }
        });

        // Image selection for front photo
        frontPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });

        // Image selection for back photo
        backPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        });

        odocumentfrontPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });

        odocumentbackPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        });
        licensefrontPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });

        licensebackPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        });
        bankPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        });

    }

    // Initialize Views for all steps and buttons
    private void initializeViews() {
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);
        step5 = findViewById(R.id.step5);

        stepIndicator = findViewById(R.id.stepIndicator);
        nextButton = findViewById(R.id.nextButton);
        backArrow = findViewById(R.id.backArrow);

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
        dailyRentalPrice = findViewById(R.id.dailyRentalPrice);

        // Step 3 Fields (Features)
        airConditioning = findViewById(R.id.airConditioning);
        gps = findViewById(R.id.gps);
        bluetooth = findViewById(R.id.bluetooth);
        childSeat = findViewById(R.id.childSeat);
        otherFeatures = findViewById(R.id.otherFeatures);

        // Step 4 Fields (Location)
        pickupLocation = findViewById(R.id.pickupLocation);
        dropoffLocation = findViewById(R.id.dropoffLocation);
        frontPhoto = findViewById(R.id.frontImageView);
        backPhoto = findViewById(R.id.backImageView);
        odocumentfrontPhoto = findViewById(R.id.odocumentfrontImageView);
        odocumentbackPhoto = findViewById(R.id.odocumentbackImageView);
        licensefrontPhoto = findViewById(R.id.licensefrontImageView);
        licensebackPhoto = findViewById(R.id.licensebackImageView);
        bankPhoto = findViewById(R.id.bankPhotoImageView);

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
        if (etCarName.getText().toString().trim().isEmpty() ||
                etCarModel.getText().toString().trim().isEmpty() ||
                etRegistrationNumber.getText().toString().trim().isEmpty() ||
                etSubcategory.getText().toString().trim().isEmpty()) {
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

    // Submit form logic (final step)
    private void submitForm() {
        AddCarRequest request = new AddCarRequest();
        request.setCarName(etCarName.getText().toString().trim());
        request.setCarModel(etCarModel.getText().toString().trim());
        request.setRegistrationNumber(etRegistrationNumber.getText().toString().trim());
        request.setSubcategory(etSubcategory.getText().toString().trim());
        request.setCategory(spinnerCategory.getSelectedItem().toString());
        request.setSeatingCapacity(Integer.parseInt(spinnerSeatingCapacity.getSelectedItem().toString()));
        request.setFuelType(spinnerFuelType.getSelectedItem().toString());
        request.setTransmissionType(spinnerTransmissionType.getSelectedItem().toString());
        request.setDailyRentalPrice(Double.parseDouble(dailyRentalPrice.getText().toString().trim()));

        List<String> features = new ArrayList<>();
        if (airConditioning.isChecked()) features.add("Air Conditioning");
        if (gps.isChecked()) features.add("GPS");
        if (bluetooth.isChecked()) features.add("Bluetooth");
        if (childSeat.isChecked()) features.add("Child Seat");
        if (!otherFeatures.getText().toString().isEmpty()) features.add(otherFeatures.getText().toString());

        request.setFeatures(features);
        request.setPickupLocation(pickupLocation.getText().toString().trim());
        request.setDropoffLocation(dropoffLocation.getText().toString().trim());

        apiService.addCar(request).enqueue(new Callback<AddCarResponse>() {
            @Override
            public void onResponse(Call<AddCarResponse> call, Response<AddCarResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AddCarActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().isSuccess()) {
                        Intent intent = new Intent(AddCarActivity.this, PartnerDashboardActivity.class);
                        intent.putExtra("tab", "MyCars");
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(AddCarActivity.this, "Failed to add car", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCarResponse> call, Throwable t) {
                Toast.makeText(AddCarActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Handle the result of image selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                imageList.add(bitmap);
                imageAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showDeleteConfirmationDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Image")
                .setMessage("Are you sure you want to delete this image?")
                .setPositiveButton("Yes", (DialogInterface dialog, int which) -> {
                    imageList.remove(position);
                    imageAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private class ImageAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return imageList.size() < MAX_IMAGES ? imageList.size() + 1 : MAX_IMAGES;
        }

        @Override
        public Object getItem(int position) {
            return position < imageList.size() ? imageList.get(position) : null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, android.view.ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(AddCarActivity.this);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }

            if (position < imageList.size()) {
                imageView.setImageBitmap(imageList.get(position));
            } else {
                imageView.setImageResource(android.R.drawable.ic_input_add); // "+" icon
            }

            return imageView;
        }
    }
}