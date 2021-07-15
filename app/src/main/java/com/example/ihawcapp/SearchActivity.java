package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        test = findViewById(R.id.results);
        Intent intent = getIntent();
        String state = intent.getStringExtra("state");
        String tribe = intent.getStringExtra("tribe");
        String type = intent.getStringExtra("type");
        String specialty = intent.getStringExtra("specialty");
        String res = state + " " + tribe + " " + type + " " + specialty;
        test.setText(res);
    }
}