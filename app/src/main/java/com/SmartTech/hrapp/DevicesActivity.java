package com.SmartTech.hrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.SmartTech.hrapp.Activites.Users.UsersActivity;
import com.SmartTech.hrapp.Adapter.DevicesAdapter;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.MySizes;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.Custom.SpaceRecycler_V;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.DeviceModel;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DevicesActivity extends MyActivity {


    private Toolbar toolbar;
    private TextView title;
    private View backView;
    private ImageView imageAdd;

    private RecyclerView recyclerView;
    private DevicesAdapter adapter;
    private ArrayList<DeviceModel> arrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        // init
        recyclerView=(RecyclerView)findViewById(R.id.recycler_devices);
        toolbar=(Toolbar)findViewById(R.id.devices_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(View) toolbar.findViewById(R.id.t_normal_back);
        imageAdd=(ImageView)toolbar.findViewById(R.id.t_normal_add);

        //title
        title.setText(getResources().getString(R.string.devices));

        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // add users
        imageAdd.setVisibility(View.VISIBLE);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddDeviceActivity.class));
                overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);
            }
        });

        // recycler
        arrayList.clear();
        adapter=new DevicesAdapter(arrayList, getContext(), new OnPress() {
            @Override
            public void onClick(View view, int position) {
                // press
            }
        }, new OnPress() {
            @Override
            public void onClick(View view, int position) {
                // press more
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new SpaceRecycler_V(MySizes.gethight(getContext())/20));
        recyclerView.setAdapter(adapter);

        // load devices
        loadDevices();
    }

    private void loadDevices() {
        StringRequest request=new MyRequest(getToken(),0,getDeviceUrl(),new OnSuccessRequest(new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {
                Log.i("devices",object.toString());
                if (object.has("success")){
                    try {
                        JSONArray array=object.getJSONArray("success");
                        for (int i=0;i<array.length();i++){
                            JSONObject deviceObject = array.getJSONObject(i);
                            String id=deviceObject.getString("id");
                            String name=deviceObject.getString("name");
                            String ip=deviceObject.getString("ip");
                            String usersCount=deviceObject.getString("users");
                            String statues=deviceObject.getString("status");
                            String lastUpdate=deviceObject.getString("last_export");

                            DeviceModel model=new DeviceModel();
                            model.setId(id);
                            model.setIp(ip);
                            model.setName(name);
                            model.setUsersCount(usersCount);
                            model.setStatues(statues);
                            model.setLastUpdate(lastUpdate);
                            arrayList.add(model);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {

            }
        }));

        Myvollysinglton.getInstance(getContext()).addtorequst(request);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}