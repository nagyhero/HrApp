package com.SmartTech.hrapp.Activites.Login;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import com.SmartTech.hrapp.Custom.MyFragment;
import com.SmartTech.hrapp.MyActivity;
import com.SmartTech.hrapp.R;

import net.skoumal.fragmentback.BackFragmentHelper;


public class LoginActivity extends MyActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        MyFragment.changeLoginFragment(this,new LoginFragment(),R.id.login_container,R.anim.fadin,R.anim.fadout);

    }



    @Override
    public void onBackPressed() {
        if(!BackFragmentHelper.fireOnBackPressedEvent(this)) {
            super.onBackPressed();
        }

    }
}
