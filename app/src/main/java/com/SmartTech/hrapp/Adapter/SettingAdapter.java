package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.SettingHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class SettingAdapter extends RecyclerView.Adapter<SettingHolder> {


    private ArrayList<String> arrayList;
    private Context context;
    private OnPress onPress;

    public SettingAdapter(ArrayList<String> arrayList, Context context, OnPress onPress) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
    }

    @NonNull
    @Override
    public SettingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.settingHolder(context,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingHolder holder, final int position) {

        // title
        holder.title.setText(arrayList.get(position));


        // click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPress.onClick(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
