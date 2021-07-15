package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner state;
    private Spinner type;
    private Spinner tribal;
    private Spinner specialty;

    private static final String TAG = "MainActivity";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference providers = db.collection("provider");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_practitioner);

        // Get the spinners
        state = findViewById(R.id.state);
        type = findViewById(R.id.typeOfDoctor);
        tribal = findViewById(R.id.tribal_info);
        specialty = findViewById(R.id.speciality);

        // Query the database
        providers.get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    ArrayList<String> specialitiesList = new ArrayList<>();
                    ArrayList<String> tribesList = new ArrayList<>();
                    ArrayList<String> statesList = new ArrayList<>();
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
                    ArrayAdapter<String> adapterSpecialties = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, specialitiesList.toArray());
                    specialty.setAdapter(adapterSpecialties);

                    ArrayAdapter<String> adapterType = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.search_drop_list));
                    type.setAdapter(adapterType);

                    ArrayAdapter<String> adapterState = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, statesList.toArray());
                    state.setAdapter(adapterState);

                    ArrayAdapter<String> adapterTribe = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, tribesList.toArray());
                    tribal.setAdapter(adapterTribe);
                }
            });
    }

    public void onClick(View view) {
        // Start a new intent, get the data from the spinners and start a new activity
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("tribe", tribal.getSelectedItem().toString());
        intent.putExtra("state", state.getSelectedItem().toString());
        intent.putExtra("type", type.getSelectedItem().toString());
        intent.putExtra("specialty", specialty.getSelectedItem().toString());
        startActivity(intent);
    }
}