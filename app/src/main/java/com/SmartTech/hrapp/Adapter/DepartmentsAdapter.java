package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.DepartmentsHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.DepartmentsModel;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsHolder> {

    private ArrayList<DepartmentsModel> arrayList;
    private Context context;
    private OnPress onPress,onPressMore;

    public DepartmentsAdapter(ArrayList<DepartmentsModel> arrayList, Context context, OnPress onPress, OnPress onPressMore) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
        this.onPressMore = onPressMore;
    }

    @NonNull
    @Override
    public DepartmentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.departmentsHolder(parent.getContext(),parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
