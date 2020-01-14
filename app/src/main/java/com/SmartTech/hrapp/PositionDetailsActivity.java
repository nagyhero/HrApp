package com.SmartTech.hrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.SmartTech.hrapp.Adapter.ViewPagerAdapter;
import com.SmartTech.hrapp.Fragment.TasksFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PositionDetailsActivity extends AppCompatActivity {


    public static TabLayout tabLayout;
    private ViewPager viewPager;
    public static ArrayList<Fragment> fragments=new ArrayList<>();
    private ViewPagerAdapter adapter;

    private Toolbar toolbar;
    private ImageView backView;
    private TextView title;

    private String id,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_details);

        // get data
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");

        // init
        toolbar=(Toolbar)findViewById(R.id.positionDetails_toolbar);
        backView=(ImageView) toolbar.findViewById(R.id.t_normal_back);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        tabLayout=(TabLayout)findViewById(R.id.pos_tabLayout);
        viewPager=(ViewPager)findViewById(R.id.pos_viewpager);


        // set data
        title.setText(name);



        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // pager
        adapter=new ViewPagerAdapter(this,getSupportFragmentManager(),fragments);
        int limit = (adapter.getCount() > 1 ? adapter.getCount() - 1 : 1);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(limit);
        tabLayout.setupWithViewPager(viewPager);
        initTab();
    }

    private void initTab() {
        fragments.clear();
        fragments.add(new TasksFragment());
        fragments.add(new TasksFragment());

        adapter.notifyDataSetChanged();
    }
}
