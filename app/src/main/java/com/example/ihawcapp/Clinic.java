package com.example.ihawcapp;

import java.util.HashMap;

public class Clinic extends Provider {
    public String network;
    public String address;
    public String website;

    public Clinic(String name, String field, String specialty, String email, String phone, String network, String address, String website) {

        this.network = this.network;
        this.address = this.address;
        this.website = this.website;
    }



    public Clinic() {
        this.network = this.network;
        this.address = this.address;
        this.website = this.website;
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


    //Robert made the setters in order to pass create thos fields in a new class
    public void setNetwork(String network) {
        this.network = network;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
