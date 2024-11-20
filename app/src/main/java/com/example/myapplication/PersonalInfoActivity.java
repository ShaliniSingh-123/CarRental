package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalInfoActivity extends AppCompatActivity {

    private ImageView backButton;
    private EditText nameField, emailField, phoneField, addressField;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        // Initialize Views
        backButton = findViewById(R.id.back_button);
        nameField = findViewById(R.id.name_field);
        emailField = findViewById(R.id.email_field);
        phoneField = findViewById(R.id.phone_field);
        addressField = findViewById(R.id.address_field);
        continueButton = findViewById(R.id.continue_button);

        // Back Button Click Listener
        backButton.setOnClickListener(v -> finish());

        // Continue Button Click Listener
        continueButton.setOnClickListener(v -> {
            String name = nameField.getText().toString();
            String email = emailField.getText().toString();
            String phone = phoneField.getText().toString();
            String address = addressField.getText().toString();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(PersonalInfoActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                // Create an Intent to navigate to IdentificationActivity
                Intent intent = new Intent(PersonalInfoActivity.this, IdentificationActivity.class);

                // Pass data to IdentificationActivity
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);

                // Start IdentificationActivity
                startActivity(intent);
            }
        });
    }
}
