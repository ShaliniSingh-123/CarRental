package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddMiniBioActivity extends AppCompatActivity {

    private EditText bioEditText;
    private Button saveBioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mini_bio);

        bioEditText = findViewById(R.id.bioEditText);
        saveBioButton = findViewById(R.id.saveBioButton);

        saveBioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bio = bioEditText.getText().toString();
                // Save the bio (e.g., save to database or shared preferences)
                Toast.makeText(AddMiniBioActivity.this, "Bio saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
