package com.SmartTech.hrapp.Custom;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.SmartTech.hrapp.Dialogs.MyProgressDialog;
import com.SmartTech.hrapp.InterFaces.OnPressView;
import com.SmartTech.hrapp.R;

import java.util.ArrayList;

public class MyFragmentCustom extends Fragment {

    private MyProgressDialog dialog;

    public MyFragmentCustom() {

        dialog=new MyProgressDialog(new OnPressView() {
            @Override
            public void onclick(View view) {
                Myvollysinglton.cancel("req");
                cancelDialog();
            }
        });
        dialog.setCancelable(false);

    }

    public String domain(){

        return getActivity().getResources().getString(R.string.domain);
    }

    public void  showDialog(){
        assert getFragmentManager() != null;
        dialog.show(getFragmentManager(),"");
    }
    public void cancelDialog(){
        dialog.dismiss();
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
