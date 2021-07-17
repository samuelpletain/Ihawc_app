package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.ref.WeakReference;

public class SingleResultActivity extends AppCompatActivity {

    private WeakReference<Activity> activity;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference providers = db.collection("provider");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String documentId = intent.getStringExtra("ID");
        Log.d("singleResultActivity", documentId);

        activity = new WeakReference<>(SingleResultActivity.this);

        QuerySingle query = new QuerySingle(activity, providers, documentId);
        Thread thread = new Thread(query, "QuerySingle");
        thread.start();
    }
}