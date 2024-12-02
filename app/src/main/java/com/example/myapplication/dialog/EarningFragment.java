package com.example.myapplication.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activity.AddCarActivity; // Make sure AddCarActivity is imported

public class EarningFragment extends Fragment {

    private ImageView addCarButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_fragment_earning, container, false);

        // Find the ImageView for the "Add Your Car" button
        addCarButton = view.findViewById(R.id.btn_add_car);  // Ensure this matches the ID in the layout

        // Set an OnClickListener for the ImageView
        addCarButton.setOnClickListener(v -> {
            // Create an Intent to start the AddCarActivity
            Intent intent = new Intent(getActivity(), AddCarActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
