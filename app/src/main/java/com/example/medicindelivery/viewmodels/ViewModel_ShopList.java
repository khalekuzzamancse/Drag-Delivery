package com.example.medicindelivery.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.medicindelivery.datatypes.Datatype_ShopList;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewModel_ShopList extends ViewModel {

    private MutableLiveData<HashMap<String, List<String>>> ShopListWithDragList;
    private MutableLiveData<HashMap<String, Datatype_ShopList>> ShopListInfo;
    private MutableLiveData<List<String>> ShopList;

    public ViewModel_ShopList() {
        List<String> L5 = new ArrayList<>();
        L5.add("");
        HashMap<String, List<String>> HM = new HashMap<>();
        HM.put("", L5);
        //  initializeHashMap_District();
        ShopListWithDragList = new MutableLiveData<>(HM);
        //initialize district list;
        List<String> L = new ArrayList<>();
        ShopList = new MutableLiveData<>(L);
        //
        HashMap<String, Datatype_ShopList>tmpInfo=new HashMap<>();

        ShopListInfo = new MutableLiveData<HashMap<String, Datatype_ShopList>>(tmpInfo);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db = FirebaseFirestore.getInstance();
        db.collection("ShopList")
                .addSnapshotListener((QuerySnapshot q, FirebaseFirestoreException e) -> {
                    if (e != null) {
                        //
                    } else {


                        Datatype_ShopList d = new Datatype_ShopList();
                        List<Datatype_ShopList> list = new ArrayList<>();
                        list = q.toObjects(Datatype_ShopList.class);
                        setData(list);


                    }
                });


    }

    public MutableLiveData<List<String>> getShopList() {
        return ShopList;
    }


    public MutableLiveData<HashMap<String, List<String>>> getShopListHashMap() {
        return ShopListWithDragList;
    }

    public MutableLiveData<HashMap<String, Datatype_ShopList>> getShopListInfo() {
        return ShopListInfo;
    }

    private void setData(List<Datatype_ShopList> list) {

        for (int i = 0; i < list.size(); i++) {
            Datatype_ShopList l = new Datatype_ShopList();
            l = list.get(i);
            Log.i("DataTakenM", l.toString());
            List<String> existingDistrictList = new ArrayList<>();
            List<String> DragList = list.get(i).DragList;

            existingDistrictList = ShopList.getValue();
            String district = list.get(i).Id;
            existingDistrictList.add(district);
            ShopList.postValue(existingDistrictList);
            Log.i("Getting,MM", String.valueOf(existingDistrictList));

            HashMap<String, List<String>> tmp = new HashMap<>();
            tmp = ShopListWithDragList.getValue();
            tmp.put(district, DragList);
            Log.i("Getting,M", String.valueOf(tmp));
            ShopListWithDragList.postValue(tmp);
            //
            HashMap<String, Datatype_ShopList>tmpInfo=new HashMap<>();
            tmpInfo=ShopListInfo.getValue();
            tmpInfo.put(l.email,l);
            ShopListInfo.postValue(tmpInfo);


        }


    }

}
