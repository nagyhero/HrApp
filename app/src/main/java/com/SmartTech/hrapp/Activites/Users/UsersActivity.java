package com.SmartTech.hrapp.Activites.Users;

import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
;
import com.android.volley.toolbox.StringRequest;
import com.SmartTech.hrapp.Adapter.UsersAdapter;
import com.SmartTech.hrapp.AddUserActivity;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.MySizes;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.Custom.SpaceRecycler_V;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.UsersModel;
import com.SmartTech.hrapp.MyActivity;
import com.SmartTech.hrapp.R;
import com.SmartTech.hrapp.UserActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UsersActivity extends MyActivity {


    private Toolbar toolbar;
    private TextView title;
    private ImageView backView;
    private ImageView imageAdd;

    private EditText search;
    private RecyclerView recyclerView;
    private ArrayList<UsersModel> arrayList=new ArrayList<>();
    private UsersAdapter adapter;

    private ShimmerFrameLayout shimmerContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_users);
        toolbar=(Toolbar)findViewById(R.id.users_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(ImageView) toolbar.findViewById(R.id.t_normal_back);
        imageAdd=(ImageView)toolbar.findViewById(R.id.t_normal_add);
        search= (EditText) findViewById(R.id.users_search);
        shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.users_shimmer);


        //title
        title.setText(getResources().getString(R.string.users));

        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // add users
        imageAdd.setVisibility(View.VISIBLE);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddUserActivity.class));
                overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);
            }
        });

        // recycler
        arrayList.clear();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new SpaceRecycler_V(MySizes.gethight(this)/50));
        adapter=new UsersAdapter(arrayList, this, new OnPress() {
            @Override
            public void onClick(View view, int position) {

                UsersModel user=arrayList.get(position);

                // click card
                startActivity(new Intent(UsersActivity.this, UserActivity.class)
                .putExtra("name",user.getName())
                .putExtra("position",user.getPositionName())
                .putExtra("image",user.getImage())
                .putExtra("id",user.getId()));
                overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);
            }
        }, new OnPress() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view, int position) {
                // click more
                popUp(view,position);


            }
        });

        recyclerView.setAdapter(adapter);



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loadUsers();




    }

    private void loadUsers() {

        // shimmer
        shimmerContainer.setVisibility(View.VISIBLE);
        shimmerContainer.startShimmerAnimation();

        StringRequest request=new MyRequest(getToken(),0,getUserUrl(),
                new OnSuccessRequest(getContext(),new SuccessCall() {
                    @Override
                    public void OnBack(JSONObject object) {
                        //Log.d("Tag",object.toString());



                            // shimmer
                            shimmerContainer.setVisibility(View.GONE);

                            if (object!=null) {

                                try {
                                    JSONArray successArray = object.getJSONArray("success");
                                    for (int i = 0; i < successArray.length(); i++) {
                                        JSONObject jsonObject = successArray.getJSONObject(i);

                                        String id = jsonObject.getString("id");
                                        String name = jsonObject.getString("name");
                                        String gender = jsonObject.getString("gender");

                                        String email = null;
                                        if (jsonObject.isNull("email")) {
                                            jsonObject.getString("email");
                                        }

                                        String image = null;
                                        if (!jsonObject.isNull("image")) {
                                            image = jsonObject.getString("image");
                                        }

                                        String phone = null;
                                        if (!jsonObject.isNull("phone")) {
                                            phone = jsonObject.getString("phone");
                                        }

                                        String hiringDate = null;
                                        if (!jsonObject.isNull("hiring_date")) {
                                            hiringDate = jsonObject.getString("hiring_date");
                                        }

                                        String salary = null;
                                        if (!jsonObject.isNull("salary")) {
                                            salary = jsonObject.getString("salary");
                                        }


                                        String positionName = jsonObject.getString("position");

                                        UsersModel model = new UsersModel();
                                        model.setId(id);
                                        model.setName(name);
                                        model.setImage(getPathImageUser()+image);
                                        model.setPositionName(positionName);
                                        model.setEmail(email);
                                        model.setGender(gender);
                                        model.setHiringDate(hiringDate);
                                        model.setSalary(salary);


                                        arrayList.add(model);
                                        adapter.notifyDataSetChanged();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                    }
                }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {
                // shimmer
                shimmerContainer.setVisibility(View.GONE);
            }
        }));
        Myvollysinglton.getInstance(getContext()).addtorequst(request);
    }

    // actions

    // pop up menu
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void popUp(View view, final int position){
        PopupMenu popupMenu=new PopupMenu(UsersActivity.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.user_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();

                UsersModel model=arrayList.get(position);

                switch (id){
                    case R.id.menu_u_edit:

                        startActivity(new Intent(getContext(), AddUserActivity.class)
                                .putExtra("is_edit",true)
                                .putExtra("id",model.getId())
                                .putExtra("name",model.getName())
                        );
                        overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);

                        break;
                    case R.id.menu_u_delete:

                        showDeleteDialogAlert(position);

                        break;

                }
                return true;
            }
        });

        MenuPopupHelper menuHelper = new MenuPopupHelper(getContext(), (MenuBuilder) popupMenu.getMenu(),view);
        menuHelper.setForceShowIcon(true);
        menuHelper.show();
    }

    private void showDeleteDialogAlert(final int position){
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.are_you_sure)+"  "+arrayList.get(position).getName()+" !")
               // .setContentText("Won't be able to recover this file!")
                .setCancelText(getResources().getString(R.string.no_cancel))
                .setConfirmText(getResources().getString(R.string.yes_delete))
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // go to delete server
                        sDialog.dismissWithAnimation();
                        deleteUserFromServer(position);

                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText(getResources().getString(R.string.cancelled))
                               // .setContentText("Your imaginary file is safe :)")
                                .setConfirmText(getString(R.string.ok))
                                .showCancelButton(false)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                    }
                })

                .show();
    }

    private void deleteUserFromServer(int position){
        getProgressToShow().show();
        getProgress().dismissWithAnimation();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}
