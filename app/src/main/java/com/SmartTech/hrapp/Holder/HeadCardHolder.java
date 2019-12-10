package com.SmartTech.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.R;

public class HeadCardHolder extends RecyclerView.ViewHolder{

    public TextView title;
    public ImageView icon;

    public HeadCardHolder(@NonNull View itemView) {
        super(itemView);
        title=(TextView)itemView.findViewById(R.id.r_card_title);
        icon=(ImageView) itemView.findViewById(R.id.r_card_icon);
    }
}
