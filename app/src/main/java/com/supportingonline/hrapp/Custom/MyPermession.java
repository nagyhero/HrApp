package com.supportingonline.hrapp.Custom;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MyPermession extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS =0; ;

    public static void checkPermission(Context context){

        Activity activity=(Activity)context;

        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                Toast.makeText(context, "i click deny before", Toast.LENGTH_SHORT).show();
            } else {
                // No explanation needed; request the permission
                Toast.makeText(context, "showing system dialog", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            Toast.makeText(activity, "code", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                Toast.makeText(this, "i click allow", Toast.LENGTH_SHORT).show();
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(this, "i click deny", Toast.LENGTH_SHORT).show();
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
