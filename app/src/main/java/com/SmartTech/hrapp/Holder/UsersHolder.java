package com.SmartTech.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.R;

public class UsersHolder  extends RecyclerView.ViewHolder {

    public ImageView more,imageView;
    public TextView textClick,name,subName,positionName;
    public RelativeLayout layoutContanier,userNameView;



    public UsersHolder(@NonNull View itemView) {
        super(itemView);

        more=(ImageView)itemView.findViewById(R.id.r_users_more);
        imageView=(ImageView)itemView.findViewById(R.id.r_users_image);
        textClick=(TextView)itemView.findViewById(R.id.r_users_clicktext);
        name=(TextView)itemView.findViewById(R.id.r_users_name);
        subName=(TextView)itemView.findViewById(R.id.r_users_subname);
        positionName=(TextView)itemView.findViewById(R.id.r_users_positionname);
        layoutContanier=(RelativeLayout) itemView.findViewById(R.id.r_users_clickcontainer);
        userNameView=(RelativeLayout) itemView.findViewById(R.id.r_users_nameview_layout);
    }

}