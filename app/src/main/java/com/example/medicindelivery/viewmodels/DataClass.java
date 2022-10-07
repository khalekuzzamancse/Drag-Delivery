package com.example.medicindelivery.viewmodels;

import android.util.Log;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataClass {
    public List<Map<String, String>> dates;

    @Override
    public String toString() {
        return "DataClass{" +
                "dates=" + dates +
                '}';
    }
}

