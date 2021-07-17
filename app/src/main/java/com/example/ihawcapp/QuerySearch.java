package com.example.ihawcapp;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

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
        Query q = FirebaseFirestore.getInstance().collection("provider");
        if (!state.equals("State")) {
            q = q.whereEqualTo("state", state);
        }
        if (!type.equals("Clinics and Practitioners")) {
            q = q.whereEqualTo("type", type);
        }
        if (!tribe.equals("Tribal Affiliation")) {
            q = q.whereEqualTo("tribalAffiliation", tribe);
        }
        q.get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    String data = "";
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if (documentSnapshot.get("specialties").toString().contains(specialty) || specialty.equals("Field")) {
                            data = data.concat(documentSnapshot.get("name").toString() + ";");
                            ids.add(documentSnapshot.getId());
                        }
                    }
                    res = data.split(";");
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
