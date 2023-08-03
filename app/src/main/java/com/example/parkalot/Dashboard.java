package com.example.parkalot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button btn_myorder, btn_change_pass, btn_view_profile, btnlogout;
    public static final String Shared_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btn_myorder = findViewById(R.id.btn_myorder);
        btn_change_pass = findViewById(R.id.btn_change_pass);
        btn_view_profile = findViewById(R.id.btn_view_profile);
        btnlogout = findViewById(R.id.btnlogout);

        bottomNavigationView = findViewById(R.id.bnView);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.home){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(0,0);
                return true;
            }

            else if (id == R.id.dashboard) {
                return true;
            }

//            else if(id == R.id.profile) {
//                startActivity(new Intent(getApplicationContext(),Profile.class));
//                overridePendingTransition(0,0);
//                return true;
//            }

            else if(id == R.id.notification) {
                startActivity(new Intent(getApplicationContext(),Notification.class));
                overridePendingTransition(0,0);
                return true;
            }

            else {
                startActivity(new Intent(getApplicationContext(),About.class));
                overridePendingTransition(0,0);
                return true;
            }

        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(Shared_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("name","");
                editor.apply();

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this,MainActivity.class));
            }
        });

        btn_myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, My_order.class));
                Toast.makeText(Dashboard.this, "My Order", Toast.LENGTH_SHORT).show();
            }
        });

        btn_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Profile.class));
                Toast.makeText(Dashboard.this, "My Profile", Toast.LENGTH_SHORT).show();
            }
        });

        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Change_password.class));
                Toast.makeText(Dashboard.this, "Change Password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
