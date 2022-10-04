package com.example.medicindelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.medicindelivery.databinding.ActivityOrderBinding;
import com.example.medicindelivery.viewmodels.ViewModel_ShopList;

import java.util.HashMap;
import java.util.List;


public class Order_Activity extends AppCompatActivity {
    List<String> DragList;
    ActivityOrderBinding orderBinding;
    ViewModel_ShopList model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderBinding = ActivityOrderBinding.inflate(getLayoutInflater());
        View view = orderBinding.getRoot();
        // setContentView(R.layout.activity_order);
        setContentView(view);


        model = new ViewModelProvider(this).get(ViewModel_ShopList.class);
        model.getShopListHashMap().observe(Order_Activity.this, new Observer<HashMap<String, List<String>>>() {
            @Override
            public void onChanged(HashMap<String, List<String>> stringListHashMap) {
                DragList = stringListHashMap.get("khalekuzzaman91@gmail.com");
                ArrayAdapter adapter = new ArrayAdapter(Order_Activity.this, R.layout.layout_suggestion, DragList);
                orderBinding.autoCompleteTextView.setAdapter(adapter);
                 Log.i("DataTakenOrder", String.valueOf(stringListHashMap));

            }
        });
    }

}