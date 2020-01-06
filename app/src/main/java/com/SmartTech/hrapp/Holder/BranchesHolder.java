package com.SmartTech.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.R;

public class BranchesHolder extends RecyclerView.ViewHolder {

    public TextView name,countUsers,countDepartments;
    public ImageView imageMore;
    public RelativeLayout layout;

    public BranchesHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.r_bran_name);
        countUsers=(TextView)itemView.findViewById(R.id.r_bran_countUsers);
        countDepartments=(TextView)itemView.findViewById(R.id.r_bran_countDepartments);
        imageMore=(ImageView)itemView.findViewById(R.id.r_bran_more);
        layout=(RelativeLayout)itemView.findViewById(R.id.r_bran_clickcontainer);
    }
}
