package com.example.myapplication.models;

public class Booking {
    private String carModel;
    private String bookingId;
    private String tripStart;
    private String tripEnd;
    private String paidAmount;
    private String location;
    private int carImage;

    public Booking(String carModel, String bookingId, String tripStart, String tripEnd, String paidAmount, String location, int carImage) {
        this.carModel = carModel;
        this.bookingId = bookingId;
        this.tripStart = tripStart;
        this.tripEnd = tripEnd;
        this.paidAmount = paidAmount;
        this.location = location;
        this.carImage = carImage;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getTripStart() {
        return tripStart;
    }

    public String getTripEnd() {
        return tripEnd;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public String getLocation() {
        return location;
    }

    public int getCarImage() {
        return carImage;
    }
}
