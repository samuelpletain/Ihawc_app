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

public class RegisterPatient2 extends AppCompatActivity implements View.OnClickListener{
    private EditText reg_email, reg_password, reg_name, reg_phone;
    private Button reg_next;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient2);

        mAuth = FirebaseAuth.getInstance();
        //read info from all of the buttons
        reg_email = (EditText)findViewById(R.id.reg_email);
        reg_password = (EditText)findViewById(R.id.reg_password);
        reg_name = (EditText)findViewById(R.id.reg_name);
        reg_phone = (EditText)findViewById(R.id.reg_phone);
        reg_next = (Button)findViewById(R.id.reg_next);
        reg_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_next:
                registerPatient();

                break;
        }

    }

    private void registerPatient() {
        String email = reg_email.getText().toString().trim();
        String password = reg_password.getText().toString().trim();
        String name = reg_name.getText().toString().trim();
        String phone = reg_phone.getText().toString().trim();

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
                            Provider patient= new Provider();
                            patient.setEmail(email);
                            patient.setName(name);
                            patient.setPhone(phone);
                            patient.setType("patient");





                            db.collection("provider")
                                    .add(patient)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(RegisterPatient2.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();


                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterPatient2.this, "Failed to register", Toast.LENGTH_LONG).show();
                                        }
                                    });


                        }
                    }

                    ;


                });
        startActivity(new Intent(this, Login.class));
    }
}