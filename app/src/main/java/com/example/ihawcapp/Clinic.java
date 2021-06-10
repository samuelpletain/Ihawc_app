package com.example.ihawcapp;

import java.util.HashMap;

public class Clinic extends Provider {
    public String network;
    public String address;
    public String website;

    public Clinic(String name, String field, String specialties, String email, String phone, HashMap<String, String> hours, String network, String address, String website) {
        super(name, field, specialties, email, phone, hours);
        this.network = network;
        this.address = address;
        this.website = website;
    }

    public String getNetwork() {
        return network;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }
}
