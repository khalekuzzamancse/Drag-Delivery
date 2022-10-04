package com.example.medicindelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.medicindelivery.datatypes.Datatype_ShopList;
import com.example.medicindelivery.viewmodels.ViewModel_AllDistrictList;
import com.example.medicindelivery.viewmodels.ViewModel_ShopList;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    ViewModel_ShopList model;
    ViewModel_AllDistrictList modelDistrict;
    List<String> districtList;
    List<String> subDistrictList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, Order_Activity.class));
        // startActivity(new Intent(this, Activity_DragList_Shop_Keeper.class));


        //
        model = new ViewModelProvider(this).get(ViewModel_ShopList.class);
        model.getShopListHashMap().observe(MainActivity.this, new Observer<HashMap<String, List<String>>>() {
            @Override
            public void onChanged(HashMap<String, List<String>> stringListHashMap) {
                // Log.i("DataTaken", String.valueOf(stringListHashMap));

            }
        });
        model.getShopListInfo().observe(MainActivity.this, new Observer<HashMap<String, Datatype_ShopList>>() {
            @Override
            public void onChanged(HashMap<String, Datatype_ShopList> info) {
                for (String key : info.keySet()) {
                    if (key != "" || key != null)
                        Log.i("DataTakenMain", String.valueOf(info.get("khalekuzzaman91@gmail.com").DragList));
                }

            }
        });


        modelDistrict = new ViewModelProvider(this).get(ViewModel_AllDistrictList.class);
        modelDistrict.getDistrictListHashMap().observe(MainActivity.this, new Observer<HashMap<String, List<String>>>() {
            @Override
            public void onChanged(HashMap<String, List<String>> Dis) {
                //   Log.i("DataTaken", String.valueOf(Dis));
            }
        });


    }

    private void setLocation() {
        districtList = new ArrayList<>();
        districtList = modelDistrict.getDistrictList().getValue();


    }

    private void setSubDistrict(String s) {
        subDistrictList = new ArrayList<>();
        subDistrictList = modelDistrict.getDistrictListHashMap().getValue().get(s);
        Log.i("SubDistrict", String.valueOf(subDistrictList));



    }

}