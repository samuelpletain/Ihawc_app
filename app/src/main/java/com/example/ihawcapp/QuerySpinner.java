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

    public QuerySpinner(WeakReference<Activity> activity, CollectionReference providers) {
        this.activity = activity;
        this.providers = providers;
    }

    @Override
    public void run() {
        providers.get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
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
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<String> specialitiesList = new ArrayList<>();
                        ArrayList<String> tribesList = new ArrayList<>();
                        ArrayList<String> statesList = new ArrayList<>();
                        tribesList.add("Tribal Affiliation");
                        statesList.add("State");
                        specialitiesList.add("Field");
                        // Loop through each document and populate each of the 3 arrays above if the data is not already in the array
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String specialities = documentSnapshot.get("specialties").toString();
                            String state = documentSnapshot.get("state").toString();
                            String tribalAffiliation = documentSnapshot.get("tribalAffiliation").toString();
                            for (String specialty : specialities.split(", ")) {
                                if (!specialitiesList.contains(specialty)) {
                                    specialitiesList.add(specialty);
                                }
                            }
                            if (!tribesList.contains(tribalAffiliation)) {
                                tribesList.add(tribalAffiliation);
                            }
                            if (!statesList.contains(state)) {
                                statesList.add(state);
                            }
                        }
                        // Populate the spinners
                        activity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Spinner state = activity.get().findViewById(R.id.state);
                                Spinner type = activity.get().findViewById(R.id.typeOfDoctor);
                                Spinner tribal = activity.get().findViewById(R.id.tribal_info);
                                Spinner specialty = activity.get().findViewById(R.id.speciality);


                                ArrayAdapter<String> adapterSpecialties = new ArrayAdapter<>(activity.get(), android.R.layout.simple_spinner_dropdown_item, (String[]) specialitiesList.toArray());
                                specialty.setAdapter(adapterSpecialties);

                                ArrayAdapter<String> adapterType = new ArrayAdapter<>(activity.get(), android.R.layout.simple_spinner_dropdown_item, activity.get().getResources().getStringArray(R.array.search_drop_list));
                                type.setAdapter(adapterType);

                                ArrayAdapter<String> adapterState = new ArrayAdapter<>(activity.get(), android.R.layout.simple_spinner_dropdown_item, (String[]) statesList.toArray());
                                state.setAdapter(adapterState);

                                ArrayAdapter<String> adapterTribe = new ArrayAdapter<>(activity.get(), android.R.layout.simple_spinner_dropdown_item, (String[]) tribesList.toArray());
                                tribal.setAdapter(adapterTribe);
                            }
                        });

                    }
                });
    }
}
