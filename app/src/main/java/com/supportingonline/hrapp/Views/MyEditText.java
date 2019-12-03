package com.supportingonline.hrapp.Views;

import android.widget.EditText;

public class MyEditText {

    public static void enableDisableEditText(boolean isEnabled, EditText editText) {
        editText.setFocusable(isEnabled);
        editText.setFocusableInTouchMode(isEnabled) ;
        editText.setClickable(isEnabled);
        editText.setLongClickable(isEnabled);
        editText.setCursorVisible(isEnabled) ;
    }

    public static boolean isEmail(String data){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean isemail=false;

        if (data.matches(emailPattern)){
            isemail = true;
        }

        return  isemail;
    }
}
