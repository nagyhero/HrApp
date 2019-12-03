package com.supportingonline.hrapp.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.supportingonline.hrapp.R;

import static android.graphics.PorterDuff.*;


public class TabLayoutViews {




    // get view title
    public static View getTabView(Context context ,String tabTitle,int icon){
      View v = LayoutInflater.from(context).inflate(R.layout.tab_view, null);

     // title
      TextView title = (TextView) v.findViewById(R.id.tab_title);
      title.setText(tabTitle);


      // image
      ImageView img = (ImageView) v.findViewById(R.id.tab_icon);
      img.setImageResource(icon);
      img.setColorFilter(context.getResources().getColor(android.R.color.darker_gray),Mode.SRC_IN);


        return v;
    }

    public static void selectTab(Context context,View view){
        TextView t=view.findViewById(R.id.tab_title);


        ImageView im=view.findViewById(R.id.tab_icon);
        im.setColorFilter(context.getResources().getColor(R.color.custom1), Mode.SRC_IN);

    }


    public static void unselectedTab(Context context,View view){

        TextView t=view.findViewById(R.id.tab_title);


        ImageView im=view.findViewById(R.id.tab_icon);
        im.setColorFilter(context.getResources().getColor(android.R.color.darker_gray), Mode.SRC_IN);
    }

    public static void addToCountNotification(Context context,View view){
        TextView t=view.findViewById(0);
        int s= Integer.parseInt(t.getText().toString());
        int newValue=s+1;
        t.setText(String.valueOf(newValue));

        RelativeLayout layout=(RelativeLayout)view.findViewById(0);
        layout.setVisibility(View.VISIBLE);
        MyAnimation.setAnimation(context,layout,R.anim.zominzomout);
        MyAnimation.setAnimation(context,t,R.anim.zominzomout);

    }

    public static void removeCount(Context context,View view){
        TextView t=view.findViewById(0);
        t.setText("0");
        RelativeLayout layout=(RelativeLayout)view.findViewById(0);
        layout.setVisibility(View.INVISIBLE);

    }
}
