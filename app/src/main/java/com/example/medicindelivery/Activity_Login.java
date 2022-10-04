package com.example.medicindelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Activity_Login extends AppCompatActivity {
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_scrollable);
        register=findViewById(R.id.ActivityLogin_Button_Register);
        register.setOnClickListener(view -> {
            Intent i=new Intent(this,Activity_Register.class);
            startActivity(i);
        });

    }
}