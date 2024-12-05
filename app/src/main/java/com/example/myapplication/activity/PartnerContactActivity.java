package com.example.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class PartnerContactActivity extends AppCompatActivity {

    private TextView partnerName, partnerEmail, partnerPhone;
    private EditText contactMessage;
    private ImageView backButton;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_contact);

        // Initialize UI components
        partnerName = findViewById(R.id.partner_name);
        partnerEmail = findViewById(R.id.partner_email);
        partnerPhone = findViewById(R.id.partner_phone);
        contactMessage = findViewById(R.id.contact_message);
        sendMessageButton = findViewById(R.id.send_message_button);
        backButton = findViewById(R.id.back_button);

        // Handle Back Button click
        backButton.setOnClickListener(v -> finish());

        // Set partner details
        partnerName.setText("Partner Name: John Doe");
        partnerEmail.setText("Email: johndoe@example.com");
        partnerPhone.setText("Phone: +1 234 567 890");

        // Set button click listener
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = contactMessage.getText().toString().trim();
                if (message.isEmpty()) {
                    Toast.makeText(PartnerContactActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                } else {
                    sendMessage(message);
                }
            }
        });
    }

    // Method to send a message
    private void sendMessage(String message) {
        // Simulate sending a message (can integrate with backend or email API)
        Toast.makeText(this, "Message sent: " + message, Toast.LENGTH_LONG).show();

        // Clear the message field after sending
        contactMessage.setText("");
    }
}
