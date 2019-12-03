package com.supportingonline.hrapp.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supportingonline.hrapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitAddFragment extends Fragment {


    public SubmitAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submit_add, container, false);
    }

}
