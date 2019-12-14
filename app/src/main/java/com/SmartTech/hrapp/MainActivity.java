package com.SmartTech.hrapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.SmartTech.hrapp.Activites.Home.HomeActivity;
import com.SmartTech.hrapp.Activites.Login.LoginActivity;

public class MainActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Handler handler =new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getToken().equals("")){
                    startActivity(new Intent(getContext(),LoginActivity.class));
                }else {
                    Log.d("role",getRole());
                    startActivity(new Intent(getContext(), HomeActivity.class)
                    );


                }
                finish();


            }
        },2000);



    }




}
