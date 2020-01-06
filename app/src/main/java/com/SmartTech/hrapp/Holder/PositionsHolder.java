package com.SmartTech.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.R;

public class PositionsHolder extends RecyclerView.ViewHolder {

    public TextView name,count,salary,dueDay;
    public ImageView imageMore;
    public RelativeLayout layout;

    public PositionsHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.r_pos_name);
        count=(TextView)itemView.findViewById(R.id.r_pos_countUsers);
        salary=(TextView)itemView.findViewById(R.id.r_pos_VS);
        dueDay=(TextView)itemView.findViewById(R.id.r_pos_day);
        imageMore=(ImageView)itemView.findViewById(R.id.r_pos_more);
        layout=(RelativeLayout)itemView.findViewById(R.id.r_pos_clickcontainer);
    }
}
