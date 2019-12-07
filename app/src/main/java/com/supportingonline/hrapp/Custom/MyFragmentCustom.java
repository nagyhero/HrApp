package com.supportingonline.hrapp.Custom;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.supportingonline.hrapp.Dialogs.MyProgressDialog;
import com.supportingonline.hrapp.InterFaces.OnPressView;
import com.supportingonline.hrapp.R;

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
}