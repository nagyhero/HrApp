package com.SmartTech.hrapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.SmartTech.hrapp.Activites.Users.UsersActivity;
import com.SmartTech.hrapp.Adapter.DevicesAdapter;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.MyRequestHead;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequestHead;
import com.SmartTech.hrapp.Custom.MySizes;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.Custom.SpaceRecycler_V;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.DeviceModel;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DevicesActivity extends MyActivity {


    private Toolbar toolbar;
    private TextView title;
    private View backView;
    private ImageView imageAdd;

    private RecyclerView recyclerView;
    private DevicesAdapter adapter;
    private ArrayList<DeviceModel> arrayList=new ArrayList<>();

    private  ShimmerFrameLayout shimmerContainer;


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
        shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.devices_shimmer);

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
                startActivity(new Intent(getContext(),FPDetailsActivity.class)
                .putExtra("id",arrayList.get(position).getId())
                .putExtra("name",arrayList.get(position).getName())
                );
                overridePendingTransition(R.anim.slide_up,R.anim.fadout);
            }
        }, new OnPress() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view, int position) {
                // press more
                popUp(view,position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new SpaceRecycler_V(MySizes.gethight(getContext())/50));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),RecyclerView.VERTICAL));
        recyclerView.setAdapter(adapter);

        // load devices
        loadDevices();

    }

    private void loadDevices() {

        // shimmer
        shimmerContainer.setVisibility(View.VISIBLE);
        shimmerContainer.startShimmerAnimation();


        StringRequest request=new MyRequest(getToken(),0,getDeviceUrl(),new OnSuccessRequest(getContext(),new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {



                    // shimmer
                    shimmerContainer.setVisibility(View.GONE);
                    if (object!=null) {

                        try {
                            JSONArray array = object.getJSONArray("success");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject deviceObject = array.getJSONObject(i);
                                String id = deviceObject.getString("id");
                                String name = deviceObject.getString("name");
                                String serial = deviceObject.getString("serial");
                                String ip = deviceObject.getString("ip");
                                String usersCount = deviceObject.getString("users");
                                String statues = deviceObject.getString("status");
                                String lastUpdate = deviceObject.getString("last_export");

                                DeviceModel model = new DeviceModel();
                                model.setId(id);
                                model.setIp(ip);
                                model.setName(name);
                                model.setUsersCount(usersCount);
                                model.setStatues(statues);
                                model.setLastUpdate(lastUpdate);
                                model.setSerial(serial);
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
                shimmerContainer.setVisibility(View.GONE);
            }
        }));
          request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Myvollysinglton.getInstance(getContext()).addtorequst(request);
    }

    // pop up menu
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void popUp(View view, final int position){
        PopupMenu popupMenu=new PopupMenu(getContext(),view);
        popupMenu.getMenuInflater().inflate(R.menu.device_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                DeviceModel model = arrayList.get(position);
                int id=item.getItemId();

                switch (id){
                    case R.id.menu_d_edit:

                        startActivity(new Intent(getContext(),AddDeviceActivity.class)
                        .putExtra("is_edit",true)
                        .putExtra("id",model.getId())
                        .putExtra("name",model.getName())
                        .putExtra("ip",model.getIp())
                        .putExtra("serial",model.getSerial()));
                        overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        break;
                    case R.id.menu_d_export:

                        break;
                    case R.id.menu_d_shutdown:

                        actionDevice(model.getId(),"shutdown");

                        break;
                    case R.id.menu_d_delete:
                        showDeleteDialogAlert(position);

                        break;

                }
                return true;
            }
        });

        popupMenu.show();
    }

    private void showDeleteDialogAlert(final int position){
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.are_you_sure)+"  "+arrayList.get(position).getName()+" !")
                // .setContentText("Won't be able to recover this file!")
                .setCancelText(getResources().getString(R.string.no_cancel))
                .setConfirmText(getResources().getString(R.string.yes_delete))
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // go to delete server
                        sDialog.dismissWithAnimation();
                        deleteUserFromServer(position);

                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText(getResources().getString(R.string.cancelled))
                                // .setContentText("Your imaginary file is safe :)")
                                .setConfirmText(getString(R.string.ok))
                                .showCancelButton(false)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                    }
                })

                .show();
    }

    private void deleteUserFromServer(int position){
        getProgressToShow().show();
        getProgress().dismissWithAnimation();
    }


    private void actionDevice(String id, final String type){

        // object to send
        JSONObject object =new JSONObject();

        final JSONArray array = new JSONArray();
        array.put(id);

        try {
            object.put("ids",array);
            object.put("type",type);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        getProgressToShow().show();


        JsonObjectRequest request=new MyRequestHead(getToken(), 1, getActionDeviceUrl(), object.toString(), new OnSuccessRequestHead(getContext(), new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {

                getProgress().dismissWithAnimation();

                if (object!=null){

                }

            }
        }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {
                getProgress().dismissWithAnimation();
            }
        }));

        Myvollysinglton.getInstance(this).addtorequst(request);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}
