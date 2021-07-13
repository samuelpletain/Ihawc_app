package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reg_Choice extends AppCompatActivity implements View.OnClickListener {
    private Button patient_choice, practicioner_choice, clinic_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_choice);

        patient_choice = (Button)findViewById(R.id.patient_choice);
        practicioner_choice = (Button)findViewById(R.id.practicioner_choice);
        clinic_choice = (Button)findViewById(R.id.clinic_choice);
        patient_choice.setOnClickListener(this);
        practicioner_choice.setOnClickListener(this);
        clinic_choice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.patient_choice:
                startActivity(new Intent(this, RegisterPatient2.class));
                break;
            case R.id.practicioner_choice:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.clinic_choice:
                startActivity(new Intent(this, RegisterClinic2.class));
                break;
        }

    }
}