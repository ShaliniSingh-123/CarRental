package com.example.myapplication.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class PartnerChangePasswordActivity extends AppCompatActivity {

    private TextInputEditText passwordEditText, confirmPasswordEditText;
    private TextView passwordStrengthText, passwordMatchError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_change_password);

        // Initialize views
        ImageView backArrow = findViewById(R.id.backArrow);
        TextView instructionText = findViewById(R.id.instructionText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        passwordStrengthText = findViewById(R.id.passwordStrengthText);
        passwordMatchError = findViewById(R.id.passwordMatchError);
        Button registerButton = findViewById(R.id.registerButton);

        // Back arrow functionality
        backArrow.setOnClickListener(v -> onBackPressed());

        // Password strength validation
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePasswordStrength(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Register button functionality
        registerButton.setOnClickListener(v -> resetPassword());
    }

    private void validatePasswordStrength(String password) {
        if (password.length() >= 8) {
            passwordStrengthText.setText("Password strength: Strong");
            passwordStrengthText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            passwordStrengthText.setText("Must be at least 8 characters.");
            passwordStrengthText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void resetPassword() {
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals(confirmPassword)) {
            passwordMatchError.setVisibility(View.GONE);
            Toast.makeText(this, "Password reset successfully!", Toast.LENGTH_SHORT).show();
            // Perform further actions like API call or navigation here
        } else {
            passwordMatchError.setVisibility(View.VISIBLE);
        }
    }
}
