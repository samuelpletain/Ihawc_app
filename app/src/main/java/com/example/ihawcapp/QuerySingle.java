package com.example.ihawcapp;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class QuerySingle implements Runnable {

    private static final String TAG = "QuerySingle";

    WeakReference<Activity> activity;
    CollectionReference providers;
    String id;
    Practitioner practitioner;
    Clinic clinic;

    public QuerySingle(WeakReference<Activity> activity, CollectionReference providers, String id) {
        this.activity = activity;
        this.providers = providers;
        this.id = id;
    }

    @Override
    public void run() {
        DocumentReference document = providers.document(id);
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot task) {
                if (task.get("type").toString().equals("practitioner")) {
                    practitioner = task.toObject(Practitioner.class);

                    Log.d(TAG, practitioner.toString());
                    activity.get().runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            activity.get().setContentView(R.layout.activity_full_info_practitioner);

                            TextView nameTextView = activity.get().findViewById(R.id.name);
                            if (Objects.nonNull(practitioner.getName())){
                                String name = practitioner.getName();
                                nameTextView.setText(name);
                            } else {
                                nameTextView.setVisibility(View.GONE);
                            }

                            TextView fieldTextView = activity.get().findViewById(R.id.field);
                            if (Objects.nonNull(practitioner.getField())){
                                String field = practitioner.getField();
                                fieldTextView.setText(field);
                            } else {
                                fieldTextView.setVisibility(View.GONE);
                            }

                            TextView titleTextView = activity.get().findViewById(R.id.titles);
                            if (Objects.nonNull(practitioner.getTitle())){
                                String title = practitioner.getTitle();
                                titleTextView.setText(title);
                            } else {
                                titleTextView.setVisibility(View.GONE);
                            }

                            TextView specialitiesTextView = activity.get().findViewById(R.id.specialties);
                            if (Objects.nonNull(practitioner.getSpecialties())){
                                String specialties = practitioner.getSpecialties();
                                specialitiesTextView.setText(specialties);
                            } else {
                                specialitiesTextView.setVisibility(View.GONE);
                            }

                            TextView credentialsTextView = activity.get().findViewById(R.id.credentials);
                            if (Objects.nonNull(practitioner.getCredentials())){
                                String credentials = practitioner.getCredentials();
                                credentialsTextView.setText(credentials);
                            } else {
                                credentialsTextView.setVisibility(View.GONE);
                            }

                            TextView emailTextView = activity.get().findViewById(R.id.email);
                            if (Objects.nonNull(practitioner.getEmail())){
                                String email = practitioner.getEmail();
                                emailTextView.setText(email);
                            } else {
                                emailTextView.setVisibility(View.GONE);
                            }

                            TextView phoneTextView = activity.get().findViewById(R.id.phone);
                            if (Objects.nonNull(practitioner.getPhone())){
                                String phone = practitioner.getPhone();
                                phoneTextView.setText(phone);
                            } else {
                                phoneTextView.setVisibility(View.GONE);
                            }

                            TextView adressesTextView = activity.get().findViewById(R.id.addresses);
                            if (Objects.nonNull(practitioner.getAdresses())){
                                String adresses = practitioner.getAdresses(); //possible hashmap later???
                                adressesTextView.setText(adresses);
                            } else {
                                adressesTextView.setVisibility(View.GONE);
                            }

                            CheckBox telehealthCheckBox = activity.get().findViewById(R.id.telehealth);
                            if (Objects.nonNull(practitioner.getAdresses())){
                                Boolean telehealth = practitioner.getTelehealth();
                                telehealthCheckBox.setChecked(telehealth);
                            } else {
                                telehealthCheckBox.setChecked(false);
                            }

                            TextView thirdPartyTextView = activity.get().findViewById(R.id.third);
                            if (Objects.nonNull(practitioner.getThirdParty())){
                                String thirdParty = practitioner.getThirdParty();
                                thirdPartyTextView.setText(thirdParty);
                            } else {
                                thirdPartyTextView.setVisibility(View.GONE);
                            }

                            TextView hoursTextView = activity.get().findViewById(R.id.hours);
                            if (Objects.nonNull(practitioner.getHours())){
                                String hours = practitioner.getHours();
                                hoursTextView.setText(hours);
                            } else {
                                hoursTextView.setVisibility(View.GONE);
                            }

                            TextView tribalAffiliationTextView = activity.get().findViewById(R.id.tribal);
                            if (Objects.nonNull(practitioner.getTribalAffiliation())){
                                String tribalAffiliation = practitioner.getTribalAffiliation();
                                tribalAffiliationTextView.setText(tribalAffiliation);
                            } else {
                                tribalAffiliationTextView.setVisibility(View.GONE);
                            }
                        }
                    });

                } else if (task.get("type").toString().equals("clinic")) {
                    clinic = task.toObject(Clinic.class);

                    activity.get().runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            activity.get().setContentView(R.layout.activity_full_info_clinic);

                            TextView nameTextView = activity.get().findViewById(R.id.nameClinic);
                            if (Objects.nonNull(clinic.getName())){
                                String name = clinic.getName();
                                nameTextView.setText(name);
                            } else {
                                nameTextView.setVisibility(View.GONE);
                            }

                            TextView websiteTextView = activity.get().findViewById(R.id.websiteClinic);
                            if (Objects.nonNull(clinic.getWebsite())){
                                String field = clinic.getWebsite();
                                websiteTextView.setText(field);
                            } else {
                                websiteTextView.setVisibility(View.GONE);
                            }

                            TextView networkTextView = activity.get().findViewById(R.id.networkClinic);
                            if (Objects.nonNull(clinic.getNetwork())){
                                String title = clinic.getNetwork();
                                networkTextView.setText(title);
                            } else {
                                networkTextView.setVisibility(View.GONE);
                            }

                            TextView specialitiesTextView = activity.get().findViewById(R.id.servicesClinic);
                            if (Objects.nonNull(clinic.getSpecialties())){
                                String specialties = clinic.getSpecialties();
                                specialitiesTextView.setText(specialties);
                            } else {
                                specialitiesTextView.setVisibility(View.GONE);
                            }

                            TextView emailTextView = activity.get().findViewById(R.id.emailClinic);
                            if (Objects.nonNull(clinic.getEmail())){
                                String email = clinic.getEmail();
                                emailTextView.setText(email);
                            } else {
                                emailTextView.setVisibility(View.GONE);
                            }

                            TextView phoneTextView = activity.get().findViewById(R.id.phoneClinic);
                            if (Objects.nonNull(clinic.getPhone())){
                                String phone = clinic.getPhone();
                                phoneTextView.setText(phone);
                            } else {
                                phoneTextView.setVisibility(View.GONE);
                            }

                            TextView adressesTextView = activity.get().findViewById(R.id.addressClinic);
                            if (Objects.nonNull(clinic.getAddress())){
                                String adresses = clinic.getAddress(); //possible hashmap later???
                                adressesTextView.setText(adresses);
                            } else {
                                adressesTextView.setVisibility(View.GONE);
                            }

                            TextView hoursTextView = activity.get().findViewById(R.id.hoursClinic);
                            if (Objects.nonNull(clinic.getHours())){
                                String hours = clinic.getHours();
                                hoursTextView.setText(hours);
                            } else {
                                hoursTextView.setVisibility(View.GONE);
                            }

                            TextView tribalAffiliationTextView = activity.get().findViewById(R.id.tribalClinic);
                            if (Objects.nonNull(clinic.getTribalAffiliation())){
                                String tribalAffiliation = clinic.getTribalAffiliation();
                                tribalAffiliationTextView.setText(tribalAffiliation);
                            } else {
                                tribalAffiliationTextView.setVisibility(View.GONE);
                            }
                        }
                    });

                }

            }
        });
    }
}
