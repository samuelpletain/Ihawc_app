package com.example.ihawcapp;

import java.util.HashMap;

public class Practitioner extends Provider {
    public String title;
    public String credentials;
    public HashMap<String, String> adresses;
    public Boolean telehealth;
    public String thirdParty;
    public String tribalAffiliation;

    public Practitioner(String name, String field, String specialties, String email, String phone, HashMap<String, String> hours, String title, String credentials, HashMap<String, String> adresses, Boolean telehealth, String thirdParty, String tribalAffiliation) {
        super(name, field, specialties, email, phone, hours);
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
}
