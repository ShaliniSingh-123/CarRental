package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.activity.ConfirmationActivity;

public class IdentificationActivity  extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText nationalIdInput;
    private LinearLayout uploadFront, uploadBack;
    private Button nextButton;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        // Initialize Views
        radioGroup = findViewById(R.id.radio_group);
        nationalIdInput = findViewById(R.id.national_id_input);
        uploadFront = findViewById(R.id.upload_front);
        uploadBack = findViewById(R.id.upload_back);
        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.back_button);

        // Handle Radio Button Selection
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_national_id) {
                nationalIdInput.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.radio_passport) {
                nationalIdInput.setVisibility(View.GONE);
            }
        });

        // Upload Image Click Handlers
        uploadFront.setOnClickListener(v -> {
            // Add logic to upload front ID photo
            Toast.makeText(this, "Upload Front Photo", Toast.LENGTH_SHORT).show();
        });

        uploadBack.setOnClickListener(v -> {
            // Add logic to upload back ID photo
            Toast.makeText(this, "Upload Back Photo", Toast.LENGTH_SHORT).show();
        });

        // Back Button
        backButton.setOnClickListener(v -> finish());

        // Next Button
        nextButton.setOnClickListener(v -> {
            String nationalId = nationalIdInput.getText().toString().trim();
            if (radioGroup.getCheckedRadioButtonId() == R.id.radio_national_id && nationalId.isEmpty()) {
                Toast.makeText(this, "Please enter your National ID", Toast.LENGTH_SHORT).show();
            } else {
                // Proceed to ConfirmActivity
                Intent intent = new Intent(IdentificationActivity.this, ConfirmationActivity.class);
                startActivity(intent);
            }
        });

    }
}