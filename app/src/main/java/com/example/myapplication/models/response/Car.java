package com.example.myapplication.models.response;

import java.util.List;

public class Car {
    private String brand;
    private String model;
    private int year;
    private int seats;
    private String fuelType;
    private double pricePerDay;
    private double milage;
    private String color;
    private String availabilityStatus;
    private List<String> features;
    private String category;
    private String subCategory;
    private List<String> images; // List of image URLs

    // Getters and setters for all fields

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getSeats() { return seats; }
    public String getFuelType() { return fuelType; }
    public double getPricePerDay() { return pricePerDay; }
    public double getMilage() { return milage; }
    public String getColor() { return color; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public List<String> getFeatures() { return features; }
    public String getCategory() { return category; }
    public String getSubCategory() { return subCategory; }
    public List<String> getImages() { return images; }
}
