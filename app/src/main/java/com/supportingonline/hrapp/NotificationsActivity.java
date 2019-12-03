package com.supportingonline.hrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.supportingonline.hrapp.Adapter.NotificationsAdapter;
import com.supportingonline.hrapp.Custom.MySizes;
import com.supportingonline.hrapp.Custom.SpaceRecycler_V;
import com.supportingonline.hrapp.InterFaces.OnPress;
import com.supportingonline.hrapp.Model.NotificationsModel;
import com.supportingonline.hrapp.Views.SwipeToDeleteCallback;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView title;
    private View backView;

    private RecyclerView recyclerView;
    private ArrayList<NotificationsModel> arrayList=new ArrayList<>();
    private NotificationsAdapter adapter;

    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // init
        toolbar=(Toolbar)findViewById(R.id.notification_toolbar);
        title=(TextView)toolbar.findViewById(R.id.t_normal_title);
        backView=(View) toolbar.findViewById(R.id.t_normal_back);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_notifications);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        //title
        title.setText(getResources().getString(R.string.notifications));

        // back
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // recycler
        arrayList.clear();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new SpaceRecycler_V(MySizes.gethight(this)/60));
        adapter=new NotificationsAdapter(arrayList, this, new OnPress() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);

        enableSwipeToDeleteAndUndo();









        // load
        loadNoti();
    }

    private void loadNoti() {
        for (int i= 0;i<10;i++){
            NotificationsModel  model=new NotificationsModel();
            arrayList.add(model);
        }
        adapter.notifyDataSetChanged();
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final NotificationsModel item = adapter.getData().get(position);

                adapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Notification was removed from the list.", Snackbar.LENGTH_LONG);

                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }
}
