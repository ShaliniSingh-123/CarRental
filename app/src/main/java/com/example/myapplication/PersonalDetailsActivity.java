package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        // Set up close button to finish the activity
        ImageView btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> finish());

        // Example: Display data (You may want to fetch actual user data from the database)
        TextView tvFirstName = findViewById(R.id.tvFirstName);
        TextView tvLastName = findViewById(R.id.tvLastName);
        TextView tvDob = findViewById(R.id.tvDob);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvPhone = findViewById(R.id.tvPhone);

        // Set example data, replace with actual data retrieval if needed
        tvFirstName.setText("Shalini");
        tvLastName.setText("Singh");
        tvDob.setText("12/05/2002");
        tvEmail.setText("shalinisingh954856@gmail.com");
        tvPhone.setText("095485 62581");
    }
}
