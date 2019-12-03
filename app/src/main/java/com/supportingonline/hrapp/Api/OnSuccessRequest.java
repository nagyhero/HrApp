package com.supportingonline.hrapp.Api;

import com.android.volley.Response;
import com.supportingonline.hrapp.InterFaces.SuccessCall;

import org.json.JSONException;
import org.json.JSONObject;

public class OnSuccessRequest implements Response.Listener<String> {


    private SuccessCall successCall;

    public OnSuccessRequest(SuccessCall successCall) {
        this.successCall = successCall;
    }

    @Override
    public void onResponse(String response) {

        try {
            JSONObject object=new JSONObject(response);
            successCall.OnBack(object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
