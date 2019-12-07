package com.supportingonline.hrapp.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supportingonline.hrapp.Custom.MyFragmentCustom;
import com.supportingonline.hrapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends MyFragmentCustom {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_tasks, container, false);



        return view;
    }

}
