package com.example.myapplication.activity;

import com.google.gson.annotations.SerializedName;

public class Car {
    private String brand;
    private String model;
    private int year;
    private int seats;
    private String fuelType;
    private int pricePerDay;
    private int mileage;
    private String color;
    private String availabilityStatus;

    // Getter methods for each field
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getSeats() {
        return seats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public int getMileage() {
        return mileage;
    }

    public String getColor() {
        return color;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }
}
