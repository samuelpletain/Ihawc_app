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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private TextView TxtPassword;

    // defining the Login objects from login1.xml
    private EditText Email; //editTextTextEmailAddress
    private EditText Password; //password_login
    private Button Btlogin; //login_button
    private ProgressDialog progressDialog;

    // declare an object firebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(this);


        TxtPassword = (TextView)findViewById(R.id.resetPassword);
        TxtPassword.setOnClickListener(this);


        // initiate the object firebaseAuth
        firebaseAuth =  FirebaseAuth.getInstance();

        // declaring the view's
        Email =  (EditText) findViewById(R.id.editTextTextEmailAddress);
        Password =  (EditText) findViewById(R.id.password_login);
        Btlogin =  (Button) findViewById(R.id.login_button);

        progressDialog = new ProgressDialog(this);
        Btlogin.setOnClickListener(this);
    }


    public void loginUser(){
        // get the email and password from the text boxes
        String getEmail = Email.getText().toString().trim();
        String getPassword = Password.getText().toString().trim();

        // verify if the text boxes are not empty

        if (TextUtils.isEmpty(getEmail)){
            Toast.makeText(this,"missing email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(getPassword)){
            Toast.makeText(this,"missing Password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("receiving information... ");
        progressDialog.show();

        // login the user

        firebaseAuth.signInWithEmailAndPassword(getEmail,getPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()){

                            Toast.makeText(Login.this, " Welcome ",Toast.LENGTH_LONG).show();
                            Intent intention = new Intent(getApplication(),MainActivity.class);
                            startActivity(intention);

                        } else{
                            Toast.makeText(Login.this,"User Not Found",Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, Reg_Choice.class));
                break;

            case R.id.resetPassword:
                startActivity(new Intent(this, PasswordReset.class));
                break;

            case R.id.login_button:
                loginUser();
                break;
        }
    }




}