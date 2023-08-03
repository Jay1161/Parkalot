package com.example.parkalot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Booking extends AppCompatActivity {

    private Button btnbook;
    private DatePicker date;
    private TimePicker time;
    private TextView tvdate, bookslot,tvtime;
    private ImageView imgdate,imgtime;
    private EditText fname;
    private DatabaseReference bookingsRef;

//    private FirebaseDatabase db = FirebaseDatabase.getInstance();
//    private  DatabaseReference root = db.getReference().child("Bookings");


    String[] items = {"Car", "Motorcycle", "Van", "Truck", "Cycle"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        btnbook = findViewById(R.id.btnbook);

        fname = findViewById(R.id.fname);
        date=(DatePicker)findViewById(R.id.date);
        time=(TimePicker)findViewById(R.id.time);
        tvdate=(TextView)findViewById(R.id.tvdate);
        imgdate=(ImageView)findViewById(R.id.imgdate);
        tvtime=(TextView)findViewById(R.id.tvtime);
        imgtime=(ImageView)findViewById(R.id.imgtime);
        //bookslot=(TextView)findViewById(R.id.bookslot);
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        bookingsRef = database.getReference("Bookings");


        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,items);

        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });


        imgdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.setVisibility(v.VISIBLE);
            }
        });

        imgtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setVisibility(v.VISIBLE);
            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date.setVisibility(v.GONE);
                time.setVisibility(v.GONE);

                String name = fname.getText().toString();
                String item = autoCompleteTxt.getText().toString();

                if (name.isEmpty() || item.isEmpty()) {
                    Toast.makeText(Booking.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String,String> bookMap = new HashMap<>();

                bookMap.put("Name", name);
                bookMap.put("Vehicle Type", item);

//                root.push().setValue(bookMap);

                int day = date.getDayOfMonth();
                int month = date.getMonth() + 1;  // Month is zero-based
                int year = date.getYear();
                String selectedDate = String.format("%02d-%02d-%04d", day, month, year);

                int hour = time.getCurrentHour();
                int minute = time.getCurrentMinute();
                String selectedTime = String.format("%02d:%02d", hour, minute);

                // Generate a unique key for the booking entry
                String key = bookingsRef.push().getKey();

                // Write the selected date and time under the generated key in the "bookings" node
                bookingsRef.child(key).child("Name").setValue(name);
                bookingsRef.child(key).child("Vehicle Type").setValue(item);
                bookingsRef.child(key).child("Date").setValue(selectedDate);
                bookingsRef.child(key).child("Time").setValue(selectedTime);


                Toast.makeText(Booking.this, "Successfully Booked " + selectedDate + " " + selectedTime, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
