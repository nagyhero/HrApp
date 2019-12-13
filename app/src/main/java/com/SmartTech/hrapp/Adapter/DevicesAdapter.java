package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.DeviceHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.DeviceModel;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class DevicesAdapter extends RecyclerView.Adapter<DeviceHolder> {

    private ArrayList<DeviceModel> arrayList;
    private Context context;
    private OnPress onPress,onPressMore;

    public DevicesAdapter(ArrayList<DeviceModel> arrayList, Context context, OnPress onPress, OnPress onPressMore) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
        this.onPressMore = onPressMore;
    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.deviceHolder(parent.getContext(),parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, final int position) {

        // model
        DeviceModel model=arrayList.get(position);


        // click
        holder.clickContanier.setOnClickListener(new View.OnClickListener() {
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

        // name
        holder.name.setText(model.getName());

        // ip
        holder.ip.append(model.getIp());

        // serial
        holder.serial.append(model.getSerial());

        // user count
        holder.userCount.setText(model.getUsersCount() + " "+ context.getResources().getString(R.string.users));

        // last update
        holder.lastUpdate.setText(context.getResources().getString(R.string.lastupdate)+" "+model.getLastUpdate());

        // statues
        holder.statues.setText(model.getStatues());
        if (model.getStatues().equals("Online")){
            holder.statues.setTextColor(context.getResources().getColor(R.color.custom3));
        }else {
            holder.statues.setTextColor(context.getResources().getColor(R.color.colorAccent));

        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
