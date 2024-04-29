package com.example.vehiculosjavafx.models;

public class Vehicle {
    private int id;
    private String description;
    private String brand;
    private String tuition; // Matrícula
    private double kmDone;
    private double pricePerDay;
    private boolean isRented;

    public Vehicle() {
    }

    public Vehicle(int id, String description, String brand, String tuition, double kmDone, double pricePerDay, boolean isRented) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.tuition = tuition;
        this.kmDone = kmDone;
        this.pricePerDay = pricePerDay;
        this.isRented = isRented;
    }

    public int getId() {
        return id;
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

    public double getKmDone() {
        return kmDone;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isRented() {
        return isRented;
    }
}
