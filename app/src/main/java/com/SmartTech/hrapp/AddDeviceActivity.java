package com.SmartTech.hrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SmartTech.hrapp.Activites.Home.HomeActivity;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddDeviceActivity extends MyActivity {

    private EditText eName,eip,eSerial;
    private Button btn;
    private boolean isEdit;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        // is edit
        isEdit=getIntent().getBooleanExtra("is_edit",false);

        // init
        eName=(EditText)findViewById(R.id.add_device_ename);
        eip=(EditText)findViewById(R.id.add_device_eip);
        eSerial=(EditText)findViewById(R.id.add_device_serial);
        btn=(Button)findViewById(R.id.add_device_btn);

        if (isEdit){
            getReadyForEdit();
        }


        // btn
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=eName.getText().toString().trim();
                String ip=eip.getText().toString().trim();
                String serial=eSerial.getText().toString().trim();
                if (name.length()<5){
                    eName.findFocus();
                    eName.setError("");
                }
                else if (ip.length()<8 || !ip.contains(".")){
                    eip.findFocus();
                    eip.setError("");
                }
                else if (serial.length()<8){
                    eSerial.findFocus();
                    eSerial.setError("");
                }

                else {


                    // create or edit device from server
                    createDevice(name,ip,serial);
                }
            }
        });



    }

    private void getReadyForEdit() {
        eName.setText(getIntent().getStringExtra("name"));
        eip.setText(getIntent().getStringExtra("ip"));
        eSerial.setText(getIntent().getStringExtra("serial"));
        id=getIntent().getStringExtra("id");
    }

    private void createDevice(final String name, final String ip, final String serial) {
        getProgressToShow().show();
        String url=null;
        int method;
        if (isEdit){
            // edit put
            url = getDeviceUrl()+"/"+id+"/edit";
            method = 2;
        }else {
            // create post
            url = getDeviceUrl();
            method = 1;
        }
        StringRequest request=new MyRequest(getToken(),method,url,new OnSuccessRequest(getContext(),new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {

                getProgress().dismissWithAnimation();

                   //startActivity(new Intent(getContext(), HomeActivity.class));
                  // finishAffinity();


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
                map.put("ip_address",ip);
                map.put("serial",serial);

                return map;
            }
        };
        Myvollysinglton.getInstance(getContext()).addtorequst(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}
