package com.SmartTech.hrapp.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SmartTech.hrapp.Custom.MyFragmentCustom;
import com.SmartTech.hrapp.DevicesActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.SmartTech.hrapp.Activites.Users.UsersActivity;
import com.SmartTech.hrapp.Adapter.HeadCardAdapter;
import com.SmartTech.hrapp.Custom.MySizes;
import com.SmartTech.hrapp.Custom.SpaceRecycler_H;
import com.SmartTech.hrapp.Custom.SpaceRecycler_V;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.Model.HeadCardModel;
import com.SmartTech.hrapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminHomeFragment extends MyFragmentCustom {


    private View view;
    private Context mContext;


    private HeadCardAdapter headCardAdapter;
    private RecyclerView recyclerHeadCard;
    private ArrayList<HeadCardModel> headCardList=new ArrayList<>();


    private PieChart piechart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_admin_home, container, false);

        // context
        mContext=view.getContext();

        // init
        piechart=(PieChart) view.findViewById(R.id.home_pie_chart) ;
        recyclerHeadCard=(RecyclerView)view.findViewById(R.id.recycler_admin_head);


        // recycler head

        recyclerHeadCard.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerHeadCard.addItemDecoration(new SpaceRecycler_H(MySizes.getwedith(mContext)/60));
        recyclerHeadCard.addItemDecoration(new SpaceRecycler_V(MySizes.gethight(mContext)/40));
        headCardAdapter=new HeadCardAdapter(headCardList, mContext, new OnPress() {
            @Override
            public void onClick(View view, int position) {

                switch (position){
                    case 0:
                        startActivity(new Intent(mContext, UsersActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, DevicesActivity.class));
                        break;


                }
                ((Activity)mContext).overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

            }
        });
        recyclerHeadCard.setAdapter(headCardAdapter);

        // pie chart
        loadPieChart();



        // init head
        initHead();

   return view;
    }


    private void loadPieChart() {
        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.WHITE);

        piechart.setTransparentCircleColor(Color.WHITE);
        piechart.setTransparentCircleAlpha(110);

        piechart.setHoleRadius(58f);
        piechart.setTransparentCircleRadius(61f);

        piechart.setDrawCenterText(true);

        piechart.setRotationAngle(0);
        // enable rotation of the chart by touch
        piechart.setRotationEnabled(true);
        piechart.setHighlightPerTapEnabled(true);

        List<String> labes=new ArrayList<>() ;
        labes.add("January");
        labes.add("February");
        labes.add("March");
        labes.add("April");
        labes.add("May");
        labes.add("June");


        List<PieEntry> entries=new ArrayList<>() ;
        entries.add(new PieEntry(50, labes.get(0)));
        entries.add(new PieEntry(10, labes.get(1)));
        entries.add(new PieEntry(20, labes.get(2)));
        entries.add(new PieEntry(20, labes.get(3)));




        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColor(R.color.custom1);

        PieData pieData = new PieData(pieDataSet);


        pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        piechart.setData(pieData);
        piechart.setEntryLabelColor(R.color.custom1);

        piechart.animateY(1400, Easing.EaseInOutQuad);

    }


    private void initHead() {

        for (int i= 0;i<getCardTitles().size();i++){
            HeadCardModel model=new HeadCardModel();
            model.setTitle(getCardTitles().get(i));
            model.setIcon(getCardIcons().get(i));
            headCardList.add(model);
            headCardAdapter.notifyDataSetChanged();
        }

    }

}
