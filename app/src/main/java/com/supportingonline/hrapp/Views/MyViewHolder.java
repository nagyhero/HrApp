package com.supportingonline.hrapp.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supportingonline.hrapp.Adapter.UsersAdapter;
import com.supportingonline.hrapp.Holder.HeadCardHolder;
import com.supportingonline.hrapp.Holder.MenuHolder;
import com.supportingonline.hrapp.Holder.NotificationsHolder;
import com.supportingonline.hrapp.Holder.SettingHolder;
import com.supportingonline.hrapp.Holder.UsersHolder;

public class MyViewHolder {

    public static UsersHolder myuserholder(Context context, int layout, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(layout,viewGroup,false);

       return  new UsersHolder(view);
    }

    public static HeadCardHolder headCardHolder(Context context, int layout, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(layout,viewGroup,false);
        return  new HeadCardHolder(view);
    }

    public static MenuHolder menuHolder(Context context, int layout, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(layout,viewGroup,false);
        return  new MenuHolder(view);
    }

    public static NotificationsHolder notificationsHolder(Context context, int layout, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(layout,viewGroup,false);
        return  new NotificationsHolder(view);
    }
    public static SettingHolder settingHolder(Context context, int layout, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(layout,viewGroup,false);
        return  new SettingHolder(view);
    }
}
