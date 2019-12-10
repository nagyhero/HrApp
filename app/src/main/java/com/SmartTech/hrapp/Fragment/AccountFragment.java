package com.SmartTech.hrapp.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SmartTech.hrapp.Custom.MyFragmentCustom;
import com.SmartTech.hrapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends MyFragmentCustom {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_account, container, false);

        return view;
    }

}
