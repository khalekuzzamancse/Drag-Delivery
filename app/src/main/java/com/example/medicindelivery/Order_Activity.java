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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.medicindelivery.datatypes.DataType_OrderList;
import com.example.medicindelivery.viewholders.AdapteRecyler_OrderActivity;
import com.example.medicindelivery.viewmodels.ViewModel_ShopList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Order_Activity extends AppCompatActivity {
    List<String> DragList;
    ViewModel_ShopList model;
    List<DataType_OrderList> orderList;
    AutoCompleteTextView tv;
    AutoCompleteTextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tv = findViewById(R.id.AutoCompleteTextViewSelect);
        DragList = new ArrayList<>();
        orderList = new ArrayList<>();
        model = new ViewModelProvider(this).get(ViewModel_ShopList.class);
        model.getShopListHashMap().observe(Order_Activity.this, new Observer<HashMap<String, List<String>>>() {
            @Override
            public void onChanged(HashMap<String, List<String>> stringListHashMap) {
                DragList = stringListHashMap.get("khalekuzzaman91@gmail.com");
                if(DragList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(Order_Activity.this, R.layout.layout_suggestion, DragList);
                    tv.setAdapter(adapter);
                }


            }
        });
        List<Integer> Count=new ArrayList<>();
       for(int i=1;i<=100;i++)
           Count.add(i);
//
        ArrayAdapter<Integer> adapter3=new ArrayAdapter<>(this,R.layout.layout_suggestion,Count);
        AutoCompleteTextView v=findViewById(R.id.autoCompleteTextView2);
        v.setAdapter(adapter3);

        AdapteRecyler_OrderActivity adapter2 = new AdapteRecyler_OrderActivity(Order_Activity.this, orderList);
        RecyclerView r = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        r.setLayoutManager(linearLayoutManager);
        r.setAdapter(adapter2);
        Button save=findViewById(R.id.save);
        amount=findViewById(R.id.autoCompleteTextView2);
      save.setOnClickListener(view1 -> {
            String itemSelected = tv.getText().toString().trim();
          DataType_OrderList l=new DataType_OrderList();
          l.itemName=itemSelected;
          l.itemAmount=amount.getText().toString();
            if (itemSelected.isEmpty()) {
                tv.setError("Can not be empty!");

                return;
            }
            orderList.add(l);
            adapter2.notifyDataSetChanged();

        });


    }

}