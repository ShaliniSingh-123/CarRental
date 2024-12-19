package com.example.myapplication.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Car implements Parcelable {
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
    private List<String> images;

    // Default constructor
    public Car() {}

    // Getters and setters
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }
    public double getMilage() { return milage; }
    public void setMilage(double milage) { this.milage = milage; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }
    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getSubCategory() { return subCategory; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    // Parcelable implementation
    protected Car(Parcel in) {
        brand = in.readString();
        model = in.readString();
        year = in.readInt();
        seats = in.readInt();
        fuelType = in.readString();
        pricePerDay = in.readDouble();
        milage = in.readDouble();
        color = in.readString();
        availabilityStatus = in.readString();
        features = in.createStringArrayList();
        category = in.readString();
        subCategory = in.readString();
        images = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeInt(year);
        dest.writeInt(seats);
        dest.writeString(fuelType);
        dest.writeDouble(pricePerDay);
        dest.writeDouble(milage);
        dest.writeString(color);
        dest.writeString(availabilityStatus);
        dest.writeStringList(features);
        dest.writeString(category);
        dest.writeString(subCategory);
        dest.writeStringList(images);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}
