package com.example.myapplication.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class PartnerContactActivity extends AppCompatActivity {

    private EditText fullName, emailAddress, message;
    private Button submitButton;
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_contact);

        fullName = findViewById(R.id.full_name);
        emailAddress = findViewById(R.id.email_address);
        message = findViewById(R.id.message);
        submitButton = findViewById(R.id.submit_button);
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString().trim();
                String email = emailAddress.getText().toString().trim();
                String userMessage = message.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(userMessage)) {
                    Toast.makeText(PartnerContactActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    // Send message or perform other actions
                    Toast.makeText(PartnerContactActivity.this, "Message Sent!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
