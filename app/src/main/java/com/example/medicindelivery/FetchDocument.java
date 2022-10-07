package com.example.medicindelivery;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public interface FetchDocument {
    public void fetched(Task<DocumentSnapshot> snapshot);
}
