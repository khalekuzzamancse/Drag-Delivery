package com.example.medicindelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private ViewModelTest model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    model=new ViewModelProvider(this).get(ViewModelTest.class);

       model.getCurrentName().observe(this, new Observer<String>() {
           @Override
           public void onChanged(String s) {
               Log.i("Curr,Lamad",s);
           }
       });

        Log.i("Curr",model.getCurrentName().getValue());

        model.setCurrentName("Alhamdulliah");

    }
}