package com.example.parkalot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Notification extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        bottomNavigationView = findViewById(R.id.bnView);
        bottomNavigationView.setSelectedItemId(R.id.notification);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.home){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(0,0);
                return true;
            }

            else if (id == R.id.dashboard) {
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                overridePendingTransition(0,0);
                return true;
            }

//            else if(id == R.id.profile) {
//                startActivity(new Intent(getApplicationContext(),Profile.class));
//                overridePendingTransition(0,0);
//                return true;
//            }

            else if(id == R.id.notification) {
                return true;
            }

            else {
                startActivity(new Intent(getApplicationContext(),About.class));
                overridePendingTransition(0,0);
                return true;
            }

        });

    }
}