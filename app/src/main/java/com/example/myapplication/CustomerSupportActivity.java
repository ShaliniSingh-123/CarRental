package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerSupportActivity extends AppCompatActivity {

    private TextView phoneNumberTextView, emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);

        // Initialize TextViews
        phoneNumberTextView = findViewById(R.id.tv_phone_number);
        emailTextView = findViewById(R.id.tv_email);

        // Set dynamic data if required (e.g., phone number or email)
        phoneNumberTextView.setText("(704) 555-0127");
        emailTextView.setText("kenzilawson@example.com");
    }
}
