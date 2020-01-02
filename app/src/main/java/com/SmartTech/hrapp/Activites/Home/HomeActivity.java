package com.SmartTech.hrapp.Activites.Home;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.SmartTech.hrapp.AddBranchActivity;
import com.SmartTech.hrapp.AddDepartmentActivity;
import com.SmartTech.hrapp.AddDeviceActivity;
import com.SmartTech.hrapp.AddPositionActivity;
import com.SmartTech.hrapp.AddVacationActivity;
import com.SmartTech.hrapp.BranchsActivity;
import com.SmartTech.hrapp.DepartmentsActivity;
import com.SmartTech.hrapp.DevicesActivity;
import com.SmartTech.hrapp.Fragment.AccountFragment;
import com.SmartTech.hrapp.PositionsActivity;
import com.SmartTech.hrapp.VacationsActivity;
import com.SmartTech.hrapp.VacationsRequestActivity;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.SmartTech.hrapp.Activites.Login.LoginActivity;
import com.SmartTech.hrapp.Activites.Users.UsersActivity;
import com.SmartTech.hrapp.Adapter.MenuAdapter;
import com.SmartTech.hrapp.AddUserActivity;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.MyFragment;
import com.SmartTech.hrapp.Custom.MySharedPref;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.Fragment.AdminHomeFragment;
import com.SmartTech.hrapp.Fragment.TasksFragment;
import com.SmartTech.hrapp.Fragment.UserHomeFragment;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.OnPressInside;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.MenuModel;
import com.SmartTech.hrapp.MyActivity;
import com.SmartTech.hrapp.NotificationsActivity;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.SettingActivity;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends MyActivity {

    private DrawerLayout drawer;

    private Toolbar toolbar;
    private ImageView menu_icon,noti_icon,more_icon;

    private CoordinatorLayout coordinatorLayout;
    private CircleImageView imageView;
    private TextView textName,textEmail;

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private ArrayList<MenuModel> menuModels=new ArrayList<>();
    
    private boolean isNoti;


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
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.home_coord);

        imageView=(CircleImageView)findViewById(R.id.home_image);
        textName=(TextView)findViewById(R.id.home_name);
        textEmail=(TextView)findViewById(R.id.home_email);







        // drawer
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        // menu
        if (getRole().equals("hr")) {
            initMenuForHr();
            MyFragment.changeLoginFragment(this,new AdminHomeFragment(),R.id.myhome_container,0,0);

        }else if (getRole().equals("user")){
            initMenuForUser();
            MyFragment.changeLoginFragment(this,new UserHomeFragment(),R.id.myhome_container,0,0);

        }







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
        Glide.with(getContext()).load(getImage()).error(R.drawable.profile).into(imageView);
        textName.setText(getName());
        textEmail.setText(getEmail());


        // is noti
        isNoti=getIntent().getBooleanExtra("is_noti",false);
        if (isNoti){
            showNotiInHome();
        }



    }

    private void showNotiInHome() {

        String message=getIntent().getStringExtra("message");
        assert message != null;
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message.toUpperCase(), Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(android.R.color.white));

        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.custom3));

        snackbar.show();
    }


    // hr menu
    private void initMenuForHr() {
        menuModels.clear();
        for (int i=0;i<getAdminTitles().size();i++){
            MenuModel model=new MenuModel();
            model.setTitle(getAdminTitles().get(i));
            model.setIcon(getAdminIcons().get(i));
            if (i==0){
                model.setSelected(true);
            }
            if (i==2){
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
            else if (i==3){
                ArrayList<MenuModel> mlist=new ArrayList<>();
                String[] menuTtiles2=getResources().getStringArray(R.array.menu_titles_d);
                int[] menuIcons2={android.R.drawable.btn_radio,android.R.drawable.btn_radio};

                //  menu items for devices
                for (int mi=0;mi<menuTtiles2.length;mi++) {
                    MenuModel themodel = new MenuModel();
                    themodel.setTitle(menuTtiles2[mi]);
                    themodel.setIcon(menuIcons2[mi]);
                    mlist.add(themodel);
                }
                model.setListInside(mlist);
            }
            else if (i==4){
                ArrayList<MenuModel> mlist=new ArrayList<>();
                String[] menuTtiles3=getResources().getStringArray(R.array.menu_titles_vac);
                int[] menuIcons3={android.R.drawable.btn_radio,android.R.drawable.btn_radio,android.R.drawable.btn_radio};

                //  menu items for vacations
                for (int mi=0;mi<menuTtiles3.length;mi++) {
                    MenuModel themodel = new MenuModel();
                    themodel.setTitle(menuTtiles3[mi]);
                    themodel.setIcon(menuIcons3[mi]);
                    mlist.add(themodel);
                }
                model.setListInside(mlist);
            }
            else if (i==5){
                //  menu items for position

                ArrayList<MenuModel> mlist=new ArrayList<>();
                String[] menuTtiles3=getResources().getStringArray(R.array.menu_titles_position);
                int[] menuIcons3={android.R.drawable.btn_radio,android.R.drawable.btn_radio};


                for (int mi=0;mi<menuTtiles3.length;mi++) {
                    MenuModel themodel = new MenuModel();
                    themodel.setTitle(menuTtiles3[mi]);
                    themodel.setIcon(menuIcons3[mi]);
                    mlist.add(themodel);
                }
                model.setListInside(mlist);
            } else if (i==6){
                //  menu items for departments

                ArrayList<MenuModel> mlist=new ArrayList<>();
                String[] menuTtiles3=getResources().getStringArray(R.array.menu_titles_departments);
                int[] menuIcons3={android.R.drawable.btn_radio,android.R.drawable.btn_radio};


                for (int mi=0;mi<menuTtiles3.length;mi++) {
                    MenuModel themodel = new MenuModel();
                    themodel.setTitle(menuTtiles3[mi]);
                    themodel.setIcon(menuIcons3[mi]);
                    mlist.add(themodel);
                }
                model.setListInside(mlist);
            }

            else if (i==7){
                //  menu items for branches

                ArrayList<MenuModel> mlist=new ArrayList<>();
                String[] menuTtiles3=getResources().getStringArray(R.array.menu_titles_branches);
                int[] menuIcons3={android.R.drawable.btn_radio,android.R.drawable.btn_radio};


                for (int mi=0;mi<menuTtiles3.length;mi++) {
                    MenuModel themodel = new MenuModel();
                    themodel.setTitle(menuTtiles3[mi]);
                    themodel.setIcon(menuIcons3[mi]);
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

                if (!menuModels.get(position).isSelected()){
                    drawer.closeDrawer(Gravity.LEFT);
                }

                switch (position) {
                    case 0:

                        // dash bord
                        if (!menuModels.get(position).isSelected()){
                            MyFragment.changeLoginFragment(getContext(),new AdminHomeFragment(),R.id.myhome_container,
                                    R.anim.fadin,R.anim.fadout);
                            mangeSelectMenu(position);

                        }
                        break;
                    case 1:

                        // admin account

                        MyFragment.changeLoginFragment(getContext(),new AccountFragment()
                        ,R.id.myhome_container,R.anim.fadin,R.anim.fadout);
                        mangeSelectMenu(position);

                        break;


                        // 2->users 3->devices 4->vacations 5->positions 6->departments
                    // 7->branches
                    case 8:

                        alertLogoutDilaog();

                        break;
                }

            }
        }, new OnPressInside() {
            @Override
            public void onClick(View view, int position, int position2) {

                // click inside menu
                switch (position) {


                    // users
                    case 2:
                        if (position2==0){

                            // users
                            startActivity(new Intent(getContext(),UsersActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }else if (position2==1){
                            // add user
                            startActivity(new Intent(getContext(), AddUserActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }

                        break;

                        // devices
                    case 3:

                        if (position2==0){
                            // devices
                            startActivity(new Intent(getContext(), DevicesActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }else if (position2==1){

                            // add device
                            startActivity(new Intent(getContext(), AddDeviceActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }
                        break;

                    case 4:
                        // vacations

                        if (position2==0){
                            // vacations
                            startActivity(new Intent(getContext(), VacationsActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }else if (position2==1){

                            // add vacation
                            startActivity(new Intent(getContext(), AddVacationActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }else if (position2==2){

                            // vacations request
                            startActivity(new Intent(getContext(), VacationsRequestActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }
                        break;

                    case 5:
                        // positions

                        if (position2==0){
                            // positions
                            startActivity(new Intent(getContext(), PositionsActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }else if (position2==1){

                            // positions
                            startActivity(new Intent(getContext(), AddPositionActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }
                        break;

                    case 6:
                        // departments

                        if (position2==0){
                            // departments
                            startActivity(new Intent(getContext(), DepartmentsActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }else if (position2==1){

                            // add departments
                            startActivity(new Intent(getContext(), AddDepartmentActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }
                        break;

                    case 7:
                        // branches

                        if (position2==0){
                            // branches
                            startActivity(new Intent(getContext(), BranchsActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }else if (position2==1){

                            // add branch
                            startActivity(new Intent(getContext(), AddBranchActivity.class));
                            overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        }
                        break;

                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(menuAdapter);
    }

    // user menu
    private void initMenuForUser() {
        menuModels.clear();
        String[] menuTtiles=getResources().getStringArray(R.array.menu_titlesforuser);
        int[] menuIcons={R.drawable.ic_dashboard,R.drawable.ic_edit,R.drawable.ic_logout};
        for (int i=0;i<menuTtiles.length;i++){
            MenuModel model=new MenuModel();
            model.setTitle(menuTtiles[i]);
            model.setIcon(menuIcons[i]);
            if (i==0){
                model.setSelected(true);
            }
            menuModels.add(model);
        }

        menuAdapter=new MenuAdapter(menuModels, this, new OnPress() {
            @Override
            public void onClick(View view, int position) {

                // click menu

                if (!menuModels.get(position).isSelected()){
                    drawer.closeDrawer(Gravity.LEFT);

                }
                switch (position) {
                    case 0:
                        if (!menuModels.get(position).isSelected()){
                            MyFragment.changeLoginFragment(getContext(),new UserHomeFragment(),R.id.myhome_container,
                                    R.anim.fadin,R.anim.fadout);
                            mangeSelectMenu(position);
                        }

                        break;
                    case 1:

                        if (!menuModels.get(position).isSelected()){
                            MyFragment.changeLoginFragment(getContext(),new TasksFragment(),R.id.myhome_container,
                                    R.anim.fadin,R.anim.fadout);
                            mangeSelectMenu(position);
                        }

                        break;

                    case 2:

                        alertLogoutDilaog();

                        break;
                }
               ;
            }
        }, null);
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

        new SweetAlertDialog(getContext(),SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getResources().getString(R.string.aresurelogout))
                .setConfirmText(getResources().getString(R.string.yes))
                .setCancelText(getResources().getString(R.string.no))
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        logOut();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
    }
    private void logOut(){

        // show progress dialog
        getProgressToShow().show();

        StringRequest request=new MyRequest(getToken(),1,getLogoutUrl(),new OnSuccessRequest(getContext(),new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {

                getProgress().dismissWithAnimation();

                        MySharedPref.removerAfterLogout(getContext());
                        finishAffinity();
                        startActivity(new Intent(getContext(), LoginActivity.class));






            }
        }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {

                getProgress().dismissWithAnimation();

            }
        }));
        Myvollysinglton.getInstance(getContext()).addtorequst(request);

    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.LEFT)){
            drawer.closeDrawer(Gravity.LEFT);
        }
        // if not in home
        else if (!menuModels.get(0).isSelected()){


            if (getRole().equals("user")){
                MyFragment.changeLoginFragment(getContext(),new UserHomeFragment(),
                        R.id.myhome_container,R.anim.fadin,R.anim.fadout);
            }else {
                MyFragment.changeLoginFragment(getContext(),new AdminHomeFragment(),
                        R.id.myhome_container,R.anim.fadin,R.anim.fadout);
            }
            mangeSelectMenu(0);
        }
        // end

        else {
            super.onBackPressed();
        }
    }

    private void mangeSelectMenu(int pos){
        for (int i =0; i<menuModels.size();i++){
            menuModels.get(i).setSelected(false);
        }
        menuModels.get(pos).setSelected(true);
        menuAdapter.notifyDataSetChanged();
    }
}
