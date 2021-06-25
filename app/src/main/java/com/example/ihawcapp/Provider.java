package com.example.ihawcapp;

import java.util.HashMap;

public class Provider {


    public String name;
    public String field;
    public String specialties;
    public String email;
    public String phone;
    public HashMap<String, String> hours;

    public Provider(String name, String field, String specialties, String email, String phone, HashMap<String, String> hours) {
        this.name = name;
        this.field = field;
        this.specialties = specialties;
        this.email = email;
        this.phone = phone;
        this.hours = hours;
    }
    public Provider() {
        this.name = name;
        this.field = field;
        this.specialties = specialties;
        this.email = email;
        this.phone = phone;
        this.hours = hours;
    }



    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public String getSpecialties() {
        return specialties;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public HashMap<String, String> getHours() {
        return hours;
    }
    //Robert created setters in order to create the object needed to pass to the database.

    public void setName(String name) {
        this.name = name;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setHours(HashMap<String, String> hours) {
        this.hours = hours;
    }
}
