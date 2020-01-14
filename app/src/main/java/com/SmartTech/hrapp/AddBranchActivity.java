package com.SmartTech.hrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class AddBranchActivity extends MyActivity {


    private EditText eName;
    private Button btnSubmit;

    private boolean isEdit;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        // is edit
        isEdit=getIntent().getBooleanExtra("is_edit",false);

        // init
        eName=(EditText)findViewById(R.id.add_branch_eName);
        btnSubmit=(Button)findViewById(R.id.add_branch_btn);

        if (isEdit){
            getReadyForEdit();
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= eName.getText().toString().trim();
                if (name.length()<4){
                    eName.findFocus();
                    eName.setError(getErrorRequire());
                }else {
                    createVacation(name);
                }

            }
        });
    }

    private void createVacation(final String name) {
        getProgressToShow().show();
        String url=null;
        int method;
        if (isEdit){
            // edit put
            url = getBranchesUrl()+"/"+id;
            method = 2;
        }else {
            // create post
            url = getBranchesUrl();
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

                return map;
            }
        };
        Myvollysinglton.getInstance(getContext()).addtorequst(request);
    }

    private void getReadyForEdit() {
        eName.setText(getIntent().getStringExtra("name"));

        id=getIntent().getStringExtra("id");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }
}
