package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private Spinner state;
    private Spinner type;
    private Spinner tribal;
    private Spinner specialty;

    private static final String TAG = "MainActivity";

    WeakReference<Activity> activity;

    // Connect to the database and select the provider collection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference providers = db.collection("provider");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_practitioner);

        activity = new WeakReference<>(MainActivity.this);

        // Get the spinners
        state = findViewById(R.id.state);
        type = findViewById(R.id.typeOfDoctor);
        tribal = findViewById(R.id.tribal_info);
        specialty = findViewById(R.id.speciality);

        // In a back thread, query the database to populate the dropdown menus
        QuerySpinner query = new QuerySpinner(activity, providers);
        Thread thread = new Thread(query, "QuerySpinner");
        thread.start();
    }

    public void onClick(View view) {
        // Start a new intent
        Intent intent = new Intent(this, SearchActivity.class);
        String query = "";
        // Get data from the spinners
        query = query.concat(tribal.getSelectedItem().toString())
            .concat(",").concat(state.getSelectedItem().toString())
            .concat(",").concat(type.getSelectedItem().toString())
            .concat(",").concat(specialty.getSelectedItem().toString());
        // Start a new activity and pass it the query elements
        intent.putExtra("query", query);
        startActivity(intent);
    }
}