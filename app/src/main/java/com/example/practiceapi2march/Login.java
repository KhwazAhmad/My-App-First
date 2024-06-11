package com.example.practiceapi2march;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;


public class Login extends AppCompatActivity {
    AppCompatButton login, ragister;
    TextInputEditText email, pass;
    SharedPreferences sharedPreferences;
    String  spass, get_name, get_phone;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.loginbutton);
        ragister = findViewById(R.id.ragisterbutton);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Ragister.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(Constent.GETDATA, MODE_PRIVATE);
        //code for not login again and again
        boolean chechlogin = sharedPreferences.getBoolean(Constent.IS_LOGGED_IN, false);
        if (chechlogin) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = email.getText().toString();
                String password = pass.getText().toString();
                Animation animation = new TranslateAnimation(-20.0f, 20.f, 0.f, 0.f);
                animation.setDuration(1000);
                String Smail = sharedPreferences.getString(Constent.MAIL, "");
                String Spass = sharedPreferences.getString(Constent.PASS, "");
                if (username.isEmpty()) {
                    email.setError("Name is empty");
                    email.setAnimation(animation);
                    email.requestFocus();
                    return;
                } else if (password.isEmpty()) {
                    pass.setError("password is empty");
                    pass.setAnimation(animation);
                    pass.requestFocus();
                    return;

                }
               else if (username.equals(Smail) && password.equals(Spass)) {
                    editor.putBoolean(Constent.IS_LOGGED_IN, true);
                    editor.apply();
                    Toast.makeText(Login.this, "login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
               else {
                    Toast.makeText(Login.this, "email or Password is wrong", Toast.LENGTH_SHORT).show();
                    email.setError("something is wrong");
                    email.setAnimation(animation);
                    email.requestFocus();
                    pass.setAnimation(animation);
                    pass.requestFocus();
                }

            }
        });
    }

}
