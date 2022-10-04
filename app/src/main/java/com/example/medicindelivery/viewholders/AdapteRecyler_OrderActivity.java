package com.example.medicindelivery.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicindelivery.Order_Activity;
import com.example.medicindelivery.R;
import com.example.medicindelivery.datatypes.DataType_OrderList;

import java.util.ArrayList;
import java.util.List;

public class AdapteRecyler_OrderActivity extends RecyclerView.Adapter<VH_OrderActivity> {
    Context context;
    List<DataType_OrderList> list;

    public AdapteRecyler_OrderActivity(Context context, List<DataType_OrderList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH_OrderActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewOfRecyclerLayout = LayoutInflater.from(context).
                inflate(R.layout.layout_recycler_order_activity, parent, false);
        VH_OrderActivity viewHolder = new VH_OrderActivity(viewOfRecyclerLayout);
        viewHolder.delete.setOnClickListener(view -> {
            int pos=viewHolder.getAdapterPosition();
            list.remove(pos);
            notifyItemRemoved(pos);

        });



        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull VH_OrderActivity holder, int position) {
        String stationName = list.get(position).itemName;
        holder.TextView_ViewHolder_DragName.setText(stationName);
        holder.DropDown.setText(list.get(position).itemAmount);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
