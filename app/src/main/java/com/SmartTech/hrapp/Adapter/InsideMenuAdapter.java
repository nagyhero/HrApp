package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.MenuHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.MenuModel;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class InsideMenuAdapter extends RecyclerView.Adapter<MenuHolder>  {

    private ArrayList<MenuModel> arrayList;
    private OnPress onPress;
    private Context context;

    public InsideMenuAdapter(ArrayList<MenuModel> arrayList, OnPress onPress, Context context) {
        this.arrayList = arrayList;
        this.onPress = onPress;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.menuHolder(context,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, final int position) {


        // model
        MenuModel model = arrayList.get(position);

        // title
        holder.textView.setText(model.getTitle());

        // icon
        holder.icon.setBackgroundResource(model.getIcon());

        // has child
        boolean hasChild=false;
        if (arrayList.get(position).getListInside().size()>0){
            hasChild=true;
        }


        // arrow
        if (hasChild){
            holder.arrow.setVisibility(View.VISIBLE);
        }else {
            holder.arrow.setVisibility(View.INVISIBLE);
        }

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
