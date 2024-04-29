package com.example.vehiculosjavafx.models;

import java.time.LocalDate;

public class VehicleRent {
    private Vehicle vehicle;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    public VehicleRent() {
    }

    public VehicleRent(Vehicle vehicle, Customer customer, LocalDate startDate, LocalDate endDate, double totalPrice) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
