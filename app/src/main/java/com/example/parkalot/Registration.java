package com.example.parkalot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText name, email, password;
    private Button btnregister;
    private TextView tvloginp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = findViewById(R.id.email);
        //name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        btnregister = findViewById(R.id.btnregister);
        tvloginp = findViewById(R.id.tvloginp);

        mAuth = FirebaseAuth.getInstance();

        tvloginp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,MainActivity.class));
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(Registration.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6){
                    Toast.makeText(Registration.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtEmail , txtPassword);
                }
            }
        });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Registration.this, "Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this,Start.class));
                }
                else{
                    Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
