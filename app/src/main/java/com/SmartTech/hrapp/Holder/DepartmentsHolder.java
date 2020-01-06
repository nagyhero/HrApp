package com.SmartTech.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.R;

public class DepartmentsHolder extends RecyclerView.ViewHolder {

    public TextView name,count;
    public ImageView imageMore;
    public RelativeLayout layout;

    public DepartmentsHolder(@NonNull View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.r_dep_name);
        count=(TextView)itemView.findViewById(R.id.r_dep_countUsers);
        imageMore=(ImageView)itemView.findViewById(R.id.r_dep_more);
        layout=(RelativeLayout)itemView.findViewById(R.id.r_dep_clickcontainer);
    }
}
