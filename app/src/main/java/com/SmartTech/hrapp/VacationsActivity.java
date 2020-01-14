package com.SmartTech.hrapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.SmartTech.hrapp.Adapter.DevicesAdapter;
import com.SmartTech.hrapp.Adapter.VacationsAdapter;
import com.SmartTech.hrapp.Api.MyRequest;
import com.SmartTech.hrapp.Api.OnErrorRequest;
import com.SmartTech.hrapp.Api.OnSuccessRequest;
import com.SmartTech.hrapp.Custom.MySizes;
import com.SmartTech.hrapp.Custom.Myvollysinglton;
import com.SmartTech.hrapp.Custom.SpaceRecycler_V;
import com.SmartTech.hrapp.InterFaces.ErrorCall;
import com.SmartTech.hrapp.InterFaces.OnPress;
import com.SmartTech.hrapp.InterFaces.SuccessCall;
import com.SmartTech.hrapp.Model.DeviceModel;
import com.SmartTech.hrapp.Model.VacationsModel;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VacationsActivity extends MyActivity {

    private Toolbar toolbar;
    private TextView title;
    private View backView;
    private ImageView imageAdd;

    private RecyclerView recyclerView;
    private VacationsAdapter adapter;
    private ArrayList<VacationsModel> arrayList=new ArrayList<>();

    private ShimmerFrameLayout shimmerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacations);


        // init
        recyclerView=(RecyclerView)findViewById(R.id.recycler_vacations);
        toolbar=(Toolbar)findViewById(R.id.vacations_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(View) toolbar.findViewById(R.id.t_normal_back);
        imageAdd=(ImageView)toolbar.findViewById(R.id.t_normal_add);
        shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.vacations_shimmer);

        //title
        title.setText(getResources().getString(R.string.vacations));

        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // add
        imageAdd.setVisibility(View.VISIBLE);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddVacationActivity.class));
                overridePendingTransition(R.anim.slide_from_righ,R.anim.slide_to_left);
            }
        });

        // recycler
        arrayList.clear();
        adapter=new VacationsAdapter(arrayList, getContext(), new OnPress() {
            @Override
            public void onClick(View view, int position) {

                // details
                startActivity(new Intent(getContext(), VacationDetailsActivity.class)
                .putExtra("id",arrayList.get(position).getId())
                );
                overridePendingTransition(R.anim.slide_up,R.anim.fadout);

            }
        }, new OnPress() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view, int position) {
                // more
                popUp(view,position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new SpaceRecycler_V(MySizes.gethight(getContext())/60));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),RecyclerView.VERTICAL));
        recyclerView.setAdapter(adapter);

        // load devices
        loadVacations();
    }

    private void loadVacations() {
        // shimmer
        shimmerContainer.setVisibility(View.VISIBLE);
        shimmerContainer.startShimmerAnimation();

        StringRequest request=new MyRequest(getToken(),0,getVacationsUrl(),new OnSuccessRequest(getContext(), new SuccessCall() {
            @Override
            public void OnBack(JSONObject object) {
                // shimmer
                shimmerContainer.setVisibility(View.GONE);

                if (object!=null){
                    try {
                        JSONArray array = object.getJSONArray("success");
                        for (int i = 0 ;i<array.length();i++){

                            JSONObject VACObject = array.getJSONObject(i);
                            String id = VACObject.getString("id");
                            String name = VACObject.getString("name");
                            String positionCount = VACObject.getString("positions");
                            String ratio = VACObject.getString("vacation_ratio");
                            String lastDate = VACObject.getString("last_vacation");

                            VacationsModel model = new VacationsModel();
                            model.setId(id);
                            model.setName(name);
                            model.setCountPosition(positionCount);
                            model.setRatio(ratio);
                            model.setLastDate(lastDate);

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

        Myvollysinglton.getInstance(this).addtorequst(request);
    }

    // pop up menu
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void popUp(View view, final int position){
        PopupMenu popupMenu=new PopupMenu(getContext(),view);
        popupMenu.getMenuInflater().inflate(R.menu.user_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                VacationsModel model = arrayList.get(position);
                int id=item.getItemId();

                switch (id){
                    case R.id.menu_u_edit:

                        startActivity(new Intent(getContext(), AddVacationActivity.class)
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
                       // deleteDeviceFromServer(position);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}
