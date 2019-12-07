package com.SmartTech.hrapp;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.SmartTech.hrapp.Custom.MySharedPref;

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
    public String getLogoutUrl(){

        return getDomain()+"api/logout";
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
        return getDomain()+"imgs/users/"+MySharedPref.getdata(getContext(),"image");
    }
    public String getRole(){
        return MySharedPref.getdata(getContext(),"role");
    }
}
