package com.example.medicindelivery.viewholders;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicindelivery.R;

public class VH_OrderActivity extends RecyclerView.ViewHolder {
    public TextView TextView_ViewHolder_DragName;

    public VH_OrderActivity(@NonNull View itemView) {
        super(itemView);
        TextView_ViewHolder_DragName = itemView.findViewById(R.id.DragNameOrder);

    }


}
