package com.supportingonline.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.supportingonline.hrapp.R;

public class MenuHolder extends RecyclerView.ViewHolder{

    public TextView textView;
    public ImageView icon,arrow;
    public ExpandableRelativeLayout exp;
    public RecyclerView recyclerView;
    public RelativeLayout layout;

    public MenuHolder(@NonNull View itemView) {
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.r_menu_title);
        icon=(ImageView)itemView.findViewById(R.id.r_menu_icon);
        arrow=(ImageView)itemView.findViewById(R.id.r_menu_arrow);
        exp=(ExpandableRelativeLayout)itemView.findViewById(R.id.r_menu_exp);
        recyclerView=(RecyclerView)itemView.findViewById(R.id.recycler_insideMenu);
        layout=(RelativeLayout)itemView.findViewById(R.id.r_menu_layout);

        exp.collapse();
    }
}
