package com.example.ihawcapp;

import android.app.Activity;

import java.lang.ref.WeakReference;

public class ProviderList {
    public String service;
    public String state;
    public String specialty;
    public String tribalAffiliation;
    public WeakReference<Activity> activity;

    public ProviderList(String service, String state, String specialty, String tribalAffiliation, WeakReference<Activity> activity) {
        this.service = service;
        this.state = state;
        this.specialty = specialty;
        this.tribalAffiliation = tribalAffiliation;
        this.activity = activity;
    }

    public void run(){
        //Implement GSON stuff
    }
}
