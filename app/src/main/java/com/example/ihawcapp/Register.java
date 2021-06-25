package com.example.ihawcapp;
/**
 * Robert is Doing this class this class is for reading in the initial page and
 * determining if it is a practicioner, clinic or patient
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText reg_email, reg_password, reg_name, reg_address, reg_phone;
    private Button reg_next;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        //read info from all of the buttons
        reg_email = (EditText)findViewById(R.id.reg_email);
        reg_password = (EditText)findViewById(R.id.reg_password);
        reg_name = (EditText)findViewById(R.id.reg_name);
        reg_address = (EditText)findViewById(R.id.reg_address);
        reg_phone = (EditText)findViewById(R.id.reg_phone);
        reg_next = (Button)findViewById(R.id.reg_next);
        reg_next.setOnClickListener(this);
        Spinner drop_list = (Spinner)findViewById(R.id.reg_drop_list);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.reg_drop_list));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drop_list.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_next:
                /*if (reg_practicioner_check == true){
                    Practitioner person = new Practitioner();

                }
                else if (reg_clinic_check == true){
                    Clinic person = new Clinic();
                }
                
                startActivity(new Intent(this, Register.class));
                break;*/
        }

    }
}