package com.SmartTech.hrapp.Activites.Home;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.SmartTech.hrapp.AddDeviceActivity;
import com.SmartTech.hrapp.DevicesActivity;
import com.SmartTech.hrapp.Fragment.AccountFragment;
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
import com.SmartTech.hrapp.Dialogs.MyProgressDialog;
import com.SmartTech.hrapp.Fragment.AdminHomeFragment;
import com.SmartTech.hrapp.Fragment.TasksFragment;
import com.SmartTech.hrapp.Fragment.UserHomeFragment;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.OnPressInside;
import com.SmartTech.hrapp.InterFaces.OnPressView;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.MenuModel;
import com.SmartTech.hrapp.MyActivity;
import com.SmartTech.hrapp.NotificationsActivity;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.SettingActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

                    case 2:


                        break;


                    case 3:


                        break;

                    case 4:

                        alertLogoutDilaog();

                        break;
                }

            }
        }, new OnPressInside() {
            @Override
            public void onClick(View view, int position, int position2) {

                // click inside menu
                switch (position) {


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
