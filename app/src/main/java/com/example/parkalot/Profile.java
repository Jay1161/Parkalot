package com.example.parkalot;

import static com.example.parkalot.R.id.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Profile extends AppCompatActivity {

//    BottomNavigationView bottomNavigationView;
    EditText name, email, contact, dateOfBirth;
    Button editProfile,submit;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Calendar calendar;
    RadioButton male,female;
    RadioGroup gender;
    Spinner spinner;
    ArrayList<String> arrayList;
    boolean isSelect = false;
    String sGender,sCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        bottomNavigationView = findViewById(R.id.bnView);
//        bottomNavigationView.setSelectedItemId(R.id.profile);
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        contact = findViewById(R.id.profile_contact);
        dateOfBirth = findViewById(R.id.profile_date_of_birth);
        spinner = findViewById(R.id.profile_spinner);
        male = findViewById(R.id.profile_male);
        female = findViewById(R.id.profile_female);
        gender = findViewById(R.id.profile_gender);
        submit = findViewById(R.id.profile_submit_button);
        //editProfile = findViewById(R.id.profile_button);

        arrayList = new ArrayList<>();
        arrayList.add("Ahmedabad");
        arrayList.add("Anand");
        arrayList.add("Vadodara");
        arrayList.add("Test");
        arrayList.add("Surt");
        arrayList.add("Rajkot");

        arrayList.remove(3);
        arrayList.set(3,"Surat");

        arrayList.add(0,"Gandhinagar");

        ArrayAdapter adapter = new ArrayAdapter(Profile.this, android.R.layout.simple_list_item_1,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapter);

//        bottomNavigationView.setOnItemSelectedListener(item -> {
//
//            int id = item.getItemId();
//
//            if (id == R.id.home){
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                overridePendingTransition(0,0);
//                return true;
//            }
//
//            else if (id == R.id.dashboard) {
//                startActivity(new Intent(getApplicationContext(),Dashboard.class));
//                overridePendingTransition(0,0);
//                return true;
//            }
//
//            else if(id == R.id.profile) {
//                return true;
//            }
//
//            else if(id == R.id.notification) {
//                startActivity(new Intent(getApplicationContext(),Notification.class));
//                overridePendingTransition(0,0);
//                return true;
//            }
//
//            else {
//                startActivity(new Intent(getApplicationContext(),About.class));
//                overridePendingTransition(0,0);
//                return true;
//            }
//
//        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                sGender = radioButton.getText().toString();
            }
        });

        calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateClick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                dateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelect) {
                    //new DatePickerDialog(ProfileActivity.this,dateClick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

                    DatePickerDialog pickerDialog = new DatePickerDialog(Profile.this, dateClick, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    //pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    pickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    pickerDialog.show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Submitted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}