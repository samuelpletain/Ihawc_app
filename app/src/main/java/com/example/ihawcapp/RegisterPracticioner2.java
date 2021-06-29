package com.example.ihawcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegisterPracticioner2 extends AppCompatActivity {
    private EditText reg_title,reg_credentials, reg_affiliation, reg_hours, reg_specialty, reg_field;
    private CheckBox telehealth;
    private Button submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_practicioner2);
    }
}