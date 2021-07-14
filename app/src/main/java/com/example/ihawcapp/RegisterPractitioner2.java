package com.example.ihawcapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public  class RegisterPractitioner2 extends AppCompatActivity implements View.OnClickListener {
    private EditText reg_title,reg_credentials, reg_affiliation, reg_hours, reg_specialty, reg_field;
    private CheckBox telehealth;
    private Button submit;
    private FirebaseAuth mAuth;
    private String email, address, phone, name, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_practicioner2);
        String email = getIntent().getExtras().getString("pracEmail");
        String address = (String) getIntent().getExtras().get("pracAddress");
        String phone = getIntent().getExtras().getString("pracPhone");
        String name = getIntent().getExtras().getString("pracName");
        String password = getIntent().getExtras().getString("pracPassword");
        mAuth = FirebaseAuth.getInstance();
        reg_title = (EditText)findViewById(R.id.provider_title);
        reg_credentials  = (EditText)findViewById(R.id.practicioner_credentials);
        reg_affiliation = (EditText)findViewById(R.id.practicioner_tribal);
        reg_hours = (EditText)findViewById(R.id.prac_hours);
        reg_specialty = (EditText)findViewById(R.id.prac_specialty);
        reg_field = (EditText)findViewById(R.id.prac_field);
        submit = (Button)findViewById(R.id.prac_submit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        registerPractitioner();

    }

    private void registerPractitioner() {

        String title = reg_title.getText().toString().trim();
        String credentials = reg_credentials.getText().toString().trim();
        String affiliation = reg_affiliation.getText().toString().trim();
        String hours = reg_hours.getText().toString().trim();
        String specialty = reg_specialty.getText().toString().trim();
        String field = reg_field.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(RegisterPractitioner2.this, "here", Toast.LENGTH_LONG).show();

                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterPractitioner2.this, "here", Toast.LENGTH_LONG).show();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        Practitioner practitioner = new Practitioner();
                        practitioner.setEmail(email);
                        practitioner.setName(name);
                        practitioner.setAdresses(address);//Should this be a hash map or string? and how do I convert
                        practitioner.setPhone(phone);
                        practitioner.setTitle(title);
                        practitioner.setCredentials(credentials);
                        practitioner.setTribalAffiliation(affiliation);//add the fields from page2 to the class
                        practitioner.setHours(hours);
                        practitioner.setSpecialties(specialty);
                        practitioner.setField(field);
                        Toast.makeText(RegisterPractitioner2.this, "Made it here", Toast.LENGTH_LONG).show();
                        db.collection("provider")
                                .add(practitioner)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(RegisterPractitioner2.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        Intent nextScreen = new Intent(RegisterPractitioner2.this, Login.class);
                                        startActivity(nextScreen);

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterPractitioner2.this, "Failed to register", Toast.LENGTH_LONG).show();
                                    }
                                });


                        }
                    }


                });
    }



}