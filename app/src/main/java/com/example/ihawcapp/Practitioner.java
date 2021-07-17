package com.example.ihawcapp;

import android.widget.EditText;

import java.util.HashMap;

public class Practitioner extends Provider {
    public String title;
    public String credentials;
    public String adresses; //possible hashmap later???
    public Boolean telehealth;
    public String thirdParty;

    public Practitioner() {
    }

    public Practitioner(String title, String credentials, String adresses, Boolean telehealth, String thirdParty) {
        this.title = title;
        this.credentials = credentials;
        this.adresses = adresses;
        this.telehealth = telehealth;
        this.thirdParty = thirdParty;
    }

    public String getTitle() {
        return title;
    }

    public String getCredentials() {
        return credentials;
    }

    public String getAdresses() {
        return adresses;
    }

    public Boolean getTelehealth() {
        return telehealth;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public void setAdresses(String adresses) {
        this.adresses = adresses;
    }

    public void setTelehealth(Boolean telehealth) {
        this.telehealth = telehealth;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }
}
