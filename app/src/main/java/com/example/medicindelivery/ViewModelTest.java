package com.example.medicindelivery;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class ViewModelTest extends ViewModel {



    private MutableLiveData<String> currentName;
    public ViewModelTest()
    {
        Log.i("Curr","constructor");
        currentName=new MutableLiveData<>("bismillah");

    }

    public LiveData<String> getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String name) {
      currentName.postValue(name);
    }
}

