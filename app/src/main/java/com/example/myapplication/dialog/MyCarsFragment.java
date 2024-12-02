package com.example.myapplication.dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;

public class MyCarsFragment extends Fragment {

    private ImageView editIcon, deleteIcon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_fragment_my_cars, container, false);

        // Initialize ImageView objects
        editIcon = view.findViewById(R.id.editIcon);
        deleteIcon = view.findViewById(R.id.deleteIcon);

        // Set click listeners
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

    private void editCar() {
        // Add code to edit the car details
        // For example, show a dialog to edit car details
    }

    private void deleteCar() {
        // Add code to delete the car
        // For example, show a confirmation dialog and delete the car from the database
    }
}
