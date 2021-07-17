package com.example.ihawcapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity implements View.OnClickListener  {

    private EditText Email;
    private Button Btsubmit;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        // initiate the object firebaseAuth
        firebaseAuth =  FirebaseAuth.getInstance();

        // declaring the view's
        Email =  (EditText) findViewById(R.id.userEmail);
        Btsubmit =  (Button) findViewById(R.id.buttonSubmit);

        progressDialog = new ProgressDialog(this);
        Btsubmit.setOnClickListener(this);

    }

    public void password(){
        String getEmail = Email.getText().toString().trim();


        // verify if the text box is not empty

        if (TextUtils.isEmpty(getEmail)){
            Toast.makeText(this,"missing email",Toast.LENGTH_LONG).show();
            return;
        }
        //printing successfully message
        progressDialog.setMessage("receiving information... ");
        progressDialog.show();

        firebaseAuth.sendPasswordResetEmail(getEmail)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(PasswordReset.this, " Password send to Email ",Toast.LENGTH_LONG).show();


                        } else{
                            Toast.makeText(PasswordReset.this,"User Not Found",Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();
                    }
                });
        startActivity(new Intent(this, Login.class));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSubmit:
                password();
                break;
        }
    }
}