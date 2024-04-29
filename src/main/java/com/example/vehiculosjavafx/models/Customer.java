package com.example.vehiculosjavafx.models;

public class Customer {
    private String name;
    private String surname;
    private String nif;
    private String address;
    private String city;

    public Customer() {
    }

    public Customer(String name, String surname, String nif, String address, String city) {
        this.name = name;
        this.surname = surname;
        this.nif = nif;
        this.address = address;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNif() {
        return nif;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
