package com.SmartTech.hrapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.VacationsHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.VacationsModel;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class VacationsAdapter extends RecyclerView.Adapter<VacationsHolder> {

    private ArrayList<VacationsModel> arrayList;
    private Context context;
    private OnPress onPress,onPressMore;

    public VacationsAdapter(ArrayList<VacationsModel> arrayList, Context context, OnPress onPress, OnPress onPressMore) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
        this.onPressMore = onPressMore;
    }

    @NonNull
    @Override
    public VacationsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.vacationsHolder(parent.getContext(),parent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VacationsHolder holder, final int position) {

        // model
        VacationsModel model=arrayList.get(position);

        //name
        holder.name.setText(model.getName());

        // count position
        holder.countPosition.setText(model.getCountPosition()+" "+context.getResources().getString(R.string.postions_has_vac));

        // ratio
        holder.ratio.append(" "+model.getRatio()+" %");

        // last date
        holder.lastDate.append(" "+model.getLastDate());

        // click
        holder.layoutContanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPress.onClick(v,position);
            }
        });

        // click more
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPressMore.onClick(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
