package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FailureLoadingActivity extends AppCompatActivity {

    /**
     * Set the activity layout.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure_loading);
    }

    /**
     * Start a new activity that will display an error message.
     * @param view  The current view.
     */
    public void reload(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}