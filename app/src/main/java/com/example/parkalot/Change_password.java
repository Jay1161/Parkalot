package com.example.parkalot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class Change_password extends AppCompatActivity {

    EditText old_pass,new_pass,new_pass_confirm;
    private boolean passwordVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        old_pass = findViewById(R.id.old_pass);
        new_pass = findViewById(R.id.new_pass);
        new_pass_confirm = findViewById(R.id.new_pass_confirm);

        old_pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (event.getRawX()>=old_pass.getRight()-old_pass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = old_pass.getSelectionEnd();
                        if (passwordVisibility){
                            //set drawable image here
                            old_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);

                            //for Hide password
                            old_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisibility = false;
                        } else{
                            //to change drawable image
                            old_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);

                            //for Show password
                            old_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisibility = true;
                        }
                        old_pass.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        new_pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (event.getRawX()>=new_pass.getRight()-new_pass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = old_pass.getSelectionEnd();
                        if (passwordVisibility){
                            //set drawable image here
                            new_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);

                            //for Hide password
                            new_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisibility = false;
                        } else{
                            //to change drawable image
                            new_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);

                            //for Show password
                            new_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisibility = true;
                        }
                        new_pass.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        new_pass_confirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (event.getRawX()>=new_pass_confirm.getRight()-new_pass_confirm.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = new_pass_confirm.getSelectionEnd();
                        if (passwordVisibility){
                            //set drawable image here
                            new_pass_confirm.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);

                            //for Hide password
                            new_pass_confirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisibility = false;
                        } else{
                            //to change drawable image
                            new_pass_confirm.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);

                            //for Show password
                            new_pass_confirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisibility = true;
                        }
                        new_pass_confirm.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });
    }
}