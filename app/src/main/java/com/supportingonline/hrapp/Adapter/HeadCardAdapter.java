package com.supportingonline.hrapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.supportingonline.hrapp.Holder.HeadCardHolder;
import com.supportingonline.hrapp.InterFaces.OnPress;
import com.supportingonline.hrapp.Model.HeadCardModel;
import com.supportingonline.hrapp.R;
import com.supportingonline.hrapp.Views.MyViewHolder;

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


        return MyViewHolder.headCardHolder(context,R.layout.recycler_head_card,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadCardHolder holder, final int position) {

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
