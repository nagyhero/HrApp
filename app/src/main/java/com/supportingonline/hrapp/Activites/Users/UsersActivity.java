package com.supportingonline.hrapp.Activites.Users;

import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.supportingonline.hrapp.Adapter.UsersAdapter;
import com.supportingonline.hrapp.AddUserActivity;
import com.supportingonline.hrapp.Api.MyRequest;
import com.supportingonline.hrapp.Api.OnErrorRequest;
import com.supportingonline.hrapp.Api.OnSuccessRequest;
import com.supportingonline.hrapp.Custom.MySizes;
import com.supportingonline.hrapp.Custom.Myvollysinglton;
import com.supportingonline.hrapp.Custom.SpaceRecycler_V;
import com.supportingonline.hrapp.InterFaces.ErrorCall;
import com.supportingonline.hrapp.InterFaces.OnPress;
import com.supportingonline.hrapp.InterFaces.SuccessCall;
import com.supportingonline.hrapp.Model.UsersModel;
import com.supportingonline.hrapp.MyActivity;
import com.supportingonline.hrapp.R;
import com.supportingonline.hrapp.UserActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class UsersActivity extends MyActivity {


    private Toolbar toolbar;
    private TextView title;
    private View backView;

    private RelativeLayout addLayout;
    private EditText search;
    private RecyclerView recyclerView;
    private ArrayList<UsersModel> arrayList=new ArrayList<>();
    private UsersAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_users);
        addLayout=(RelativeLayout)findViewById(R.id.users_add);
        toolbar=(Toolbar)findViewById(R.id.users_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(View) toolbar.findViewById(R.id.t_normal_back);
        search= (EditText) findViewById(R.id.users_search);


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
        addLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UsersActivity.this, AddUserActivity.class));
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
            @Override
            public void onClick(View view, int position) {
                // click more
                popUp(view);


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
        StringRequest request=new MyRequest(getToken(),0,getUserUrl(),
                new OnSuccessRequest(new SuccessCall() {
                    @Override
                    public void OnBack(JSONObject object) {
                        Log.d("Tag",object.toString());

                        // success
                        if (object.has("success")) {
                            try {
                                JSONArray successArray = object.getJSONArray("success");
                                for (int i=0; i<successArray.length();i++){
                                    JSONObject jsonObject = successArray.getJSONObject(i);

                                    String id = jsonObject.getString("id");
                                    String name = jsonObject.getString("name");
                                    String gender = jsonObject.getString("gender");

                                    String email = null ;
                                    if(jsonObject.isNull("email")) {
                                        jsonObject.getString("email");
                                    }

                                    String image = null;
                                    if (!jsonObject.isNull("image")) {
                                      image  = jsonObject.getString("image");
                                    }

                                    String phone = null;
                                    if (!jsonObject.isNull("phone")) {
                                        phone  = jsonObject.getString("phone");
                                    }

                                    String hiringDate = null;
                                    if (!jsonObject.isNull("hiring_date")) {
                                        hiringDate= jsonObject.getString("hiring_date");
                                    }

                                    String salary = null;
                                    if (!jsonObject.isNull("salary")) {
                                        salary  = jsonObject.getString("salary");
                                    }



                                    String positionName = jsonObject.getString("position");

                                    UsersModel model = new UsersModel();
                                    model.setId(id);
                                    model.setName(name);
                                    model.setImage(image);
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

                        // error
                        else {
                            Toast.makeText(UsersActivity.this, getErrorServerMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }),new OnErrorRequest(getContext(), new ErrorCall() {
            @Override
            public void OnBack() {

            }
        }));
        Myvollysinglton.getInstance(getContext()).addtorequst(request);
    }

    // actions

    // pop up menu
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void popUp(View view){
        PopupMenu popupMenu=new PopupMenu(UsersActivity.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.user_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();

                switch (id){


                }
                return true;
            }
        });

        popupMenu.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}
