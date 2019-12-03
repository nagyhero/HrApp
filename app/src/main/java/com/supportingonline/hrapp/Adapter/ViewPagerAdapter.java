package com.supportingonline.hrapp.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

  private ArrayList<Fragment> fragmentList;

  private Context context;

    public ViewPagerAdapter(Context context, FragmentManager fm, ArrayList<Fragment> fragmentList ){
        super(fm);
        this.fragmentList=fragmentList;
        this.context=context;

    }

    @Override
    public Fragment getItem(int position) {

       return fragmentList.get(position);
    }



    @Override
    public int getCount() {
        return fragmentList.size();
    }



}
