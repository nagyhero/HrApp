package com.SmartTech.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.R;

public class AttendanceHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView name,subName,date,status;
    public RelativeLayout userNameView;
    public AttendanceHolder(@NonNull View itemView) {
        super(itemView);

        imageView=(ImageView)itemView.findViewById(R.id.r_att_image);
        name=(TextView)itemView.findViewById(R.id.r_att_name);
        date=(TextView)itemView.findViewById(R.id.r_att_date);
        status=(TextView)itemView.findViewById(R.id.r_att_status);
        subName=(TextView)itemView.findViewById(R.id.r_att_subname);
        userNameView=(RelativeLayout) itemView.findViewById(R.id.r_att_nameview_layout);

    }
}
