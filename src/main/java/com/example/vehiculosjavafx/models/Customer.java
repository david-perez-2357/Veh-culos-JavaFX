package com.example.vehiculosjavafx.models;

public class Customer {
    private String nameAndSurname;
    private String nif;
    private String address;
    private String city;

    public Customer() {
    }

    public Customer(String nameAndSurname, String nif, String address, String city) {
        this.nameAndSurname = nameAndSurname;
        this.nif = nif;
        this.address = address;
        this.city = city;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
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

    @Override
    public String toString() {
        return nameAndSurname + " (" + nif + ")";
    }
}
