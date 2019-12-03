package com.supportingonline.hrapp.Activites.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.supportingonline.hrapp.Activites.Login.LoginActivity;
import com.supportingonline.hrapp.Activites.Users.UsersActivity;
import com.supportingonline.hrapp.Adapter.HeadCardAdapter;
import com.supportingonline.hrapp.Adapter.MenuAdapter;
import com.supportingonline.hrapp.AddUserActivity;
import com.supportingonline.hrapp.Api.MyRequest;
import com.supportingonline.hrapp.Api.OnErrorRequest;
import com.supportingonline.hrapp.Api.OnSuccessRequest;
import com.supportingonline.hrapp.Custom.MySharedPref;
import com.supportingonline.hrapp.Custom.MySizes;
import com.supportingonline.hrapp.Custom.Myvollysinglton;
import com.supportingonline.hrapp.Custom.SpaceRecycler_H;
import com.supportingonline.hrapp.Custom.SpaceRecycler_V;
import com.supportingonline.hrapp.Dialogs.MyProgressDialog;
import com.supportingonline.hrapp.InterFaces.ErrorCall;
import com.supportingonline.hrapp.InterFaces.OnPress;
import com.supportingonline.hrapp.InterFaces.OnPressInside;
import com.supportingonline.hrapp.InterFaces.OnPressView;
import com.supportingonline.hrapp.InterFaces.SuccessCall;
import com.supportingonline.hrapp.Model.HeadCardModel;
import com.supportingonline.hrapp.Model.MenuModel;
import com.supportingonline.hrapp.MyActivity;
import com.supportingonline.hrapp.NotificationsActivity;
import com.supportingonline.hrapp.R;
import com.supportingonline.hrapp.SettingActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends MyActivity {

    private DrawerLayout drawer;

    private Toolbar toolbar;
    private ImageView menu_icon,noti_icon,more_icon;

    private CircleImageView imageView;
    private TextView textName,textEmail;

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private ArrayList<MenuModel> menuModels=new ArrayList<>();


    private HeadCardAdapter headCardAdapter;
    private RecyclerView recyclerHeadCard;
    private ArrayList<HeadCardModel> headCardList=new ArrayList<>();


    private PieChart piechart;

    private HomeViewModel vm;

    private MyProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // init
        toolbar=(Toolbar)findViewById(R.id.admin_toolbar);
        menu_icon=(ImageView)toolbar.findViewById(R.id.tb_admin_image);
        noti_icon=(ImageView)toolbar.findViewById(R.id.tb_admin_noti);
        more_icon=(ImageView)toolbar.findViewById(R.id.tb_admin_more);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_menu_admin);
        drawer=(DrawerLayout)findViewById(R.id.admin_drawer);
        piechart=(PieChart) findViewById(R.id.home_pie_chart) ;
        imageView=(CircleImageView)findViewById(R.id.home_image);
        textName=(TextView)findViewById(R.id.home_name);
        textEmail=(TextView)findViewById(R.id.home_email);


        // progress dialog

        dialog=new MyProgressDialog(new OnPressView() {
            @Override
            public void onclick(View view) {
                Myvollysinglton.cancel("req");
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);






        // drawer
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        // menu
        initMenuForHr();

        // view model
        vm= ViewModelProviders.of(this).get(HomeViewModel.class);
        MutableLiveData<ArrayList<HeadCardModel>> liveData=vm.getInitHeadCards();



        // recycler head
        recyclerHeadCard=(RecyclerView)findViewById(R.id.recycler_admin_head);
        recyclerHeadCard.setLayoutManager(new GridLayoutManager(this,2));
        recyclerHeadCard.addItemDecoration(new SpaceRecycler_H(MySizes.getwedith(this)/60));
        recyclerHeadCard.addItemDecoration(new SpaceRecycler_V(MySizes.gethight(this)/40));
        headCardAdapter=new HeadCardAdapter(vm.headCardList, this, new OnPress() {
            @Override
            public void onClick(View view, int position) {

                startActivity(new Intent(HomeActivity.this, UsersActivity.class));
                overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

            }
        });
        recyclerHeadCard.setAdapter(headCardAdapter);
        liveData.observe(this, new Observer<ArrayList<HeadCardModel>>() {
            @Override
            public void onChanged(ArrayList<HeadCardModel> headCardModels) {
                headCardAdapter.notifyDataSetChanged();
            }
        });


        // notification
        noti_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NotificationsActivity.class));
            }
        });


        // more
        more_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            popUp(v);
            }
        });



        // info
        Glide.with(getContext()).load(getImage()).into(imageView);
        textName.setText(getName());
        textEmail.setText(getEmail());


        // pie chart
        loadPieChart();



        // init head
        initHead();



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

           vm.addNewCard(model);
        }

    }

    private void initMenuForHr() {
        menuModels.clear();
        String[] menuTtiles=getResources().getStringArray(R.array.menu_titles);
        int[] menuIcons={R.drawable.ic_dashboard,R.drawable.ic_user,R.drawable.ic_logout};
        for (int i=0;i<menuTtiles.length;i++){
            MenuModel model=new MenuModel();
            model.setTitle(menuTtiles[i]);
            model.setIcon(menuIcons[i]);
            if (i==0){
                model.setSelected(true);
            }
            if (i==1){
                ArrayList<MenuModel> mlist=new ArrayList<>();
                String[] menuTtiles2=getResources().getStringArray(R.array.menu_titles2);
                int[] menuIcons2={android.R.drawable.btn_radio,android.R.drawable.btn_radio};

                // second menu items for users
                for (int mi=0;mi<menuTtiles2.length;mi++) {
                    MenuModel themodel = new MenuModel();
                    themodel.setTitle(menuTtiles2[mi]);
                    themodel.setIcon(menuIcons2[mi]);
                    mlist.add(themodel);
                }
                model.setListInside(mlist);
            }
            menuModels.add(model);
        }

        menuAdapter=new MenuAdapter(menuModels, this, new OnPress() {
            @Override
            public void onClick(View view, int position) {

                // click menu

                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;

                    case 2:

                        alertLogoutDilaog();

                        break;
                }
            }
        }, new OnPressInside() {
            @Override
            public void onClick(View view, int position, int position2) {

                // click inside menu
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        if (position2==0){
                            startActivity(new Intent(getContext(),UsersActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }else if (position2==1){
                            startActivity(new Intent(getContext(), AddUserActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }

                        break;

                    case 2:

                        alertLogoutDilaog();

                        break;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(menuAdapter);
    }



    // actions

    // pop up menu for admin
    private void popUp(View view){
        PopupMenu popupMenu=new PopupMenu(HomeActivity.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.admin_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();

                switch (id){
                    case R.id.m_a_myprofile:

                        break;
                    case R.id.m_a_setting:
                        startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }


    private void alertLogoutDilaog(){
        final AlertDialog.Builder logoutdialog = new AlertDialog.Builder(getContext());
        logoutdialog.setMessage(getResources().getString(R.string.aresurelogout));
        logoutdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logOut();
            }
        });
        logoutdialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        logoutdialog.show();
    }
    private void logOut(){

        // show progress dialog
        dialog.show(getSupportFragmentManager(),"");

        StringRequest request=new MyRequest(getToken(),1,getLogoutUrl(),new OnSuccessRequest(new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {

                Log.d("logout",object.toString());
                try {
                if (object.has("success")){
                    if (object.getString("success").equals("done")){
                        MySharedPref.removerAfterLogout(getContext());
                        finishAffinity();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }

                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();

            }
        }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {

                dialog.dismiss();

            }
        }));
        Myvollysinglton.getInstance(getContext()).addtorequst(request);

    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.LEFT)){
            drawer.closeDrawer(Gravity.LEFT);
        }else {
            super.onBackPressed();
        }
    }
}
