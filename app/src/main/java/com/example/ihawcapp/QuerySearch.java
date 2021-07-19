package com.example.ihawcapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class QuerySearch implements Runnable {
    private static final String TAG = "QuerySearch";

    WeakReference<Activity> activity;
    CollectionReference providers;
    String tribe;
    String state;
    String type;
    String specialty;
    String[] res;
    ArrayList<String> ids = new ArrayList<>();

    public QuerySearch(WeakReference<Activity> activity, CollectionReference providers, String query) {
        this.activity = activity;
        this.providers = providers;
        // Split the query string provided by the intent and assign it to a variable
        String[] res = query.split(",");
        this.tribe = res[0];
        this.state = res[1];
        if (res[2].equals("Clinics Only")) {
            this.type = "clinic";
        } else if (res[2].equals("Practitioners Only")) {
            this.type = "practitioner";
        } else {
            this.type = res[2];
        }
        this.specialty = res[3];
    }

    @Override
    public void run() {
        // We create a query and add filters to it based on user input
        Query q = FirebaseFirestore.getInstance().collection("provider");
        // Filter the results if a state is selected by the user
        if (!state.equals("State")) {
            q = q.whereEqualTo("state", state);
        }
        // Filter the results if a type is selected by the user
        if (!type.equals("Clinics and Practitioners")) {
            q = q.whereEqualTo("type", type);
        }
        // Filter the results if a tribe is selected by the user
        if (!tribe.equals("Tribal Affiliation")) {
            q = q.whereEqualTo("tribalAffiliation", tribe);
        }
        // Query the database
        q.orderBy("name").get()
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
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                // If the query succeeds...
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    String data = "";
                    // Loop through each document returned
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        // Populate the result String with the data from the document only if the specialty is included in the document or if no filter was applied
                        if (documentSnapshot.get("specialties").toString().contains(specialty) || specialty.equals("Field")) {
                            // Create the data string if
                            data = data.concat(documentSnapshot.get("name").toString()
                                    .concat(" - ")
                                    .concat(documentSnapshot.get("tribalAffiliation").toString())
                                    .concat("\n"));
                            // Since the address field is different between clinic and practitioner, we need to check which one is present in the document before adding it to the result string
                            if (Objects.nonNull(documentSnapshot.get("address"))) {
                                data = data.concat(documentSnapshot.get("address").toString());
                            } else if (Objects.nonNull(documentSnapshot.get("adresses"))) {
                                data = data.concat(documentSnapshot.get("adresses").toString());
                            }
                            data = data.concat(";");
                            // Add the id of the document to the global array ids
                            ids.add(documentSnapshot.getId());
                        }
                    }
                    // Create the result array from the data string
                    res = data.split(";");
                    // Populate the recyclerview from the UI thread with the help of the custom adapter
                    activity.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProviderAdapter customAdapter = new ProviderAdapter(res, ids, activity);
                            RecyclerView recyclerView = activity.get().findViewById(R.id.recycler_results);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(activity.get()));
                            recyclerView.setAdapter(customAdapter);
                        }
                    });
                }
            });
    }
}
