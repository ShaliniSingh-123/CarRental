package com.example.myapplication.models.response;

import java.util.List;

public class GetCarByUserResponse {
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "GetCarByUserResponse{cars=" + cars + '}';
    }
}

