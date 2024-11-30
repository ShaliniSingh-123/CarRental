package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SignupDashboardActivity extends AppCompatActivity {

    // Declare the CardViews
    private CardView customerCard, partnerCard, driverCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_dashboard);  // Your layout file

        // Initialize the CardViews
        customerCard = findViewById(R.id.customerCard);
        partnerCard = findViewById(R.id.partnerCard);
        driverCard = findViewById(R.id.driverCard);

        // Set click listeners for each card
        customerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity("Customer");
            }
        });

        partnerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity("Partner");
            }
        });

        driverCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity("Driver");
            }
        });
    }

    // Method to open RegisterActivity with a specific role
    private void openRegisterActivity(String role) {
        Intent intent;

        // Decide which activity to open based on the role
        switch (role) {
            case "Customer":
                intent = new Intent(SignupDashboardActivity.this, SignupActivity.class);
                break;
            case "Partner":
                intent = new Intent(SignupDashboardActivity.this, PartnerSignupActivity.class);
                break;
            case "Driver":
                intent = new Intent(SignupDashboardActivity.this, DriverSignupActivity.class);
                break;
            default:
                // Fallback in case of invalid role (Optional)
                return;
        }

        // Pass the role as extra (optional, if needed in the target activity)
        intent.putExtra("role", role);
        startActivity(intent);
    }
}
