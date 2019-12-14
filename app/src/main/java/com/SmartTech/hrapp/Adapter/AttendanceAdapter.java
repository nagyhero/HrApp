package com.SmartTech.hrapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.Holder.AttendanceHolder;
import com.SmartTech.hrapp.Model.AttendanceModel;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.Views.MyViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceHolder> {

    private ArrayList<AttendanceModel> arrayList;
    private Context context;

    public AttendanceAdapter(ArrayList<AttendanceModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AttendanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MyViewHolder.attendanceHolder(parent.getContext(),parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceHolder holder, int position) {

        // model
        AttendanceModel model = arrayList.get(position);

        // name
        holder.name.setText(model.getName());

        // date
        holder.date.setText(model.getDate());

        // status
        holder.status.setText(model.getStatus());


        // colors
        int headColor=R.color.colorPrimary;
        if (model.getGender().equals("female")){
            headColor=R.color.colorAccent;
        }

        // image
        if (model.getImage()==null){
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.userNameView.setVisibility(View.VISIBLE);
            holder.userNameView.setBackgroundTintList(ContextCompat.getColorStateList(context,headColor));
            holder.subName.setText(model.getName().substring(0,1).toUpperCase());
        }else {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.userNameView.setVisibility(View.INVISIBLE);
            Glide.with(context).load(model.getImage())
                    .into(holder.imageView);
        }




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
