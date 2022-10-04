package com.example.medicindelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.medicindelivery.databinding.ActivityOrderBinding;
import com.example.medicindelivery.viewholders.AdapteRecyler_OrderActivity;
import com.example.medicindelivery.viewmodels.ViewModel_ShopList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Order_Activity extends AppCompatActivity {
    List<String> DragList;
    ActivityOrderBinding orderActivity;
    ViewModel_ShopList model;
    List<String> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderActivity = ActivityOrderBinding.inflate(getLayoutInflater());
        View view = orderActivity.getRoot();
       //  setContentView(R.layout.activity_order);
        setContentView(view);
        //setting the drop_down menu


        DragList = new ArrayList<>();
        orderList = new ArrayList<>();
        model = new ViewModelProvider(this).get(ViewModel_ShopList.class);
        model.getShopListHashMap().observe(Order_Activity.this, new Observer<HashMap<String, List<String>>>() {
            @Override
            public void onChanged(HashMap<String, List<String>> stringListHashMap) {
                DragList = stringListHashMap.get("khalekuzzaman91@gmail.com");
                ArrayAdapter adapter = new ArrayAdapter(Order_Activity.this, R.layout.layout_suggestion, DragList);
                orderActivity.autoCompleteTextView.setAdapter(adapter);
                Log.i("DataTakenOrder", String.valueOf(stringListHashMap));
            }
        });

        AdapteRecyler_OrderActivity adapter2 = new AdapteRecyler_OrderActivity(Order_Activity.this, orderList);
        RecyclerView r = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        r.setLayoutManager(linearLayoutManager);
//        r.setLayoutManager(new LinearLayoutManager(Order_Activity.this));
        r.setAdapter(adapter2);
        orderActivity.save.setOnClickListener(view1 -> {
            String itemSelected = orderActivity.autoCompleteTextView.getText().toString().trim();
            orderList.add(itemSelected);
            adapter2.notifyDataSetChanged();

        });


    }

}