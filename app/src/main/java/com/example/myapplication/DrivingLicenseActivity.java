package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;

public class DrivingLicenseActivity extends AppCompatActivity {

    private TextInputEditText licenseNumber, licenseExpiryDate;
    private LinearLayout frontPhoto, backPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_license);

        licenseNumber = findViewById(R.id.licenseNumber);
        licenseExpiryDate = findViewById(R.id.licenseExpiryDate);
        frontPhoto = findViewById(R.id.frontPhoto);
        backPhoto = findViewById(R.id.backPhoto);

        // Back button click
        findViewById(R.id.backIcon).setOnClickListener(v -> finish());

        // Date picker for license expiry date
        licenseExpiryDate.setOnClickListener(v -> showDatePicker());

        // Photo upload listeners
        frontPhoto.setOnClickListener(v -> showToast("Upload Front Photo"));
        backPhoto.setOnClickListener(v -> showToast("Upload Back Photo"));

        // Continue button
        findViewById(R.id.continueButton).setOnClickListener(v -> {
            String license = licenseNumber.getText().toString();
            String expiryDate = licenseExpiryDate.getText().toString();
            if (license.isEmpty() || expiryDate.isEmpty()) {
                showToast("Please fill all fields");
            } else {
                showToast("Details Submitted");
            }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> licenseExpiryDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
