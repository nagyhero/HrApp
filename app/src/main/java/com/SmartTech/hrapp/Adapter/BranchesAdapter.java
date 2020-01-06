package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.BranchesHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.BranchesModel;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class BranchesAdapter extends RecyclerView.Adapter<BranchesHolder> {

    private ArrayList<BranchesModel> arrayList;
    private Context context;
    private OnPress onPress,onPressMore;

    public BranchesAdapter(ArrayList<BranchesModel> arrayList, Context context, OnPress onPress, OnPress onPressMore) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
        this.onPressMore = onPressMore;
    }

    @NonNull
    @Override
    public BranchesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.branchesHolder(parent.getContext(),parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BranchesHolder holder,final int position) {

        BranchesModel model=arrayList.get(position);

        // click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPress.onClick(v,position);
            }
        });

        // click more
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPressMore.onClick(v,position);
            }
        });


        holder.name.setText(model.getName());

        holder.countUsers.append(" " + model.getEmployees());

        holder.countDepartments.append(" "+model.getDepartments());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
