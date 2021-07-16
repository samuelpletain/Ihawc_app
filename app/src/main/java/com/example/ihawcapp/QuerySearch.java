package com.example.ihawcapp;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.lang.ref.WeakReference;

public class QuerySearch implements Runnable {
    private static final String TAG = "Query";

    WeakReference<Activity> activity;
    CollectionReference providers;
    String tribe;
    String state;
    String type;
    String specialty;
    String res;

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
            Log.d(TAG, "In the loop, with " + state);
            q = q.whereEqualTo("state", state);
        }
        if (!type.equals("Clinics and Practitioners")) {
            Log.d(TAG, "In the loop, with " + type);
            q = q.whereEqualTo("type", type);
        }
        if (!tribe.equals("Tribal Affiliation")) {
            Log.d(TAG, "In the loop, with " + tribe);
            q = q.whereEqualTo("tribalAffiliation", tribe);
        }
        q.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        res = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            res = res.concat(documentSnapshot.getId()).concat(": ").concat(documentSnapshot.get("state").toString()) + "\n";
                        }
                        activity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView results = activity.get().findViewById(R.id.results);
                                results.setText(res);
                            }
                        });
                    }

                });
    }
}
