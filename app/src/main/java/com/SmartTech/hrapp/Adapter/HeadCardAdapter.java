package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.HeadCardHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.HeadCardModel;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class HeadCardAdapter extends RecyclerView.Adapter<HeadCardHolder>{
    private ArrayList<HeadCardModel> arrayList;
    private Context context;
    private OnPress onPress;

    public HeadCardAdapter(ArrayList<HeadCardModel> arrayList, Context context, OnPress onPress) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
    }

    @NonNull
    @Override
    public HeadCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return MyViewHolder.headCardHolder(context,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadCardHolder holder, final int position) {

        // model
        HeadCardModel model=arrayList.get(position);

        // click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPress.onClick(v,position);
            }
        });

        // title
        holder.title.setText(model.getTitle());

        // icon
        holder.icon.setBackgroundResource(model.getIcon());
        holder.icon.setBackgroundTintList(context.getResources().getColorStateList(android.R.color.white));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
