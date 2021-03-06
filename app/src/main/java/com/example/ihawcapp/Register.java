package com.example.ihawcapp;
/**
 * Robert is Doing this class this class is for reading in the initial page and
 * determining if it is a practicioner, clinic or patient
 *I used this video as a framework on how to do this part https://www.youtube.com/watch?v=Z-RE1QuUWPg
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText reg_email, reg_password, reg_name, reg_address, reg_phone, provider_title, practicioner_credentials,practicioner_tribal;
    private EditText prac_hours, prac_specialty, prac_field;
    private Button reg_next;
    private CheckBox telehealth;


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
        provider_title =(EditText)findViewById(R.id.provider_title);
        practicioner_credentials =(EditText)findViewById(R.id.practicioner_credentials);
        practicioner_tribal =(EditText)findViewById(R.id.practicioner_tribal);
        prac_hours =(EditText)findViewById(R.id.prac_hours);
        prac_specialty = (EditText)findViewById(R.id.prac_specialty);
        prac_field= (EditText)findViewById(R.id.prac_field);
        //figure out how to do checkbox
        // telehealth=(EditText)findViewById(R.id.prac_telehealth);
        reg_next = (Button)findViewById(R.id.reg_next);
        reg_next.setOnClickListener(this);
        telehealth = (CheckBox)findViewById(R.id.telehealth);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_next:
                registerPractitioner();

                break;
        }

    }

    private void registerPractitioner(){
        String email = reg_email.getText().toString().trim();
        String password = reg_password.getText().toString().trim();
        String name = reg_name.getText().toString().trim();
        String address = reg_address.getText().toString().trim();
        String phone = reg_phone.getText().toString().trim();
        String title = provider_title.getText().toString().trim();
        String credentials = practicioner_credentials.getText().toString().trim();
        String tribal= practicioner_tribal.getText().toString().trim();
        String hours = prac_hours.getText().toString().trim();
        String specialty = prac_specialty.getText().toString().trim();
        String field = prac_field.getText().toString().trim();




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
        if (title.isEmpty()){
            provider_title.setError("Provider is required");
        }


        FirebaseFirestore db = FirebaseFirestore.getInstance();





        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull  Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Practitioner practitioner = new Practitioner();
                        practitioner.setEmail(email);
                        practitioner.setName(name);
                        practitioner.setAdresses(address);//Should this be a hash map or string? and how do I convert
                        practitioner.setPhone(phone);
                        practitioner.setTitle(title);
                        practitioner.setCredentials(credentials);
                        practitioner.setTribalAffiliation(tribal);
                        practitioner.setHours(hours);
                        practitioner.setSpecialties(specialty);
                        practitioner.setType("practicioner");

                        if (telehealth.isChecked()){
                            practitioner.setTelehealth(Boolean.TRUE);
                        }




                        db.collection("provider")
                                .add(practitioner)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();


                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Register.this, "Failed to register", Toast.LENGTH_LONG).show();
                                    }
                                });


                    }
                }

                ;


        });
        startActivity(new Intent(this, Login.class));

    }
}