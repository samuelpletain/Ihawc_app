package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.ref.WeakReference;

public class SearchActivity extends AppCompatActivity {

    WeakReference<Activity> activity;

    private static final String TAG = "SearchActivity-Tag";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference providers = db.collection("provider");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        activity = new WeakReference<>(SearchActivity.this);

        // Fetch data string from intent
        Intent intent = getIntent();
        String queryString = intent.getStringExtra("query");

        // Start background thread to query the database and populate the result RecyclerView
        QuerySearch query = new QuerySearch(activity, providers, queryString);
        Thread thread = new Thread(query, "QuerySearch");
        thread.start();
    }
}