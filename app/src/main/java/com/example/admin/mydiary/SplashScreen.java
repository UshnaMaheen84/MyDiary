package com.example.admin.mydiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

    }

    @Override
    protected void onStart() {
        super.onStart();

        Thread mythread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    if (currentUser != null)
                    {
                        Intent intent = new Intent(SplashScreen.this,HomePage.class);
                        startActivity(intent);
                        finish();}
                    else {
                        Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }
}
