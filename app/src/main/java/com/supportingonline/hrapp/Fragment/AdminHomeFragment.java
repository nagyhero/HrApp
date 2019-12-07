package com.supportingonline.hrapp.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.supportingonline.hrapp.Activites.Home.HomeActivity;
import com.supportingonline.hrapp.Activites.Users.UsersActivity;
import com.supportingonline.hrapp.Adapter.HeadCardAdapter;
import com.supportingonline.hrapp.Custom.MySizes;
import com.supportingonline.hrapp.Custom.SpaceRecycler_H;
import com.supportingonline.hrapp.Custom.SpaceRecycler_V;
import com.supportingonline.hrapp.InterFaces.OnPress;
import com.supportingonline.hrapp.Model.HeadCardModel;
import com.supportingonline.hrapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminHomeFragment extends Fragment {


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

                startActivity(new Intent(mContext, UsersActivity.class));
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
        String[] titles={"ahmed","nagy","mohamed"};
        int icons[]={R.drawable.ic_user,R.drawable.ic_user,R.drawable.ic_user};
        for (int i= 0;i<titles.length;i++){
            HeadCardModel model=new HeadCardModel();
            headCardList.add(model);
            headCardAdapter.notifyDataSetChanged();
        }

    }

}
