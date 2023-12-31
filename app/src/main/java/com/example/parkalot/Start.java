package com.example.parkalot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.parkalot.adapter.MyListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class Start extends AppCompatActivity {

//    public static final String Shared_PREFS = "sharedPrefs";
    private Button btnlogout, btnslot;
    BottomNavigationView bottomNavigationView;
    Context context;
    ArrayList personNames = new ArrayList<>(Arrays.asList("Iskcon", "Thaltej", "Bapol", "Sola", "Gota", "Maninagar", "Riverfront", "Alkapuri", "Manjalpur", "Gotri","Shubhanpura","karelibaug","AV Road","Solaris"));
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //btnlogout = findViewById(R.id.btnlogout);
        //btnslot = findViewById(R.id.btnslot);

        setContentView(R.layout.activity_start);
        recyclerView = findViewById(R.id.rec);
        context = this.context;

        MyListAdapter adapter = new MyListAdapter(context,personNames);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bnView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.home){
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

//        btnlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sharedPreferences = getSharedPreferences(Shared_PREFS, MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                editor.putString("name","");
//                editor.apply();
//
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(Start.this,MainActivity.class));
//            }
//        });

//        btnslot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(Start.this,Booking.class));
//
//            }
//        });

    }
}
