package com.example.ihawcapp;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class QuerySpinner implements Runnable {
    WeakReference<Activity> activity;
    CollectionReference providers;

    /**
     * Class constructor.
     */
    public QuerySpinner(WeakReference<Activity> activity, CollectionReference providers) {
        this.activity = activity;
        this.providers = providers;
    }

    /**
     * Query the database and create 3 arrays (state, tribalAffiliation and specialties) with every unique occurrence from that field.
     * Populate the spinners on the UI thread with the results with the help of an ArrayAdapter.
     */
    @Override
    public void run() {
        // Query the database
        providers.get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    // If the query fails, start the FailureLoadingActivity
                    public void onFailure(@NonNull Exception e) {
                        activity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(activity.get(), FailureLoadingActivity.class);
                                activity.get().startActivity(intent);
                            }
                        });
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    // Otherwise...
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // Create arrays to hold the results
                        ArrayList<String> specialitiesList = new ArrayList<>();
                        ArrayList<String> tribesList = new ArrayList<>();
                        ArrayList<String> statesList = new ArrayList<>();
                        // Add the "header" data that will be displayed on the spinner when the activity is launched
                        tribesList.add("Tribal Affiliation");
                        statesList.add("State");
                        specialitiesList.add("Field");
                        // Loop through each document and populate each of the 3 arrays above if the data is not already in the array
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String specialities = documentSnapshot.get("specialties").toString();
                            String state = documentSnapshot.get("state").toString();
                            String tribalAffiliation = documentSnapshot.get("tribalAffiliation").toString();
                            // Since Firestore doesn't have a contains request, we need to loop through the list of specialities after splitting the string
                            for (String specialty : specialities.split(", ")) {
                                // If the specialty is not in the array already, we add it
                                if (!specialitiesList.contains(specialty)) {
                                    specialitiesList.add(specialty);
                                }
                            }
                            // If the tribe is not in the array already, we add it
                            if (!tribesList.contains(tribalAffiliation)) {
                                tribesList.add(tribalAffiliation);
                            }
                            // If the state is not in the array already, we add it
                            if (!statesList.contains(state)) {
                                statesList.add(state);
                            }
                        }
                        // Populate the spinners
                        activity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Find the spinners
                                Spinner state = activity.get().findViewById(R.id.state);
                                Spinner type = activity.get().findViewById(R.id.typeOfDoctor);
                                Spinner tribal = activity.get().findViewById(R.id.tribal_info);
                                Spinner specialty = activity.get().findViewById(R.id.speciality);

                                // For each spinner, use an adapter to display the data array
                                ArrayAdapter<String> adapterSpecialties = new ArrayAdapter(activity.get(), android.R.layout.simple_spinner_dropdown_item, specialitiesList.toArray());
                                specialty.setAdapter(adapterSpecialties);

                                ArrayAdapter<String> adapterType = new ArrayAdapter(activity.get(), android.R.layout.simple_spinner_dropdown_item, activity.get().getResources().getStringArray(R.array.search_drop_list));
                                type.setAdapter(adapterType);

                                ArrayAdapter<String> adapterState = new ArrayAdapter(activity.get(), android.R.layout.simple_spinner_dropdown_item, statesList.toArray());
                                state.setAdapter(adapterState);

                                ArrayAdapter<String> adapterTribe = new ArrayAdapter(activity.get(), android.R.layout.simple_spinner_dropdown_item, tribesList.toArray());
                                tribal.setAdapter(adapterTribe);
                            }
                        });

                    }
                });
    }
}
