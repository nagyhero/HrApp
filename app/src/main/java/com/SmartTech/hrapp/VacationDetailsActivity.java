package com.SmartTech.hrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class VacationDetailsActivity extends MyActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details);

        // data
        id=getIntent().getStringExtra("id");


        // load
        loadVacationDetails();
    }

    private void loadVacationDetails() {
        StringRequest request = new MyRequest(getToken(),0,getVacationsUrl()+"/"+id,new OnSuccessRequest(getContext(), new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {


            }
        }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {

            }
        }));

        Myvollysinglton.getInstance(this).addtorequst(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadin,R.anim.slide_down);

    }
}
