package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DriverSignupActivity extends AppCompatActivity {

    // Declare views
    private EditText fullNameEditText, emailEditText, mobileEditText, passwordEditText, licenseNumberEditText, licenseExpiryDateEditText;
    private Button registerButton;
    private CheckBox termsCheckBox;
    private ImageView backArrow, frontPhoto, backPhoto;

    // Constants for image picker request codes
    private static final int FRONT_PHOTO_REQUEST_CODE = 1;
    private static final int BACK_PHOTO_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signup); // Ensure your XML is named activity_driver_signup.xml

        // Initialize views
        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        licenseNumberEditText = findViewById(R.id.licenseNumberEditText);
        licenseExpiryDateEditText = findViewById(R.id.licenseExpiryDateEditText);
        registerButton = findViewById(R.id.registerButton);
        termsCheckBox = findViewById(R.id.termsCheckBox);
        backArrow = findViewById(R.id.backArrow);
        frontPhoto = findViewById(R.id.frontImageView);
        backPhoto = findViewById(R.id.backImageView);

        // Back arrow click listener
        backArrow.setOnClickListener(v -> onBackPressed());

        // Register Button click listener
        registerButton.setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String mobile = mobileEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String licenseNumber = licenseNumberEditText.getText().toString().trim();
            String licenseExpiryDate = licenseExpiryDateEditText.getText().toString().trim();
            boolean isTermsAccepted = termsCheckBox.isChecked();

            // Validate input fields
            if (fullName.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty() || licenseNumber.isEmpty() || licenseExpiryDate.isEmpty()) {
                Toast.makeText(DriverSignupActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else if (!isTermsAccepted) {
                Toast.makeText(DriverSignupActivity.this, "Please agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            } else {
                // Proceed with registration logic (e.g., save to database, send data to server, etc.)
                Toast.makeText(DriverSignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                // After registration, navigate to another activity if needed
                // Intent intent = new Intent(DriverSignupActivity.this, NextActivity.class);
                // startActivity(intent);
            }
        });

        // Front photo click listener to open image picker
        frontPhoto.setOnClickListener(v -> {
            // Open file picker for front photo
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, FRONT_PHOTO_REQUEST_CODE);
        });

        // Back photo click listener to open image picker
        backPhoto.setOnClickListener(v -> {
            // Open file picker for back photo
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, BACK_PHOTO_REQUEST_CODE);
        });
    }

    // Handle the result of the image picker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the image selection was successful
        if (resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Set the selected image to the appropriate image view based on the request code
            if (requestCode == FRONT_PHOTO_REQUEST_CODE) {
                frontPhoto.setImageBitmap(selectedImage);
            } else if (requestCode == BACK_PHOTO_REQUEST_CODE) {
                backPhoto.setImageBitmap(selectedImage);
            }
        }
    }
}
