package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class PartnerEditProfileActivity extends AppCompatActivity {

    private ImageView profilePicture;
    private EditText partnerName, partnerPhone, partnerEmail, companyName, businessAddress, serviceArea, bankAccount, upiId;
    private Button saveButton, cancelButton;
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_edit_profile);

        // Initialize Views
        profilePicture = findViewById(R.id.profile_picture);
        partnerName = findViewById(R.id.partner_name);
        partnerPhone = findViewById(R.id.partner_phone);
        partnerEmail = findViewById(R.id.partner_email);
        companyName = findViewById(R.id.company_name);
        businessAddress = findViewById(R.id.business_address);
        serviceArea = findViewById(R.id.service_area);
        bankAccount = findViewById(R.id.bank_account);
        upiId = findViewById(R.id.upi_id);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);
        backButton = findViewById(R.id.back_button);

        // Handle Back Button click
        backButton.setOnClickListener(v -> finish());

        // Load existing profile data
        loadProfileData();

        // Set Click Listeners
        profilePicture.setOnClickListener(v -> onChangeProfilePicture());
        saveButton.setOnClickListener(v -> saveProfile());
        cancelButton.setOnClickListener(v -> onCancelEdit());
    }

    private void loadProfileData() {
        // Simulate loading data (replace with actual data loading logic)
        partnerName.setText("Cameron Williamson");
        partnerPhone.setText("(219) 555-0114");
        partnerEmail.setText("cameron@example.com");
        companyName.setText("Example Company");
        businessAddress.setText("123 Example St, City, Country");
        serviceArea.setText("City or Region");
        bankAccount.setText("123456789");
        upiId.setText("cameron@upi");
    }

    private void onChangeProfilePicture() {
        Toast.makeText(this, "Change Profile Picture clicked", Toast.LENGTH_SHORT).show();
        // Implement profile picture change functionality here
    }

    private void saveProfile() {
        String name = partnerName.getText().toString().trim();
        String phone = partnerPhone.getText().toString().trim();
        String email = partnerEmail.getText().toString().trim();
        String company = companyName.getText().toString().trim();
        String address = businessAddress.getText().toString().trim();
        String area = serviceArea.getText().toString().trim();
        String account = bankAccount.getText().toString().trim();
        String upi = upiId.getText().toString().trim();

        // Validation checks
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || company.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simulate saving data (replace with actual save logic, e.g., API call)
        Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show();

        // Navigate back to the profile screen
        finish();
    }

    private void onCancelEdit() {
        Toast.makeText(this, "Edit cancelled", Toast.LENGTH_SHORT).show();
        finish(); // Close the activity and go back to the previous screen
    }
}
