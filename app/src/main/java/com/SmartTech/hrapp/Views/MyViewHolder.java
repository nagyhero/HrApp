package com.SmartTech.hrapp.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SmartTech.hrapp.Holder.HeadCardHolder;
import com.SmartTech.hrapp.Holder.MenuHolder;
import com.SmartTech.hrapp.Holder.NotificationsHolder;
import com.SmartTech.hrapp.Holder.SettingHolder;
import com.SmartTech.hrapp.Holder.UsersHolder;

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
