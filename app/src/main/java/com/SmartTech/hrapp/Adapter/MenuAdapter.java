package com.SmartTech.hrapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.SmartTech.hrapp.Holder.MenuHolder;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.OnPressInside;
import com.SmartTech.hrapp.Model.MenuModel;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.Views.MyViewHolder;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {

    private ArrayList<MenuModel> arrayList;
    private OnPress onPress;
    private OnPressInside onPressInside;
    private Context context;

    public MenuAdapter(ArrayList<MenuModel> arrayList,Context context, OnPress onPress,
                       OnPressInside onPressInside) {
        this.arrayList = arrayList;
        this.context = context;
        this.onPress = onPress;
        this.onPressInside = onPressInside;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return MyViewHolder.menuHolder(context,parent);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MenuHolder holder, final int position) {


        MenuModel model=arrayList.get(position);

        // selected

        if (model.isSelected()){
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.custom1));
            holder.icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorPrimaryLight));
        }else {
            holder.layout.setBackgroundColor(android.R.color.transparent);
            holder.icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.custom1));


        }

        // has child
        boolean hasChild=false;
        if (model.getListInside().size()>0){
            hasChild=true;
        }

        // title
        holder.textView.setText(model.getTitle());



        // icon
        holder.icon.setBackgroundResource(model.getIcon());


        // arrow
        if (hasChild){
            holder.arrow.setVisibility(View.VISIBLE);
            InsideMenuAdapter adapter=new InsideMenuAdapter(model.getListInside(), new OnPress() {
                @Override
                public void onClick(View view, int position2) {
                    // click inside
                    onPressInside.onClick(view,position,position2);
                }
            }, context);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
            holder.recyclerView.setAdapter(adapter);
        }else {
            holder.arrow.setVisibility(View.INVISIBLE);
        }



        // click

        final boolean finalHasChild = hasChild;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (finalHasChild){



                  holder.exp.toggle();
                  holder.exp.setListener(new ExpandableLayoutListener() {
                      @Override
                      public void onAnimationStart() {

                          if (holder.exp.isExpanded()){
                              holder.arrow.animate().rotation(0).start();
                          }else {
                              holder.arrow.animate().rotation(90).start();
                          }
                      }

                      @Override
                      public void onAnimationEnd() {

                      }

                      @Override
                      public void onPreOpen() {

                      }

                      @Override
                      public void onPreClose() {


                      }

                      @Override
                      public void onOpened() {
                          holder.arrow.animate().rotation(90).start();


                      }

                      @Override
                      public void onClosed() {


                      }
                  });

                }else {

                    onPress.onClick(view,position);
                }

            }
        });





    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
