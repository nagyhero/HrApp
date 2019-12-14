package com.SmartTech.hrapp.Api;

import android.content.Context;
import android.util.Log;

import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class OnSuccessRequestHead implements Response.Listener<JSONObject> {


    private SuccessCall successCall;
    private Context context;

    public OnSuccessRequestHead(Context context, SuccessCall successCall) {
        this.successCall = successCall;
        this.context = context;
    }


    @Override
    public void onResponse(JSONObject response) {

        Log.i("SSSSS",response.toString());
        if (response.has("success")) {
            successCall.OnBack(response);
        }else if (response.has("message")){

            successCall.OnBack(null);

            try {
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText(response.getString("message"))
                        .show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
