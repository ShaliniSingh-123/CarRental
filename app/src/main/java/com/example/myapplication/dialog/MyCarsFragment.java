package com.example.myapplication.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CarAdapter;  // Assuming you have an adapter for cars
import com.example.myapplication.models.response.Car;  // Assuming you have a Car model class
import java.util.List;

public class MyCarsFragment extends Fragment {

    private ImageView editIcon, deleteIcon;
    private RecyclerView carsRecyclerView;
    private CarAdapter carAdapter;  // Assuming you have a car adapter to display cars

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_fragment_my_cars, container, false);

        // Initialize ImageView objects
        editIcon = view.findViewById(R.id.editIcon);
        deleteIcon = view.findViewById(R.id.deleteIcon);

        // Initialize RecyclerView
        carsRecyclerView = view.findViewById(R.id.carsRecyclerView);  // Make sure RecyclerView ID matches

        // Set the RecyclerView layout manager
        carsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set click listeners for edit and delete actions
        editIcon.setOnClickListener(v -> {
            // Code to handle edit action
            editCar();
        });

        deleteIcon.setOnClickListener(v -> {
            // Code to handle delete action
            deleteCar();
        });

        return view;
    }

    // Method to update the list of cars in the RecyclerView
    public void updateCarList(List<Car> carList) {
        if (carAdapter == null) {
            carAdapter = new CarAdapter(carList);  // Initialize adapter with the car list
            carsRecyclerView.setAdapter(carAdapter);  // Set the adapter for the RecyclerView
        } else {
            carAdapter.updateCars(carList);  // If the adapter already exists, update the car list
        }
    }

    private void editCar() {
        // Add code to edit the car details
        // For example, show a dialog to edit car details
    }

    private void deleteCar() {
        // Add code to delete the car
        // For example, show a confirmation dialog and delete the car from the database
    }
}
