package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;

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

        Intent intent = getIntent();
        String queryString = intent.getStringExtra("query");


        QuerySearch query = new QuerySearch(activity, providers, queryString);
        Thread thread = new Thread(query, "QuerySearch");
        thread.start();
    }
}