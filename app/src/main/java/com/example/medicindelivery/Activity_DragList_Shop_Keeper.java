package com.example.medicindelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_DragList_Shop_Keeper extends AppCompatActivity {
    Button save;
    Button remove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_list_shop_keeper);
        List<String> ListAllStation = new ArrayList<>();
        ListAllStation.add("Napa");
        ListAllStation.add("Histacin");
        ListAllStation.add("ACE+");
        ListAllStation.add("Max Pro");
        AutoCompleteTextView v = findViewById(R.id.autoCompleteTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.layout_suggestion, ListAllStation);

        v.setAdapter(adapter);
        save = findViewById(R.id.save);
        save.setOnClickListener(view -> {
            String dragName = v.getText().toString().trim();
            addDrag(dragName);
        });
        remove = findViewById(R.id.button3);
        remove.setOnClickListener(view -> {
            String dragName = v.getText().toString().trim();
            removeDrag(dragName);
        });



    }

    private void addDrag(String dragName) {
//        FirebaseAuth mAuth;
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        String email = user.getEmail();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference doc = db.collection("ShopList")
                .document("khalekuzzaman91@gmail.com");
        doc.update("DragList", FieldValue.arrayUnion(dragName));

    }

    private void removeDrag(String dragName) {
//        FirebaseAuth mAuth;
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        String email = user.getEmail();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference doc = db.collection("ShopList")
                .document("khalekuzzaman91@gmail.com");
        doc.update("DragList", FieldValue.arrayRemove(dragName));

    }

}