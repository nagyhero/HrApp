package com.SmartTech.hrapp.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SmartTech.hrapp.Holder.AttendanceHolder;
import com.SmartTech.hrapp.Holder.BranchesHolder;
import com.SmartTech.hrapp.Holder.DepartmentsHolder;
import com.SmartTech.hrapp.Holder.DeviceHolder;
import com.SmartTech.hrapp.Holder.HeadCardHolder;
import com.SmartTech.hrapp.Holder.MenuHolder;
import com.SmartTech.hrapp.Holder.NotificationsHolder;
import com.SmartTech.hrapp.Holder.PositionsHolder;
import com.SmartTech.hrapp.Holder.SettingHolder;
import com.SmartTech.hrapp.Holder.UsersHolder;
import com.SmartTech.hrapp.Holder.VacationsHolder;
import com.SmartTech.hrapp.R;

public class MyViewHolder {

    public static UsersHolder myuserholder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate( R.layout.recycler_users,viewGroup,false);

       return  new UsersHolder(view);
    }

    public static HeadCardHolder headCardHolder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_head_card,viewGroup,false);
        return  new HeadCardHolder(view);
    }

    public static MenuHolder menuHolder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_menu,viewGroup,false);
        return  new MenuHolder(view);
    }

    public static NotificationsHolder notificationsHolder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_notification,viewGroup,false);
        return  new NotificationsHolder(view);
    }
    public static SettingHolder settingHolder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate( R.layout.recycler_setting,viewGroup,false);
        return  new SettingHolder(view);
    }

    public static DeviceHolder deviceHolder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_devices,viewGroup,false);
        return  new DeviceHolder(view);
    }

    public static AttendanceHolder attendanceHolder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_attendance
                ,viewGroup,false);
        return  new AttendanceHolder(view);
    }

    public static VacationsHolder vacationsHolder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_vacations
                ,viewGroup,false);
        return  new VacationsHolder(view);
    }

    public static PositionsHolder positionsHolder(Context context,ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_positions
                ,viewGroup,false);

        return new PositionsHolder(view);

    }

    public static DepartmentsHolder departmentsHolder(Context context,ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_departments
                ,viewGroup,false);
        return new DepartmentsHolder(view);
    }

    public static BranchesHolder branchesHolder(Context context, ViewGroup viewGroup){
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_departments
                ,viewGroup,false);
        return new BranchesHolder(view);
    }
}
