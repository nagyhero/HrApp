package com.SmartTech.hrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.SmartTech.hrapp.Adapter.AttendanceAdapter;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.AttendanceModel;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONObject;

import java.util.ArrayList;

public class FPDetailsActivity extends MyActivity {

    private ShimmerFrameLayout shimmerContainer;

    private RecyclerView recyclerView;
    private AttendanceAdapter adapter;
    private ArrayList<AttendanceModel> arrayList =new ArrayList<>();

    private String id,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpdetails);

        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");

        // init
        recyclerView = (RecyclerView)findViewById(R.id.recycler_attendance);
        shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.fpdetails_shimmer);


        // recycler
        arrayList.clear();
        adapter=new AttendanceAdapter(arrayList,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),RecyclerView.VERTICAL));
        recyclerView.setAdapter(adapter);

        // load
        loadAttend();
    }

    private void loadAttend() {
        StringRequest request = new MyRequest(getToken(),0,getDeviceUrl()+"/"+id,new OnSuccessRequest(getContext(), new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {

                shimmerContainer.setVisibility(View.GONE);

            }
        }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {
                shimmerContainer.setVisibility(View.GONE);

            }
        }));
        Myvollysinglton.getInstance(this).addtorequst(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadin,R.anim.slide_down);
    }
}
