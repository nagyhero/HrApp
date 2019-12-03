package com.supportingonline.hrapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.supportingonline.hrapp.Adapter.ViewPagerAdapter;
import com.supportingonline.hrapp.Custom.Myvollysinglton;
import com.supportingonline.hrapp.Dialogs.MyProgressDialog;
import com.supportingonline.hrapp.Fragment.FirstAddFragment;
import com.supportingonline.hrapp.Fragment.SecondAddFragment;
import com.supportingonline.hrapp.Fragment.SubmitAddFragment;
import com.supportingonline.hrapp.InterFaces.OnPressView;
import com.supportingonline.hrapp.Views.TabLayoutViews;

import java.util.ArrayList;

public class AddUserActivity extends MyActivity {

    private Toolbar toolbar;
    private TextView title;
    private View backView;

    public static TabLayout tabLayout;
    private ViewPager viewPager;

    public static ArrayList<Fragment> fragments=new ArrayList<>();
    private ViewPagerAdapter adapter;

    private MyProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        tabLayout=(TabLayout)findViewById(R.id.add_tabLayout);
        viewPager=(ViewPager)findViewById(R.id.add_viewpager);
        toolbar=(Toolbar)findViewById(R.id.add_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(View) toolbar.findViewById(R.id.t_normal_back);


        //title
        title.setText(getResources().getString(R.string.createnewuser));

        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // progress dialog

        dialog=new MyProgressDialog(new OnPressView() {
            @Override
            public void onclick(View view) {
                Myvollysinglton.cancel("req");
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);


        // show progress dialog
        dialog.show(getSupportFragmentManager(),"");

        // pager

        adapter=new ViewPagerAdapter(this,getSupportFragmentManager(),fragments);
        int limit = (adapter.getCount() > 1 ? adapter.getCount() - 1 : 1);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(limit);
        tabLayout.setupWithViewPager(viewPager);
        initTab();


    }


    private void initTab(){
        fragments.clear();
        fragments.add(new FirstAddFragment());
        fragments.add(new SecondAddFragment());
        fragments.add(new SubmitAddFragment());
        adapter.notifyDataSetChanged();

        tabLayout.getTabAt(0).setCustomView(TabLayoutViews.getTabView(this,getResources().getString(R.string.profile),R.drawable.ic_layers));
        tabLayout.getTabAt(1).setCustomView(TabLayoutViews.getTabView(this,getResources().getString(R.string.jobinfo),R.drawable.ic_layers));
        tabLayout.getTabAt(2).setCustomView(TabLayoutViews.getTabView(this,getResources().getString(R.string.password),R.drawable.ic_layers));

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                TabLayoutViews.selectTab(getContext(),tab.getCustomView());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TabLayoutViews.unselectedTab(getContext(),tab.getCustomView());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
       TabLayoutViews.selectTab(getContext(),tabLayout.getTabAt(0).getCustomView());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}
