package com.example.myapplication.models.request;

import java.util.List;



import java.util.List;

public class AddCarRequest {

    private String carName;
    private String carModel;
    private String registrationNumber;
    private String subcategory;
    private String category;
    private int seatingCapacity;
    private String fuelType;
    private String transmissionType;
    private double dailyRentalPrice;
    private List<String> features;
    private String pickupLocation;
    private String dropoffLocation;
    private List<String> imageUrls;

    // Default Constructor
    public AddCarRequest() {
    }

    // Parameterized Constructor
    public AddCarRequest(String carName, String carModel, String registrationNumber, String subcategory,
                         String category, int seatingCapacity, String fuelType, String transmissionType,
                         double dailyRentalPrice, List<String> features, String pickupLocation,
                         String dropoffLocation, List<String> imageUrls) {
        this.carName = carName;
        this.carModel = carModel;
        this.registrationNumber = registrationNumber;
        this.subcategory = subcategory;
        this.category = category;
        this.seatingCapacity = seatingCapacity;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.dailyRentalPrice = dailyRentalPrice;
        this.features = features;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.imageUrls = imageUrls;
    }

    // Getters and Setters
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public void setDailyRentalPrice(double dailyRentalPrice) {
        this.dailyRentalPrice = dailyRentalPrice;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}

