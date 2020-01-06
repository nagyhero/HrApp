package com.SmartTech.hrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.SmartTech.hrapp.Adapter.PositionsAdapter;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.MySizes;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.Custom.SpaceRecycler_V;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.PositionsModel;
import com.SmartTech.hrapp.Model.VacationsModel;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PositionsActivity extends MyActivity {

    private Toolbar toolbar;
    private TextView title;
    private View backView;
    private ImageView imageAdd;

    private RecyclerView recyclerView;
    private PositionsAdapter adapter;
    private ArrayList<PositionsModel> arrayList = new ArrayList<>();

    private ShimmerFrameLayout shimmerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positions);

        // init
        recyclerView=(RecyclerView)findViewById(R.id.recycler_positions);
        toolbar=(Toolbar)findViewById(R.id.positions_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(View) toolbar.findViewById(R.id.t_normal_back);
        imageAdd=(ImageView)toolbar.findViewById(R.id.t_normal_add);
        shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.positions_shimmer);

        //title
        title.setText(getResources().getString(R.string.positions));

        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // add
        imageAdd.setVisibility(View.VISIBLE);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddPositionActivity.class));
                overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);
            }
        });

        // recycler
        arrayList.clear();
        adapter=new PositionsAdapter(arrayList, getContext(), new OnPress() {
            @Override
            public void onClick(View view, int position) {

            }
        }, new OnPress() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new SpaceRecycler_V(MySizes.gethight(getContext())/60));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),RecyclerView.VERTICAL));
        recyclerView.setAdapter(adapter);

        loadPositions();
    }

    private void loadPositions() {
        // shimmer
        shimmerContainer.setVisibility(View.VISIBLE);
        shimmerContainer.startShimmerAnimation();

        StringRequest request=new MyRequest(getToken(),0,getPositionsUrl(),new OnSuccessRequest(getContext(), new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {
                // shimmer
              shimmerContainer.setVisibility(View.GONE);


                // id , name , employees , avg_salary , due_day

                if (object!=null){
                    try {
                        JSONArray array = object.getJSONArray("success");
                        for (int i = 0 ;i<array.length();i++){

                            JSONObject posObject = array.getJSONObject(i);

                            String id =posObject.getString("id");
                            String name =posObject.getString("name");
                            String employees =posObject.getString("employees");
                            String avgSalary =posObject.getString("avg_salary");
                            String dueDay =posObject.getString("due_day");

                            PositionsModel model =new PositionsModel();
                            model.setId(id);
                            model.setName(name);
                            model.setEmployees(employees);
                            model.setAvg_salary(avgSalary);
                            model.setDue_day(dueDay);

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
                // shimmer
               shimmerContainer.setVisibility(View.GONE);

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
