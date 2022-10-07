package com.example.medicindelivery;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Button;

import com.example.medicindelivery.datatypes.Datatype_ShopList;
import com.example.medicindelivery.viewmodels.DataClass;
import com.example.medicindelivery.viewmodels.ViewModel_AllDistrictList;
import com.example.medicindelivery.viewmodels.ViewModel_ShopList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    ViewModel_ShopList model;
    ViewModel_AllDistrictList modelDistrict;
    List<String> districtList;
    List<String> subDistrictList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        donateHistory();
        Button sh = findViewById(R.id.button);
        sh.setOnClickListener(view -> {
            startActivity(new Intent(this, Activity_DragList_Shop_Keeper.class));
        });
        Button cus = findViewById(R.id.button2);
        cus.setOnClickListener(view -> {
            startActivity(new Intent(this, Order_Activity.class));
        });
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

    private void extract(List<Map<String, String>> readHistory) {
        List<String> ListDate = new ArrayList<>();
        Map<String, Map<String, String>> HistoryByDate = new HashMap<>();
        //getting the map by date
        for (int i = 0; i < readHistory.size(); i++) {
            //picking the date
            String date = readHistory.get(i).get("date");
            //adding the date to the dateList
            ListDate.add(date);
            //picking the history from the corresponding date
            Map<String, String> temp = readHistory.get(i);
            //removing the data field from the picked map
            temp.remove("date");
            //putting the history map under the corresponding date
            HistoryByDate.put(date, temp);
        }
        Log.i("Fetched", String.valueOf(ListDate));
        for (int i = 0; i < ListDate.size(); i++) {
            String date=ListDate.get(i);
            Log.i("Fetched Date",date+"\n");
            Log.i("Fetched", String.valueOf(HistoryByDate.get(date)));
        }


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


    private void donateHistory() {

        OnCompleteListener<DocumentSnapshot> callbackObject = new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        List<Map<String, String>> dates = new ArrayList<>();
                        dates = (List<Map<String, String>>) document.get("dates");
                        extract(dates);

                    }
                }
            }

        };

        //
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference cities = db.collection("cities");
        DocumentReference docRef = cities.document("SF");
        Task<DocumentSnapshot> snapshotTask = docRef.get();
        snapshotTask.addOnCompleteListener(callbackObject);
    }
}

