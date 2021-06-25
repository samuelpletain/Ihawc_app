package com.example.ihawcapp;

import java.util.HashMap;

public class Practitioner extends Provider {
    public String title;
    public String credentials;
    public HashMap<String, String> adresses;
    public Boolean telehealth;
    public String thirdParty;
    public String tribalAffiliation;

    public Practitioner() {

        this.title = title;
        this.credentials = credentials;
        this.adresses = adresses;
        this.telehealth = telehealth;
        this.thirdParty = thirdParty;
        this.tribalAffiliation = tribalAffiliation;
    }

    public Practitioner(String title, String credentials, HashMap<String, String> adresses, Boolean telehealth, String thirdParty, String tribalAffiliation) {
        this.title = title;
        this.credentials = credentials;
        this.adresses = adresses;
        this.telehealth = telehealth;
        this.thirdParty = thirdParty;
        this.tribalAffiliation = tribalAffiliation;
    }

    public String getTitle() {
        return title;
    }

    public String getCredentials() {
        return credentials;
    }

    public HashMap<String, String> getAdresses() {
        return adresses;
    }

    public Boolean getTelehealth() {
        return telehealth;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public String getTribalAffiliation() {
        return tribalAffiliation;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public void setAdresses(HashMap<String, String> adresses) {
        this.adresses = adresses;
    }

    public void setTelehealth(Boolean telehealth) {
        this.telehealth = telehealth;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    public void setTribalAffiliation(String tribalAffiliation) {
        this.tribalAffiliation = tribalAffiliation;
    }
}
