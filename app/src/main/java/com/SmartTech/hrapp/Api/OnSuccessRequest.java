package com.SmartTech.hrapp.Api;

import android.content.Context;
import android.util.Log;

import com.SmartTech.hrapp.MyActivity;
import com.android.volley.Response;
import com.SmartTech.hrapp.InterFaces.SuccessCall;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class OnSuccessRequest implements Response.Listener<String> {


    private SuccessCall successCall;
    private Context context;

    public OnSuccessRequest(Context context,SuccessCall successCall) {
        this.successCall = successCall;
        this.context = context;
    }

    @Override
    public void onResponse(String response) {
        Log.i("SSSSS",response);

        try {
            JSONObject object=new JSONObject(response);
            if (object.has("success")) {
                successCall.OnBack(object);
            }else if (object.has("message")){

                successCall.OnBack(null);

                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText(object.getString("message"))
                        .show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
