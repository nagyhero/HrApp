package com.SmartTech.hrapp;

import android.app.AlertDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.SmartTech.hrapp.Custom.MySharedPref;

import java.util.ArrayList;
import java.util.Arrays;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MyActivity extends AppCompatActivity {




    public Context getContext(){

        return MyActivity.this;
    }

    public String getDomain(){

        return getResources().getString(R.string.domain);
    }

    public String getToken(){
        return MySharedPref.getdata(getContext(),"token");
    }

    public String getUserUrl(){

        return getDomain()+"api/users";
    }

    public String getDeviceUrl(){

        return getDomain()+"api/fp_devices";
    }
    public String getLogoutUrl(){

        return getDomain()+"api/logout";
    }

    public String getActionDeviceUrl(){
        return getDomain()+"api/fpd_actions";
    }

    public String getVacationsUrl(){

        return getDomain()+"api/vacations";
    }

    public String getPathImageUser(){

        return getDomain()+"imgs/users/";
    }

    public String getErrorServerMessage(){

        return getContext().getResources().getString(R.string.errorserver);
    }

    public String getName(){
        return MySharedPref.getdata(getContext(),"name");
    }

    public String getEmail(){
        return MySharedPref.getdata(getContext(),"email");
    }

    public String getImage(){
        return getPathImageUser()+MySharedPref.getdata(getContext(),"image");
    }
    public String getRole(){
        return MySharedPref.getdata(getContext(),"role");
    }

    public ArrayList<String> getAdminTitles(){
        ArrayList<String> arrayList=new ArrayList<>();
        String[] list=getResources().getStringArray(R.array.menu_titles);
        arrayList.addAll(Arrays.asList(list));
        return arrayList;
    }

    public ArrayList<Integer> getAdminIcons(){
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(R.drawable.ic_dashboard);
        arrayList.add(R.drawable.ic_user);
        arrayList.add(R.drawable.ic_user);
        arrayList.add(R.drawable.profile);
        arrayList.add(R.drawable.profile);
        arrayList.add(R.drawable.ic_logout);

        return arrayList;
    }

    public void showErrorDialog(String message){
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(message)
                .show();
    }

    private SweetAlertDialog alertProgress;
    public SweetAlertDialog getProgressToShow(){
      alertProgress =new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
      alertProgress.setTitle(getResources().getString(R.string.loading));
      return alertProgress;
    }
    public SweetAlertDialog getProgress(){
        return alertProgress;
    }
}
