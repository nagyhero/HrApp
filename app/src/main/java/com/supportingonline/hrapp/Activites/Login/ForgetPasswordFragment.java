package com.supportingonline.hrapp.Activites.Login;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.supportingonline.hrapp.Activites.Login.LoginFragment;
import com.supportingonline.hrapp.Custom.KeyBoardHiding;
import com.supportingonline.hrapp.Custom.MyFragment;
import com.supportingonline.hrapp.R;
import com.supportingonline.hrapp.Views.MyEditText;

import net.skoumal.fragmentback.BackFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment implements BackFragment {


    private View view;

    private Button btn_request,btn_cancel;
    private EditText eemail;
    private TextView alarmEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_forget_password, container, false);
        KeyBoardHiding.hide(view,view.getContext());

        btn_request=(Button)view.findViewById(R.id.forget_request);
        btn_cancel=(Button)view.findViewById(R.id.forget_cancel);
        eemail=(EditText)view.findViewById(R.id.forgot_email);
        alarmEmail=(TextView)view.findViewById(R.id.forget_alarm_email);

        // set visible of alarm
        alarmEmail.setVisibility(View.INVISIBLE);

        // cancel and back
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragment.changeLoginFragment(view.getContext(),new LoginFragment(),R.id.login_container,
                        R.anim.left_in,R.anim.left_out);

            }
        });

        // edit email listener
        eemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()>0) {

                    if (!MyEditText.isEmail(s.toString().trim())) {
                        alarmEmail.setText(view.getContext().getResources().getString(R.string.valiedemail));
                        alarmEmail.setVisibility(View.VISIBLE);
                    } else {
                        alarmEmail.setVisibility(View.INVISIBLE);
                    }
                }else {
                    alarmEmail.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public boolean onBackPressed() {
        MyFragment.changeLoginFragment(view.getContext(),new LoginFragment(),R.id.login_container,
                R.anim.left_in,R.anim.left_out);
        return true;
    }

    @Override
    public int getBackPriority() {
        return 0;
    }
}
