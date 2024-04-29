package com.example.vehiculosjavafx.models;

public class Vehicle {
    private String description;
    private String brand;
    private String tuition; // Matrícula
    private Integer kmDone;
    private Integer pricePerDay;
    private boolean isRented;

    public Vehicle() {
    }

    public Vehicle(String description, String brand, String tuition, Integer kmDone, Integer pricePerDay, boolean isRented) {
        this.description = description;
        this.brand = brand;
        this.tuition = tuition;
        this.kmDone = kmDone;
        this.pricePerDay = pricePerDay;
        this.isRented = isRented;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getTuition() {
        return tuition;
    }

    public Integer getKmDone() {
        return kmDone;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public boolean isRented() {
        return isRented;
    }

    public String toString() {
        return description + " (" + tuition + ")";
    }
}
