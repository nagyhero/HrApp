package com.SmartTech.hrapp;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;


import com.SmartTech.hrapp.Activites.Home.HomeActivity;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddVacationActivity extends MyActivity {


    private EditText eName,eDays,eRatio;
    private RadioButton r1,r2;
    private Button btnSubmit;
    private LinearLayout ratioLayout;

    private boolean isDef= true;

    private boolean isEdit;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacation);

        // is edit
        isEdit=getIntent().getBooleanExtra("is_edit",false);

        // init
        eName=(EditText)findViewById(R.id.add_vac_ename);
        eRatio=(EditText)findViewById(R.id.add_vac_eRatio);
        eDays=(EditText)findViewById(R.id.add_vac_eDays);
        ratioLayout=(LinearLayout)findViewById(R.id.add_vac_ratioLayout);
        r1=(RadioButton) findViewById(R.id.add_vac_r1);
        r2=(RadioButton) findViewById(R.id.add_vac_r2);
        btnSubmit=(Button)findViewById(R.id.add_vac_btn);

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setChecked(true);
                r2.setChecked(false);
                ratioLayout.setVisibility(View.GONE);
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2.setChecked(true);
                r1.setChecked(false);
                ratioLayout.setVisibility(View.VISIBLE);
            }
        });

        if (isEdit){
            getReadyForEdit();
        }

        // btn
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= eName.getText().toString().trim();
                String days=eDays.getText().toString().trim();
                String ratio= eRatio.getText().toString().trim();
                int isPaid = 0;
                if (r1.isChecked()){
                    isPaid=1;
                    if (name.length()<3){
                        eName.findFocus();
                        eName.setError(getErrorRequire());
                    }else if (days.length()<1){
                        eDays.findFocus();
                        eDays.setError(getErrorRequire());
                    }else {
                        createVacation(name,days,ratio,String.valueOf(isPaid));
                    }
                }else {
                    if (name.length()<3){
                        eName.findFocus();
                        eName.setError(getErrorRequire());
                    }else if (days.length()<1){
                        eDays.findFocus();
                        eDays.setError(getErrorRequire());
                    }else if (ratio.length()<1){
                        eRatio.findFocus();
                        eRatio.setError(getErrorRequire());
                    }
                    else {
                        createVacation(name,days,ratio,String.valueOf(isPaid));
                    }
                }


            }
        });


    }

    private void createVacation(final String name, final String days, final String ratio, final String is_paid) {
        getProgressToShow().show();
        String url=null;
        int method;
        if (isEdit){
            // edit put
            url = getVacationsUrl()+"/"+id;
            method = 2;
        }else {
            // create post
            url = getVacationsUrl();
            method = 1;
        }
        StringRequest request=new MyRequest(getToken(),method,url,new OnSuccessRequest(getContext(),new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {

                getProgress().dismissWithAnimation();

                if (object!=null) {

                    startActivity(new Intent(getContext(), HomeActivity.class)
                            .putExtra("is_noti",true).putExtra("message","your update has been success")

                    );
                    finishAffinity();
                }


            }
        }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {
                getProgress().dismissWithAnimation();

            }
        })){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name",name);
                map.put("days",days);
                map.put("ratio",ratio);
                map.put("is_paid",is_paid);

                return map;
            }
        };
        Myvollysinglton.getInstance(getContext()).addtorequst(request);
    }

    private void getReadyForEdit() {
        eName.setText(getIntent().getStringExtra("name"));
        eRatio.setText(getIntent().getStringExtra("ratio"));
        eDays.setText(getIntent().getStringExtra("days"));


       boolean isDef =getIntent().getBooleanExtra("is_def",false);
       if (!isDef){
           r1.setChecked(true);
           r2.setChecked(false);
           ratioLayout.setVisibility(View.GONE);
       }
        id=getIntent().getStringExtra("id");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }

}
