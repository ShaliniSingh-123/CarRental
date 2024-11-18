package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import android.content.Intent;

public class SignupActivity extends AppCompatActivity {

    private TextView headingTextView;
    private EditText emailEditText, firstNameEditText, lastNameEditText, dobEditText, passwordEditText, phoneEditText;
    private ImageButton backButton, emailNextButton, nameNextButton, dobNextButton, passwordNextButton, phoneNextButton;
    private LinearLayout nameLayout;
    private DatabaseHelper databaseHelper; // Instance of DatabaseHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Link all views
        headingTextView = findViewById(R.id.headingEmailTextView);
        backButton = findViewById(R.id.backButton);
        emailEditText = findViewById(R.id.emailEditText);
        emailNextButton = findViewById(R.id.emailNextButton);
        nameLayout = findViewById(R.id.nameLayout);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        nameNextButton = findViewById(R.id.nameNextButton);
        dobEditText = findViewById(R.id.dobEditText);
        dobNextButton = findViewById(R.id.dobNextButton);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordNextButton = findViewById(R.id.passwordNextButton);
        phoneEditText = findViewById(R.id.mobileEditText);
        phoneNextButton = findViewById(R.id.mobileNextButton);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Show "Next" button when email is filled
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                emailNextButton.setVisibility(isValidEmail(s.toString().trim()) ? View.VISIBLE : View.GONE);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        emailNextButton.setOnClickListener(v -> {
            if (isValidEmail(emailEditText.getText().toString().trim())) {
                moveToNameSection();
            } else {
                emailEditText.setError("Enter a valid email address");
            }
        });

        // Back button setup
        backButton.setOnClickListener(v -> {
            if (phoneEditText.getVisibility() == View.VISIBLE) {
                moveToPasswordSection();
            } else if (passwordEditText.getVisibility() == View.VISIBLE) {
                moveToDobSection();
            } else if (dobEditText.getVisibility() == View.VISIBLE) {
                moveToNameSection();
            } else if (nameLayout.getVisibility() == View.VISIBLE) {
                moveToEmailSection();
            }
        });

        // Show "Next" button when both first and last names are filled
        TextWatcher nameWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                boolean isNameFilled = !firstNameEditText.getText().toString().trim().isEmpty()
                        && !lastNameEditText.getText().toString().trim().isEmpty();
                nameNextButton.setVisibility(isNameFilled ? View.VISIBLE : View.GONE);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };
        firstNameEditText.addTextChangedListener(nameWatcher);
        lastNameEditText.addTextChangedListener(nameWatcher);

        nameNextButton.setOnClickListener(v -> moveToDobSection());

        // Set up DOB EditText with a DatePicker
        dobEditText.setOnClickListener(v -> showDatePicker());
        dobEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                dobNextButton.setVisibility(s.toString().trim().isEmpty() ? View.GONE : View.VISIBLE);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        dobNextButton.setOnClickListener(v -> moveToPasswordSection());

        // Show "Next" button when password is filled
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                passwordNextButton.setVisibility(s.toString().trim().isEmpty() ? View.GONE : View.VISIBLE);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        passwordNextButton.setOnClickListener(v -> moveToPhoneSection());

        // Show "Next" button when phone number is filled
        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                phoneNextButton.setVisibility(s.toString().trim().isEmpty() ? View.GONE : View.VISIBLE);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        phoneNextButton.setOnClickListener(v -> {
            if (isValidPhoneNumber(phoneEditText.getText().toString().trim())) {
                saveUserToDatabase();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                phoneEditText.setError("Enter a valid phone number");
            }
        });
    }

    private void showSection(String heading, boolean backVisible, boolean emailVisible,
                             boolean nameLayoutVisible, boolean dobVisible, boolean passwordVisible, boolean phoneVisible) {
        headingTextView.setText(heading);
        backButton.setVisibility(backVisible ? View.VISIBLE : View.GONE);
        emailEditText.setVisibility(emailVisible ? View.VISIBLE : View.GONE);
        emailNextButton.setVisibility(emailVisible && !emailEditText.getText().toString().trim().isEmpty() ? View.VISIBLE : View.GONE);
        nameLayout.setVisibility(nameLayoutVisible ? View.VISIBLE : View.GONE);
        nameNextButton.setVisibility(nameLayoutVisible && !firstNameEditText.getText().toString().trim().isEmpty() &&
                !lastNameEditText.getText().toString().trim().isEmpty() ? View.VISIBLE : View.GONE);
        dobEditText.setVisibility(dobVisible ? View.VISIBLE : View.GONE);
        dobNextButton.setVisibility(dobVisible && !dobEditText.getText().toString().trim().isEmpty() ? View.VISIBLE : View.GONE);
        passwordEditText.setVisibility(passwordVisible ? View.VISIBLE : View.GONE);
        passwordNextButton.setVisibility(passwordVisible && !passwordEditText.getText().toString().trim().isEmpty() ? View.VISIBLE : View.GONE);
        phoneEditText.setVisibility(phoneVisible ? View.VISIBLE : View.GONE);
        phoneNextButton.setVisibility(phoneVisible && !phoneEditText.getText().toString().trim().isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void moveToEmailSection() {
        showSection("What is Your Email?", false, true, false, false, false, false);
    }

    private void moveToNameSection() {
        showSection("What is Your Name?", true, false, true, false, false, false);
    }

    private void moveToDobSection() {
        showSection("What is Your Date of Birth?", true, false, false, true, false, false);
    }

    private void moveToPasswordSection() {
        showSection("Define your Password", true, false, false, false, true, false);
    }

    private void moveToPhoneSection() {
        showSection("Verify your Mobile Number", true, false, false, false, false, true);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this,
                (DatePicker view, int year, int month, int dayOfMonth) -> dobEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10 && Patterns.PHONE.matcher(phoneNumber).matches();
    }

    private void saveUserToDatabase() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", emailEditText.getText().toString().trim());
        values.put("first_name", firstNameEditText.getText().toString().trim());
        values.put("last_name", lastNameEditText.getText().toString().trim());
        values.put("dob", dobEditText.getText().toString().trim());
        values.put("password", passwordEditText.getText().toString().trim());
        values.put("phone", phoneEditText.getText().toString().trim());
        values.put("role", "Customer");  // You can adjust the role as needed

        long newRowId = db.insert("users", null, values);
        if (newRowId == -1) {
            // Insert failed
            Toast.makeText(this, "Error saving user to database", Toast.LENGTH_SHORT).show();
        } else {
            // Insert succeeded
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
