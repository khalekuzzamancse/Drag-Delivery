package com.example.medicindelivery.viewholders;


import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicindelivery.R;

public class VH_OrderActivity extends RecyclerView.ViewHolder {
    public TextView TextView_ViewHolder_DragName;
    public AutoCompleteTextView DropDown;
    public ImageButton delete;

    public VH_OrderActivity(@NonNull View itemView) {
        super(itemView);
        TextView_ViewHolder_DragName = itemView.findViewById(R.id.DragNameOrder);
        DropDown=itemView.findViewById(R.id.DropDown);
        delete=itemView.findViewById(R.id.deleteItemOrder);

    }


}
