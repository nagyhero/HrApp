package com.SmartTech.hrapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.SmartTech.hrapp.Activites.Home.HomeActivity;
import com.SmartTech.hrapp.Activites.Login.LoginActivity;

public class MainActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getToken().equals("")){
            startActivity(new Intent(this,LoginActivity.class));
        }else {
            Log.d("role",getRole());
                startActivity(new Intent(this, HomeActivity.class)
                );


        }
        finish();


    }




}
