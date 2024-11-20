package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAddress;
    private Button btnChange, btnUpdate;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_address);
        btnChange = findViewById(R.id.btn_change);
        btnUpdate = findViewById(R.id.btn_update);
        profileImage = findViewById(R.id.profile_image);

        // Set default profile image
        profileImage.setImageResource(R.drawable.profile); // Replace with actual image resource if needed

        // Handle Change Button click
        btnChange.setOnClickListener(v -> {
            // Code to handle profile image change (e.g., opening image picker)
            // For now, this can be a placeholder functionality
            Toast.makeText(EditProfileActivity.this, "Change Profile Image", Toast.LENGTH_SHORT).show();
        });

        // Handle Update Button click
        btnUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String address = etAddress.getText().toString();

            // Check if any field is empty
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(EditProfileActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save the updated profile information (this can be stored in a database or shared preferences)
                // For now, show a success message
                Toast.makeText(EditProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

                // Optionally, navigate back to the profile screen or another activity
                // Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                // startActivity(intent);
            }
        });
    }
}
