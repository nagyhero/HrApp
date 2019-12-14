package com.SmartTech.hrapp.Api;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyRequestHead extends JsonObjectRequest {
    private String token;

    public MyRequestHead(String token,int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
        this.token=token;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> map=new HashMap<>();
        map.put("Authorization","Bearer "+token);
        map.put("X-Requested-With","XMLHttpRequst");
        map.put("Accept","application/json");
        return map;
    }
}
