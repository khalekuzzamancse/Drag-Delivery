package com.example.medicindelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.medicindelivery.datatypes.DataType_OrderList;
import com.example.medicindelivery.viewholders.AdapteRecyler_OrderActivity;
import com.example.medicindelivery.viewmodels.ViewModel_ShopList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Order_Activity extends AppCompatActivity {
    List<String> DragListWithPrice;
    List<String> DragListWithoutPrice;
    HashMap<String, String> PriceTable;
    ViewModel_ShopList model;
    List<DataType_OrderList> orderList;
    AutoCompleteTextView chooseItem;
    AutoCompleteTextView amount;
    AdapteRecyler_OrderActivity adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        chooseItem = findViewById(R.id.AutoCompleteTextViewSelect);
        DragListWithPrice = new ArrayList<>();
        DragListWithoutPrice = new ArrayList<>();
        orderList = new ArrayList<>();
        PriceTable = new HashMap<>();
        model = new ViewModelProvider(this).get(ViewModel_ShopList.class);
        model.getShopListHashMap().observe(Order_Activity.this, new Observer<HashMap<String, List<String>>>() {
            @Override
            public void onChanged(HashMap<String, List<String>> stringListHashMap) {
                DragListWithPrice = stringListHashMap.get("khalekuzzaman91@gmail.com");
                if (DragListWithPrice != null) {
                    for (int i = 0; i < DragListWithPrice.size(); i++) {
                        String itemWithPrice = DragListWithPrice.get(i);
                        String itemWithoutPrice = itemWithPrice.substring(0, itemWithPrice.indexOf('$'));
                        String price = itemWithPrice.substring(itemWithPrice.indexOf('$') + 1);
                        PriceTable.put(itemWithoutPrice, price);
                        DragListWithoutPrice.add(itemWithoutPrice);
                    }

                    Collections.sort(DragListWithoutPrice);
                    ArrayAdapter adapter = new ArrayAdapter(Order_Activity.this, R.layout.layout_suggestion, DragListWithoutPrice);
                    chooseItem.setAdapter(adapter);
                }


            }
        });
        List<Integer> Count = new ArrayList<>();
        for (int i = 1; i <= 100; i++)
            Count.add(i);
//
        ArrayAdapter<Integer> adapter3 = new ArrayAdapter<>(this, R.layout.layout_suggestion, Count);
        AutoCompleteTextView v = findViewById(R.id.autoCompleteTextView2);
        v.setAdapter(adapter3);

        adapter2 = new AdapteRecyler_OrderActivity(Order_Activity.this, orderList);
        RecyclerView r = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        r.setLayoutManager(linearLayoutManager);
        r.setAdapter(adapter2);
        Button save = findViewById(R.id.save);
        amount = findViewById(R.id.autoCompleteTextView2);
        save.setOnClickListener(view1 -> {
            String itemSelected = chooseItem.getText().toString().trim();
            DataType_OrderList l = new DataType_OrderList();
            l.itemNameWithPrice = itemSelected;
            if (itemSelected.isEmpty()) {
                chooseItem.setError("Can not be empty!");

                return;
            }
            RemoveDuplicate(itemSelected, amount.getText().toString());
            l.itemAmount = amount.getText().toString();
            l.itemPrice = PriceTable.get(itemSelected);

            orderList.add(l);
            adapter2.notifyDataSetChanged();

        });
        amount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = chooseItem.getText().toString().trim();
                if (isAdded(itemSelected) == true) {
                    String s = parent.getItemAtPosition(position).toString();
                    Log.i("PriceTable", s);


                    DataType_OrderList l = new DataType_OrderList();
                    l.itemNameWithPrice = itemSelected;
                    if (itemSelected.isEmpty()) {
                        chooseItem.setError("Can not be empty!");

                        return;
                    }
                    String totalAmount = s;
                    RemoveDuplicate(itemSelected, s);
                    l.itemAmount = totalAmount;
                    l.itemPrice = PriceTable.get(itemSelected);

                    orderList.add(l);
                    adapter2.notifyDataSetChanged();
                }


            }
        });


    }

    private void RemoveDuplicate(String itemSelected, String amount) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).itemNameWithPrice.equals(itemSelected)) {
                orderList.remove(i);
                adapter2.notifyItemRemoved(i);

            }

        }


    }


    private String totalPrice(String item, String amount) {
        int total = Integer.parseInt(PriceTable.get(item)) * Integer.parseInt(amount);
        return String.valueOf(total);
    }

    private boolean isAdded(String itemSelected) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).itemNameWithPrice.equals(itemSelected)) {
                return true;
            }

        }
        return false;


    }
}