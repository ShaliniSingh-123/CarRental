package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PartnerSignupActivity extends AppCompatActivity {

    private ImageView backArrow;
    private TextInputEditText fullNameEditText, emailEditText, mobileEditText, addressEditText, passwordEditText, confirmPasswordEditText;
    private CheckBox termsCheckBox;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_signup);

        // Initialize views
        backArrow = findViewById(R.id.backArrow);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        addressEditText = findViewById(R.id.addressEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        termsCheckBox = findViewById(R.id.termsCheckBox);
        registerButton = findViewById(R.id.registerButton);

        // Back arrow functionality
        backArrow.setOnClickListener(v -> onBackPressed());

        // Register button functionality
        registerButton.setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String mobile = mobileEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            // Validate the input fields
            if (fullName.isEmpty()) {
                showToast("Full Name is required");
            } else if (email.isEmpty()) {
                showToast("Email is required");
            } else if (mobile.isEmpty()) {
                showToast("Mobile Number is required");
            } else if (address.isEmpty()) {
                showToast("Address is required");
            } else if (password.isEmpty()) {
                showToast("Password is required");
            } else if (confirmPassword.isEmpty()) {
                showToast("Confirm Password is required");
            } else if (!password.equals(confirmPassword)) {
                showToast("Passwords do not match");
            } else if (!termsCheckBox.isChecked()) {
                showToast("You must agree to the terms and conditions");
            } else {
                // Proceed with registration logic (e.g., save data, communicate with server)
                showToast("Registration Successful");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(PartnerSignupActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
