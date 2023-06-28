package com.example.freindscafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    AutoCompleteTextView atx1,atx2;
 FirebaseAuth mAuth;
    Button button;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();



        //*****************AutoCompleteTextView***************
        atx1=findViewById(R.id.username);
        atx2=findViewById(R.id.password);

        ArrayList<String> username=new ArrayList<>();
        username.add("yogesh@gmail.com");

        ArrayList<String> password=new ArrayList<>();
        password.add("yogesh@123");

        ArrayAdapter<String> adapter=new ArrayAdapter<>(login.this, android.R.layout.simple_list_item_1,username);
        atx1.setAdapter(adapter);

        ArrayAdapter<String> adapter1=new ArrayAdapter<>(login.this, android.R.layout.simple_list_item_1,password);
        atx2.setAdapter(adapter1);

        //************progreessbar
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);



        //*****firebase Authentication
       mAuth=FirebaseAuth.getInstance();
       button=findViewById(R.id.login);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progressBar.setVisibility(View.VISIBLE);
               String email = atx1.getText().toString();
               String password = atx2.getText().toString();
               if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                   Toast.makeText(login.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                   progressBar.setVisibility(View.INVISIBLE);
                   return;
               }
               FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   progressBar.setVisibility(View.INVISIBLE);
                                   Intent intent=new Intent(login.this,homepage.class);
                                   startActivity(intent);
                                   overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                                   Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                   finish();
                               } else {
                                   // Sign in failed.
                                   Toast.makeText(login.this, "Login failed", Toast.LENGTH_SHORT).show();
                                   finish();
                               }
                           }
                       });

           }
       });



        //************************************************


    }
}