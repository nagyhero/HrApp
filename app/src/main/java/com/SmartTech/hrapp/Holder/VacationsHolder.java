package com.SmartTech.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.R;

public class VacationsHolder extends RecyclerView.ViewHolder {

    public ImageView more;
    public TextView name,countPosition,ratio,lastDate;
    public RelativeLayout layoutContanier;
    public VacationsHolder(@NonNull View itemView) {
        super(itemView);

        more=(ImageView)itemView.findViewById(R.id.r_vac_more);
        name=(TextView)itemView.findViewById(R.id.r_vac_name);
        countPosition=(TextView)itemView.findViewById(R.id.r_vac_countPosition);
        ratio=(TextView)itemView.findViewById(R.id.r_vac_ratio);
        lastDate=(TextView)itemView.findViewById(R.id.r_vac_lastDate);
        layoutContanier=(RelativeLayout) itemView.findViewById(R.id.r_vac_clickcontainer);
    }
}
