package com.example.medicindelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.medicindelivery.datatypes.Datatype_ShopList;
import com.example.medicindelivery.viewmodels.SugesstionList_VM;
import com.example.medicindelivery.viewmodels.SugesstionList_VM;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_DragList_Shop_Keeper extends AppCompatActivity {
    Button save;
    Button remove;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser user;
    SugesstionList_VM model;
    List<String> DragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_list_shop_keeper);


        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
//        user = mAuth.getCurrentUser();
//        String emailCurrentUser = user.getEmail();
        AutoCompleteTextView v = findViewById(R.id.autoCompleteTextView);
        DragList = new ArrayList<>();


        model = new ViewModelProvider(this).get(SugesstionList_VM.class);
        model.getDragList().observe(Activity_DragList_Shop_Keeper.this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                DragList = strings;
                Log.i("ListData", String.valueOf(strings));
                Log.i("ListDataD", String.valueOf(DragList));
                ArrayAdapter adapter = new ArrayAdapter(Activity_DragList_Shop_Keeper.this, R.layout.layout_suggestion, DragList);
                v.setAdapter(adapter);

            }
        });


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


        DocumentReference doc = db.collection("ShopList")
                .document("khalekuzzaman91@gmail.com");
        doc.update("DragList", FieldValue.arrayUnion(dragName));
        addToSugesstion(dragName);


    }

    private void removeDrag(String dragName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference doc = db.collection("ShopList")
                .document("khalekuzzaman91@gmail.com");
        doc.update("DragList", FieldValue.arrayRemove(dragName));

    }

    private void addToSugesstion(String dragName) {
        DocumentReference doc = db.collection("SugesstionList")
                .document("list");
        doc.update("drag", FieldValue.arrayUnion(dragName));
    }


}