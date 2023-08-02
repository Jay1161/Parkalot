package com.example.parkalot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private boolean passwordVisibility;
    private Button btnlogin, btngoogle;
    private SignInButton google;
    private TextView txtsignup;
    private FirebaseAuth mAuth;
    private FirebaseAuth mUser;
    private GoogleSignInClient client;

    public static final String Shared_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        txtsignup = findViewById(R.id.txtsignup);
        btnlogin = findViewById(R.id.btnlogin);
        //btngoogle = findViewById(R.id.btngoogle);
        google = findViewById(R.id.google);

        mAuth = FirebaseAuth.getInstance();

        checkBox();

        FirebaseMessaging.getInstance().subscribeToTopic("Notification");

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = password.getSelectionEnd();
                        if (passwordVisibility){
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);

                            //for Hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisibility = false;
                        } else{
                            //show password here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);

                            //for Show password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisibility = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(MainActivity.this, "Empty Credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(txt_email , txt_password);
                }
            }
        });

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this,options);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = client.getSignInIntent();
                startActivityForResult(i,1234);

            }
        });


    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(Shared_PREFS,MODE_PRIVATE);
        String check = sharedPreferences.getString("name","");
        if (check.equals("true")){
//            Toast.makeText(MainActivity.this, "Logged in Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,Start.class));
            finish();
        }
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    //Email Password remember me
                    SharedPreferences sharedPreferences = getSharedPreferences(Shared_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("name","true");
                    editor.apply();
                    startActivity(new Intent(MainActivity.this,Start.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Wrong Password/Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(MainActivity.this,Start.class));
                                }else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!= null){
            startActivity(new Intent(MainActivity.this,Start.class));
        }
        FirebaseAuth.getInstance().signOut();
    }

}
