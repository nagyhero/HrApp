package com.supportingonline.hrapp.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.supportingonline.hrapp.R;

public class SettingHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public SettingHolder(@NonNull View itemView) {
        super(itemView);

        title=(TextView)itemView.findViewById(R.id.r_setting_title);
    }
}
