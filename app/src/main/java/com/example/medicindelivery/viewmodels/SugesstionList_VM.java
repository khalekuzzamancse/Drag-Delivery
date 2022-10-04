package com.example.medicindelivery.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.medicindelivery.datatypes.Datatype_ShopList;
import com.example.medicindelivery.datatypes.SuggestionList_DataType;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SugesstionList_VM extends ViewModel {
    FirebaseFirestore db;
    private MutableLiveData<List<String>> DragList;

    public SugesstionList_VM() {
        List<String> l = new ArrayList<>();
        DragList = new MutableLiveData<>(l);

        db = FirebaseFirestore.getInstance();
        db.collection("SugesstionList")
                .addSnapshotListener((QuerySnapshot q, FirebaseFirestoreException e) -> {
                    if (e != null) {
                        //
                    } else {


                        SuggestionList_DataType d = new SuggestionList_DataType();
                        List<SuggestionList_DataType> list = new ArrayList<>();
                        list = q.toObjects(SuggestionList_DataType.class);
                        List<String> tmp = new ArrayList<>();
                        tmp = DragList.getValue();
                        tmp = list.get(0).drag;
                        DragList.postValue(tmp);


                        Log.i("DataVM", String.valueOf(list.get(0).drag));
                    }
                });


    }


    public MutableLiveData<List<String>> getDragList() {
        return DragList;
    }
}
