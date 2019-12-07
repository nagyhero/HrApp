package com.supportingonline.hrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.supportingonline.hrapp.Activites.Home.HomeActivity;
import com.supportingonline.hrapp.Activites.Login.LoginActivity;
import com.supportingonline.hrapp.Api.MyRequest;

public class MainActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getToken().equals("")){
            startActivity(new Intent(this,LoginActivity.class));
        }else {
            Log.d("role",getRole());
                startActivity(new Intent(this, HomeActivity.class));


        }
        finish();


    }




}
