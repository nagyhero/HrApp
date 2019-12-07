package com.SmartTech.hrapp.Custom;

import android.annotation.SuppressLint;


import android.content.Context;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;



public class MyFragment {


    @SuppressLint("ResourceType")
    public static void changeLoginFragment(Context context, Fragment fragment, int container, int animIn, int animOut) {
        final FragmentActivity activity = (FragmentActivity) context;
        FragmentTransaction ft = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(animIn,animOut);
        ft.replace(container, fragment, "fragment");
        ft.commit();

    }
}
