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

import com.SmartTech.hrapp.Adapter.BranchesAdapter;
import com.SmartTech.hrapp.Adapter.DepartmentsAdapter;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.MySizes;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.Custom.SpaceRecycler_V;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.BranchesModel;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BranchsActivity extends MyActivity {

    private Toolbar toolbar;
    private TextView title;
    private View backView;
    private ImageView imageAdd;

    private RecyclerView recyclerView;
    private BranchesAdapter adapter;
    private ArrayList<BranchesModel> arrayList=new ArrayList<>();


    private ShimmerFrameLayout shimmerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branchs);


        // init
        recyclerView=(RecyclerView)findViewById(R.id.recycler_branches);
        toolbar=(Toolbar)findViewById(R.id.branches_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(View) toolbar.findViewById(R.id.t_normal_back);
        imageAdd=(ImageView)toolbar.findViewById(R.id.t_normal_add);
        shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.branches_shimmer);

        //title
        title.setText(getResources().getString(R.string.branches));

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
                startActivity(new Intent(getContext(), AddBranchActivity.class));
                overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);
            }
        });

        // recycler
        arrayList.clear();
        adapter=new BranchesAdapter(arrayList, getContext(), new OnPress() {
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


        loadBranches();
    }

    private void loadBranches() {
        // shimmer
        shimmerContainer.setVisibility(View.VISIBLE);
        shimmerContainer.startShimmerAnimation();

        StringRequest request=new MyRequest(getToken(),0,getBranchesUrl(),new OnSuccessRequest(getContext(), new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {
                // shimmer
                 shimmerContainer.setVisibility(View.GONE);

                // id , name , employees , departments

                if (object!=null){
                    try {
                        JSONArray array = object.getJSONArray("success");
                        for (int i = 0 ;i<array.length();i++){

                            JSONObject branObject = array.getJSONObject(i);

                            String id= branObject.getString("id");
                            String name= branObject.getString("name");
                            String employees= branObject.getString("employees");
                            String departments= branObject.getString("departments");

                            BranchesModel model=new BranchesModel();
                            model.setId(id);
                            model.setName(name);
                            model.setEmployees(employees);
                            model.setDepartments(departments);

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
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }

    }
