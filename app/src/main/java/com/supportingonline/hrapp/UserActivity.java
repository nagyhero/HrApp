package com.supportingonline.hrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.supportingonline.hrapp.Adapter.ViewPagerAdapter;
import com.supportingonline.hrapp.Fragment.FirstAddFragment;
import com.supportingonline.hrapp.Fragment.SecondAddFragment;
import com.supportingonline.hrapp.Fragment.SubmitAddFragment;

import java.util.ArrayList;

public class UserActivity extends MyActivity {

    private Toolbar toolbar;
    private TextView title,textName,textPosition,textEmail,textPhone;
    private View backView;
    private CardView container;
    private ImageView imageView;

    public static TabLayout tabLayout;
    private ViewPager viewPager;

    public static ArrayList<Fragment> fragments=new ArrayList<>();
    private ViewPagerAdapter adapter;


    private String id,name,position,image;
    private void getData(){
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        position=getIntent().getStringExtra("position");
        image=getIntent().getStringExtra("image");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        toolbar=(Toolbar)findViewById(R.id.user_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_user_title);
        backView=(View) toolbar.findViewById(R.id.t_user_back);
        container=(CardView)findViewById(R.id.user_container);
        textName=(TextView)container.findViewById(R.id.cont_name);
        textPosition=(TextView)container.findViewById(R.id.cont_position);
        textEmail=(TextView)container.findViewById(R.id.cont_email);
        textPhone=(TextView)container.findViewById(R.id.cont_phone);
        imageView=(ImageView)container.findViewById(R.id.cont_image);
        tabLayout=(TabLayout)findViewById(R.id.user_tablayout);
        viewPager=(ViewPager)findViewById(R.id.user_viewpager);


        // get data from get intent
        getData();

        // set data
        title.setText(name);
        textName.setText(name);
        textPosition.setText(position);
        Glide.with(this).load(getDomain()+"imgs/users/"+image)
                .into(imageView);



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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}
