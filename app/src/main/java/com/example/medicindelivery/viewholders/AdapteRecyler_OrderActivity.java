package com.example.medicindelivery.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicindelivery.R;

import java.util.List;

public class AdapteRecyler_OrderActivity extends RecyclerView.Adapter<VH_OrderActivity> {
    Context context;
    List<String> list;

    public AdapteRecyler_OrderActivity(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH_OrderActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewOfRecyclerLayout = LayoutInflater.from(context).
                inflate(R.layout.layout_recycler_order_activity, parent, false);
        VH_OrderActivity viewHolder = new VH_OrderActivity(viewOfRecyclerLayout);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull VH_OrderActivity holder, int position) {
        String stationName=list.get(position);
        holder.TextView_ViewHolder_DragName.setText(stationName);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
