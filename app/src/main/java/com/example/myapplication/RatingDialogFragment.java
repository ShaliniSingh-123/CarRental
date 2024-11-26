package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RatingDialogFragment extends DialogFragment {

    private RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the dialog layout
        View view = inflater.inflate(R.layout.dialog_rating, container, false);

        // Initialize the RatingBar
        ratingBar = view.findViewById(R.id.ratingBar);

        // Set the Submit button click listener
        Button submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            // Show a Toast or handle the rating value
            Toast.makeText(getActivity(), "You rated: " + rating + " stars", Toast.LENGTH_SHORT).show();
            dismiss(); // Close the dialog
        });

        // Set the Skip button click listener
        Button skipButton = view.findViewById(R.id.skipButton);
        skipButton.setOnClickListener(v -> dismiss()); // Just close the dialog when skip is clicked

        return view;
    }
}
