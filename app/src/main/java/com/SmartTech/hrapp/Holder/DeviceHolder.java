package com.SmartTech.hrapp.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartTech.hrapp.R;

public class DeviceHolder extends RecyclerView.ViewHolder {

    public TextView name,ip,userCount,textclick,statues,lastUpdate;
    public RelativeLayout clickContanier;
    public ImageView more;

    public DeviceHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.r_devices_name);
        ip=(TextView)itemView.findViewById(R.id.r_devices_ip);
        userCount=(TextView)itemView.findViewById(R.id.r_devices_userCount);
        textclick=(TextView)itemView.findViewById(R.id.r_devices_clicktext);
        statues=(TextView)itemView.findViewById(R.id.r_devices_statues);
        lastUpdate=(TextView)itemView.findViewById(R.id.r_devices_lastUpdate);
        clickContanier=(RelativeLayout)itemView.findViewById(R.id.r_devices_clickcontainer);
        more=(ImageView)itemView.findViewById(R.id.r_devices_more);
    }
}
