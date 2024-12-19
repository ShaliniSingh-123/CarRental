package com.example.myapplication.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.response.Car;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_fragment_my_cars, parent, false); // car_item_layout is your layout for each car
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);

        // Bind car details to the views in the CardView
        bindTextView(holder.carBrand, car.getBrand());
        bindTextView(holder.carModel, car.getModel());
        bindTextView(holder.carYear, String.valueOf(car.getYear()));
        bindTextView(holder.carSeats, String.valueOf(car.getSeats()));
        bindTextView(holder.carFuelType, car.getFuelType());
        bindTextView(holder.carPrice, String.valueOf(car.getPricePerDay()));
        bindTextView(holder.carMilage, String.valueOf(car.getMilage()));
        bindTextView(holder.carColor, car.getColor());
        bindTextView(holder.carAvailability, car.getAvailabilityStatus());

        // Bind features
        holder.carFeatures.setText(String.join(", ", car.getFeatures()));

        // Load image
        if (!car.getImages().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(car.getImages().get(0))
//                    .placeholder(R.drawable.placeholder_image)
//                    .error(R.drawable.error_image)
                    .into(holder.carImage);
        }

        // Bind category/subcategory
        bindTextView(holder.carCategory, car.getCategory());
        bindTextView(holder.carSubCategory, car.getSubCategory());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    // Helper method for setting TextViews
    private void bindTextView(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            textView.setText("N/A");
        } else {
            textView.setText(text);
        }
    }

    public void updateCars(List<Car> newCarList) {
        this.carList = newCarList;
        notifyDataSetChanged();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView carBrand, carModel, carYear, carSeats, carFuelType, carPrice, carMilage, carColor, carAvailability, carFeatures, carCategory, carSubCategory;
        ImageView carImage;

        public CarViewHolder(View itemView) {
            super(itemView);
            carBrand = itemView.findViewById(R.id.carBrand);
            carModel = itemView.findViewById(R.id.carModel);
            carYear = itemView.findViewById(R.id.carYear);
            carSeats = itemView.findViewById(R.id.carSeats);
            carFuelType = itemView.findViewById(R.id.carFuelType);
            carPrice = itemView.findViewById(R.id.carPrice);
            carMilage = itemView.findViewById(R.id.carMilage);
            carColor = itemView.findViewById(R.id.carColor);
            carAvailability = itemView.findViewById(R.id.carAvailability);
            carFeatures = itemView.findViewById(R.id.carFeatures);
            carCategory = itemView.findViewById(R.id.carCategory);
            carSubCategory = itemView.findViewById(R.id.carSubCategory);
            carImage = itemView.findViewById(R.id.carImage);
        }
    }
}

