package com.example.practiceapi2march;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.practiceapi2march.Fragment.Multiple;
import com.google.android.material.textfield.TextInputEditText;

public class Ragister extends AppCompatActivity {
    AppCompatButton ragister;
    TextInputEditText email, pass, name, phone;
    String semail, spass, get_name, get_phone;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister);
        ragister = findViewById(R.id.ragister);
        name = findViewById(R.id.r_name);
        phone = findViewById(R.id.r_phone);
        email = findViewById(R.id.r_email);
        pass = findViewById(R.id.r_pass);
        SharedPreferences sharedPreferences = getSharedPreferences(Constent.GETDATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semail = email.getText().toString();
                spass = pass.getText().toString();
                get_name = name.getText().toString();
                get_phone = phone.getText().toString();
                Multiple fragment = new Multiple();
                Animation animation = new TranslateAnimation(-20.0f, 20.f, 0.f, 0.f);
                animation.setDuration(1000);
                editor.putString(Constent.MAIL, semail);
                editor.putString(Constent.PASS, spass);
                editor.putString(Constent.Name, get_name);
                editor.putString(Constent.Phone, get_phone);
                editor.apply();
                if (get_name.isEmpty()) {

                    name.setError("Name is empty");
                    name.setAnimation(animation);
                    name.requestFocus();
                    return;
                } else if (get_phone.isEmpty()) {
                    phone.setError("NO is empty");
                    phone.setAnimation(animation);
                    phone.requestFocus();
                    return;
                } else if (semail.isEmpty()) {
                    email.setError("email is empty");
                    email.setAnimation(animation);
                    email.requestFocus();
                    return;
                } else if (spass.isEmpty()) {
                    pass.setError("password is empty");
                    pass.setAnimation(animation);
                    pass.requestFocus();
                    return;
                } else if (semail != null && spass != null) {
                    Intent i = new Intent(Ragister.this, Login.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}