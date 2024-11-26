package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerSupportActivity extends AppCompatActivity {

    private TextView phoneNumberText, emailIdText;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);

        // Initialize the views
        phoneNumberText = findViewById(R.id.phoneNumber);
        emailIdText = findViewById(R.id.emailId);
        backButton = findViewById(R.id.backButton);

        // Set the dynamic content (phone number and email) if needed
        phoneNumberText.setText("(704) 555-0127");
        emailIdText.setText("kenzi.lawson@example.com");

        // Set the onClickListener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back navigation
                onBackPressed();  // This will take you to the previous activity
            }
        });
    }
}
