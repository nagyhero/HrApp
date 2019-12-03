package com.supportingonline.hrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.supportingonline.hrapp.Adapter.SettingAdapter;
import com.supportingonline.hrapp.Custom.MySizes;
import com.supportingonline.hrapp.Custom.SpaceRecycler_V;
import com.supportingonline.hrapp.InterFaces.OnPress;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView title;
    private View backView;

    private RecyclerView recyclerView;
    private ArrayList<String> arrayList=new ArrayList<>();
    private SettingAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // init
        toolbar=(Toolbar)findViewById(R.id.setting_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(View) toolbar.findViewById(R.id.t_normal_back);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_setting);

        //title
        title.setText(getResources().getString(R.string.setting));

        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // recycler
        arrayList.clear();
        arrayList.add("personal Setting");
        arrayList.add("Notifications");
        adapter=new SettingAdapter(arrayList, this, new OnPress() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        recyclerView.setAdapter(adapter);



    }
}
