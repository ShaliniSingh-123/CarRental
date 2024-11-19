package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingInformationActivity extends AppCompatActivity {

    private ImageView carImage;
    private Switch switchDriver;
    private EditText pickUpLocation, returnLocation, pickUpDateTime, returnDateTime;
    private Button btnContinue;
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_information);

        // Initialize views
        carImage = findViewById(R.id.carImage);
        switchDriver = findViewById(R.id.switchDriver);
        pickUpLocation = findViewById(R.id.pickUpLocation);
        returnLocation = findViewById(R.id.returnLocation);
        pickUpDateTime = findViewById(R.id.pickUpDateTime);
        returnDateTime = findViewById(R.id.returnDateTime);
        btnContinue = findViewById(R.id.btnContinue);
        backButton = findViewById(R.id.back_button);

        // Back button functionality
        backButton.setOnClickListener(v -> finish());

        // Set click listeners for date and time pickers
        pickUpDateTime.setOnClickListener(v -> showDateTimePicker((dateTime) -> pickUpDateTime.setText(dateTime)));
        returnDateTime.setOnClickListener(v -> showDateTimePicker((dateTime) -> returnDateTime.setText(dateTime)));

        // Example functionality for Continue button
        btnContinue.setOnClickListener(v -> {
            String pickUp = pickUpLocation.getText().toString();
            String returnLoc = returnLocation.getText().toString();
            String pickUpTime = pickUpDateTime.getText().toString();
            String returnTime = returnDateTime.getText().toString();

            String message = "Pickup: " + pickUp + "\nReturn: " + returnLoc +
                    "\nPickup DateTime: " + pickUpTime + "\nReturn DateTime: " + returnTime;
            Toast.makeText(BookingInformationActivity.this, message, Toast.LENGTH_LONG).show();
        });
    }

    // Method to show DatePicker and TimePicker dialogs
    private void showDateTimePicker(OnDateTimeSelectedListener listener) {
        final Calendar calendar = Calendar.getInstance();

        // Show DatePickerDialog
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // Show TimePickerDialog after date is selected
            new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                // Format and return the selected date and time
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                String formattedDateTime = sdf.format(calendar.getTime());
                listener.onDateTimeSelected(formattedDateTime);
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    // Interface for handling date and time selection
    private interface OnDateTimeSelectedListener {
        void onDateTimeSelected(String dateTime);
    }
}
