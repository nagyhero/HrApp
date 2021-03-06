package com.SmartTech.hrapp.Api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.SmartTech.hrapp.R;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.SmartTech.hrapp.Activites.Login.LoginActivity;
import com.SmartTech.hrapp.Custom.MySharedPref;
import com.SmartTech.hrapp.InterFaces.ErrorCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class OnErrorRequest implements Response.ErrorListener {

    private Context context;
    private ErrorCall errorCall;

    public OnErrorRequest(Context context,ErrorCall errorCall) {
        this.context = context;
        this.errorCall=errorCall;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    // Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
       // Log.i("main_error", Objects.requireNonNull(error.getMessage()));
        errorCall.OnBack();

        if (error.networkResponse!=null) {
            try {
                String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                JSONObject object = new JSONObject(body);

                 Log.i("main_error",body);
                if (object.has("message")) {
                    if (object.getString("message").contains("Unauthenticated")){
                         MySharedPref.removerAfterLogout(context);
                        ((Activity)context).finishAffinity();
                        context.startActivity(new Intent(context, LoginActivity.class));
                    }else {
                       // Toast.makeText(context, object.getString("message"), Toast.LENGTH_SHORT).show();
                        StringBuilder msg = new StringBuilder();
                               msg.append(object.getString("message"));
                               if (object.has("errors")){
                                   msg.append("\n").append(object.getString("errors"));
                               }
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText(msg.toString())
                                .show();
                        errorCall.OnBack();
                    }


                } else {
                 errorCall.OnBack();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(context.getResources().getString(R.string.errorconn))
                    .show();

        }


    }
}
