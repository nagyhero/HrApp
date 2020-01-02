package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.PositionsHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.PositionsModel;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class PositionsAdapter extends RecyclerView.Adapter<PositionsHolder> {
    private ArrayList<PositionsModel> arrayList;
    private Context context;
    private OnPress onPress,onPressMore;

    public PositionsAdapter(ArrayList<PositionsModel> arrayList, Context context, OnPress onPress, OnPress onPressMore) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
        this.onPressMore = onPressMore;
    }

    @NonNull
    @Override
    public PositionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.positionsHolder(parent.getContext(),parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
