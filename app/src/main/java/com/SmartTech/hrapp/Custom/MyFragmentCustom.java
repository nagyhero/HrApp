package com.SmartTech.hrapp.Custom;

import androidx.fragment.app.Fragment;

import com.SmartTech.hrapp.R;

import java.util.ArrayList;

public class MyFragmentCustom extends Fragment {

    public String domain(){

        return getActivity().getResources().getString(R.string.domain);
    }



    public ArrayList<String> getCardTitles(){
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add(getResources().getString(R.string.users));
        arrayList.add(getResources().getString(R.string.devices));

        return arrayList;
    }
    public ArrayList<Integer> getCardIcons(){
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(R.drawable.ic_user);
        arrayList.add(R.drawable.ic_printer);

        return arrayList;
    }
}
