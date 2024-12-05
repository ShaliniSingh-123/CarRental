package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.Booking;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.textCarModel.setText(booking.getCarModel());
        holder.textBookingId.setText("Booking id " + booking.getBookingId());
        holder.textLocation.setText(booking.getLocation());
        holder.textTripStart.setText(booking.getTripStart());
        holder.textTripEnd.setText(booking.getTripEnd());
        holder.textPaidAmount.setText("Paid " + booking.getPaidAmount());
        holder.imageCar.setImageResource(booking.getCarImage());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {

        TextView textCarModel, textBookingId, textLocation, textTripStart, textTripEnd, textPaidAmount;
        ImageView imageCar;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            textCarModel = itemView.findViewById(R.id.textCarModel);
            textBookingId = itemView.findViewById(R.id.textBookingId);
            textLocation = itemView.findViewById(R.id.textLocation);
            textTripStart = itemView.findViewById(R.id.textTripStart);
            textTripEnd = itemView.findViewById(R.id.textTripEnd);
            textPaidAmount = itemView.findViewById(R.id.textPaidAmount);
            imageCar = itemView.findViewById(R.id.imageCar);
        }
    }
}
