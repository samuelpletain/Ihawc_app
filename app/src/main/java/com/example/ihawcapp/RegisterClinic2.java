package com.example.ihawcapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterClinic2 extends AppCompatActivity implements View.OnClickListener {
    private EditText reg_email, reg_password, reg_name, reg_address, reg_phone, reg_clinic_network;
    private EditText clinic_website, clinic_hours, clinic_specialty, clinic_field;
    private Button clinic_submit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_clinic2);
        mAuth = FirebaseAuth.getInstance();
        reg_email = (EditText)findViewById(R.id.reg_email);
        reg_password = (EditText)findViewById(R.id.reg_password);
        reg_name = (EditText)findViewById(R.id.reg_name);
        reg_address = (EditText)findViewById(R.id.reg_address);
        reg_phone = (EditText)findViewById(R.id.reg_phone);
        reg_clinic_network = (EditText)findViewById(R.id.reg_clinc_network);
        clinic_website = (EditText)findViewById(R.id.clinic_website);
        clinic_hours = (EditText)findViewById(R.id.clinic_hours);
        clinic_specialty = (EditText)findViewById(R.id.clinic_specialty);
        clinic_field = (EditText)findViewById(R.id.clinic_field);
        clinic_submit = (Button)findViewById(R.id.clinic_submit);
        clinic_submit.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clinic_submit:
                registerClinic();
                break;
        }

    }

    private void registerClinic() {
        String email = reg_email.getText().toString().trim();
        String password = reg_password.getText().toString().trim();
        String name = reg_name.getText().toString().trim();
        String address = reg_address.getText().toString().trim();
        String phone = reg_phone.getText().toString().trim();
        String network = reg_clinic_network.getText().toString().trim();
        String website = clinic_website.getText().toString().trim();
        String specialty = clinic_specialty.getText().toString().trim();
        String field = clinic_field.getText().toString().trim();
        if (email.isEmpty()){
            reg_email.setError("Email is required!");
            reg_email.requestFocus();
            return;
        }

        if (password.isEmpty()){
            reg_password.setError("Password is required!");
            reg_password.requestFocus();
            return;
        }
        if (name.isEmpty()){
            reg_name.setError("Name is required!");
            reg_name.requestFocus();
            return;
        }
        if (address.isEmpty()){
            reg_address.setError("Address is required!");
            reg_address.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            reg_phone.setError("Phone is required!");
            reg_phone.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            reg_email.setError("Please provide valid email!");
            reg_email.requestFocus();
            return;
        }

        if (password.length()<6){
            reg_password.setError("Min password length should be 6 characters!");
            reg_password.requestFocus();
            return;
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Clinic clinic= new Clinic();
                            clinic.setName(name);
                            clinic.setField(field);
                            clinic.setSpecialties(specialty);
                            clinic.setEmail(email);
                            clinic.setPhone(phone);
                            clinic.setNetwork(network);
                            clinic.setAddress(address);
                            clinic.setWebsite(website);
                            Toast.makeText(RegisterClinic2.this, clinic.getWebsite(), Toast.LENGTH_LONG).show();





                            db.collection("provider")
                                    .add(clinic)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(RegisterClinic2.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();


                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterClinic2.this, "Failed to register", Toast.LENGTH_LONG).show();
                                        }
                                    });


                        }
                    }

                    ;


                });
        startActivity(new Intent(this, Login.class));



    }
}