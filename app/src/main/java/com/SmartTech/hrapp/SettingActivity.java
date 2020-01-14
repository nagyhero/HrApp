package com.SmartTech.hrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.SmartTech.hrapp.Adapter.SettingAdapter;
import com.SmartTech.hrapp.InterFaces.OnPress;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView title;
    private ImageView backView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // init
        toolbar=(Toolbar)findViewById(R.id.setting_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(ImageView) toolbar.findViewById(R.id.t_normal_back);

        //title
        title.setText(getResources().getString(R.string.setting));

        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




    }
}
